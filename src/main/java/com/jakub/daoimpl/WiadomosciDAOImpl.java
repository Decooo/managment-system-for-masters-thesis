package com.jakub.daoimpl;

import com.jakub.dao.WiadomosciDAO;
import com.jakub.model.Users;
import com.jakub.model.Wiadomosci;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jakub on 29.05.2017.
 */
public class WiadomosciDAOImpl implements WiadomosciDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.jakub.model");


    @Override
    public List<Wiadomosci> showall(int id) {
        EntityManager entityManager = emf.createEntityManager();
        List<Wiadomosci> messages = (List<Wiadomosci>) entityManager.createNativeQuery("SELECT * FROM wiadomosci WHERE idadresata='" + id + "'", Wiadomosci.class).getResultList();

        entityManager.close();
        return messages;

    }

    @Override
    public List<Wiadomosci> showallsent(int id) {
        EntityManager entityManager = emf.createEntityManager();
        List<Wiadomosci> messages = (List<Wiadomosci>) entityManager.createNativeQuery("SELECT * FROM wiadomosci WHERE idnadawcy='" + id + "'", Wiadomosci.class).getResultList();

        entityManager.close();
        return messages;

    }

    @Override
    public void deleteMessage(int idwiadomosci) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("deleteMessage", Wiadomosci.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idwiadomosci);
        query.execute();

        entityManager.close();
    }
}

