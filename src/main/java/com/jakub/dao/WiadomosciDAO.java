package com.jakub.dao;

import com.jakub.model.Wiadomosci;

import java.util.List;

/**
 * Created by Jakub on 29.05.2017.
 */
public interface WiadomosciDAO {

    List<Wiadomosci> showall(int id);

    List<Wiadomosci> showallsent(int id);

    void deleteMessage(int idwiadomosci);
}
