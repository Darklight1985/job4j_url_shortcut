package ru.job4j.shortcut.dto;

public class AnswerDto {
    private boolean registration;

    private String login;

    private String password;

    public AnswerDto(boolean registrtion, String login, String password) {
        this.registration = registrtion;
        this.login = login;
        this.password = password;
    }

    public boolean isRegistrtion() {
        return registration;
    }

    public void setRegistrtion(boolean registrtion) {
        this.registration = registrtion;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
