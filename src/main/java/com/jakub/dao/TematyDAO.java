package com.jakub.dao;

import com.jakub.model.Tematy;

import java.util.List;
import java.util.Observable;

/**
 * Created by Jakub on 01.06.2017.
 */
public interface TematyDAO {

    void reserveTopic(int idTematy, int idUser);

    List<Tematy> showall();

    void add(int idPromotora, String temat);

    List<Tematy> showFreeTopics();

    Boolean itIsAlreadyBooked(int idUser);

    List<Tematy> showallReservation();

    void acceptReservation(int idTematu, int idUser);
    void rejectReservation(int idTematu);

    Tematy findByID(int idTematu);
}
