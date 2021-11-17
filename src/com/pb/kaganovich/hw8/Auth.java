package com.pb.kaganovich.hw8;

/**
 * Класс авторизация
 */
public class Auth {
    private String login, password;

    public void signUp(String login, String password, String confirmpassword) throws WrongLoginException, WrongPasswordException {
        if (!login.matches("[A-Za-z0-9]{5,20}"))
            throw new WrongLoginException("Требования к логину: буквы, цифры (5-20 символов)");
        if (!password.matches("\\w{6,}"))
            throw new WrongPasswordException("Требования к паролю: буквы, цифры, подчеркивания (минимум 6 символов)");
        if (!password.equals(confirmpassword))
            throw new WrongPasswordException("Введенные пароли не совпадают!");
        this.login = login;
        this.password = password;
    }

    public void signIn(String login, String password) throws WrongLoginException, WrongPasswordException {
        if (!login.equals(this.login))
            throw new WrongLoginException();
        if (!password.equals(this.password))
            throw new WrongPasswordException();
    }

}
