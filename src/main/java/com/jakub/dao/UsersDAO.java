package com.jakub.dao;

import com.jakub.model.Users;

/**
 * Created by Jakub on 26.05.2017.
 */
public interface UsersDAO {

    Users findUser(String login);

    Users findUserByID(int id);

    void add(String login, String haslo, String imie, String nazwisko, String rola);
}
