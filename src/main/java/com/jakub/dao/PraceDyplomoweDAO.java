package com.jakub.dao;

import com.jakub.model.PraceDyplomowe;

import java.util.List;

/**
 * Created by lenovo on 08.06.2017.
 */
public interface PraceDyplomoweDAO {
    Boolean isAlreadyAssigned(int idUser);

    PraceDyplomowe showMyTopic(int idUser);

    void addDissertation(byte[] pdf, int idUser);
    PraceDyplomowe findDissertation(String idTematy);
    List<PraceDyplomowe> showall();
}
