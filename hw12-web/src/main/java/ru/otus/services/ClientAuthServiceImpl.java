package ru.otus.services;

public class ClientAuthServiceImpl implements ClientAuthService {

    private final static String LOGIN = "user1";
    private final static String PASSWORD = "11111";

    @Override
    public boolean authenticate(String login, String password) {
        return LOGIN.equals(login) && PASSWORD.equals(password);
    }

}
