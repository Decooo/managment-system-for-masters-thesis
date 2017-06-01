package com.jakub.dao;

import com.jakub.model.Tematy;

import java.util.List;
import java.util.Observable;

/**
 * Created by Jakub on 01.06.2017.
 */
public interface TematyDAO {

    List<Tematy> showall();
    void add(int idPromotora, String temat);
}
