package ru.otus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.cfg.Configuration;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.core.repository.HibernateUtils;
import ru.otus.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DBServiceClient;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.server.ClientWebServer;
import ru.otus.server.ClientWebServerSimple;
import ru.otus.services.ClientAuthService;
import ru.otus.services.ClientAuthServiceImpl;
import ru.otus.services.TemplateProcessor;
import ru.otus.services.TemplateProcessorImpl;

import java.util.List;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/clients

    // REST сервис
    http://localhost:8080/api/clients/all
    http://localhost:8080/api/clients/add
*/
public class WebServerDemo {

    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws Exception {

        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");
        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var dbServiceClient = preparingToWorkWithDatabase(configuration);
        dbServiceClient.saveClient(new Client("Ибрагим", new Address(null, "Кострома"),
                List.of(new Phone(null, "88005553535"),
                        new Phone(null, "88009991515"))));
        dbServiceClient.saveClient(new Client("Andrew", new Address(null, "Istanbul"),
                List.of(new Phone(null, "552-22-21"),
                        new Phone(null, "1010"))));

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        ClientAuthService authService = new ClientAuthServiceImpl();

        ClientWebServer usersWebServer = new ClientWebServerSimple(WEB_SERVER_PORT, authService, dbServiceClient, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static DBServiceClient preparingToWorkWithDatabase(Configuration configuration) {
        var sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);
        var transactionManager = new TransactionManagerHibernate(sessionFactory);
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
        return new DbServiceClientImpl(transactionManager, clientTemplate);
    }
}
