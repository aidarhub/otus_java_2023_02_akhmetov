package ru.otus.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cache.cachehw.HwCache;
import ru.otus.cache.cachehw.MyCache;
import ru.otus.core.repository.DataTemplate;
import ru.otus.core.sessionmanager.TransactionManager;
import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.valueOf;

public class DbServiceClientImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> clientDataTemplate;
    private final TransactionManager transactionManager;
    private final HwCache<String, Client> cache = new MyCache<>();

    public DbServiceClientImpl(TransactionManager transactionManager, DataTemplate<Client> clientDataTemplate) {
        this.transactionManager = transactionManager;
        this.clientDataTemplate = clientDataTemplate;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(session -> {
            var clientCloned = client.clone();
            String stringId = String.valueOf(clientCloned.getId());
            if (client.getId() == null) {
                clientDataTemplate.insert(session, clientCloned);
                cache.put(stringId, clientCloned);
                log.info("created client: {}", clientCloned);
                return clientCloned;
            }
            clientDataTemplate.update(session, clientCloned);
            cache.put(stringId, clientCloned);
            log.info("updated client: {}", clientCloned);
            return clientCloned;
        });
    }

    @Override
    public Optional<Client> getClient(long id) {
        Client clientCache = cache.get(valueOf(id));
        if (Objects.nonNull(clientCache)) {
            return Optional.of(clientCache);
        } else {
            return transactionManager.doInReadOnlyTransaction(session -> {
                var clientOptional = clientDataTemplate.findById(session, id);
                log.info("client: {}", clientOptional);
                clientOptional.ifPresent(c -> cache.put(Long.toString(id), c));
                return clientOptional;
            });
        }
    }

    @Override
    public List<Client> findAll() {
        return transactionManager.doInReadOnlyTransaction(session -> {
            var clientList = clientDataTemplate.findAll(session);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
