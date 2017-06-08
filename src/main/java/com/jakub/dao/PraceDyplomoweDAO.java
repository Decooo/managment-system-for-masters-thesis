package com.jakub.dao;

import com.jakub.model.PraceDyplomowe;
import com.jakub.model.Tematy;

/**
 * Created by lenovo on 08.06.2017.
 */
public interface PraceDyplomoweDAO {
    Boolean isAlreadyAssigned(int idUser);
    PraceDyplomowe showMyTopic(int idUser);
}
