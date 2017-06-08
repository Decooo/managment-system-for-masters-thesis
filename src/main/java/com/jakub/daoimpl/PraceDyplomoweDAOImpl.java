package com.jakub.daoimpl;

import com.jakub.dao.PraceDyplomoweDAO;
import com.jakub.model.PraceDyplomowe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by lenovo on 08.06.2017.
 */
public class PraceDyplomoweDAOImpl implements PraceDyplomoweDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.jakub.model");

    @Override
    public Boolean isAlreadyAssigned(int idUser) {

        EntityManager entityManager = emf.createEntityManager();
        List<PraceDyplomowe> reserved = (List<PraceDyplomowe>) entityManager.createNativeQuery("SELECT * FROM pracedyplomowe WHERE iduzytkownika='" + idUser + "'", PraceDyplomowe.class).getResultList();

        Boolean isAlreadyAssigned;
        if (reserved.isEmpty() == true) {
            isAlreadyAssigned = false;
        } else isAlreadyAssigned = true;
        entityManager.close();
        return isAlreadyAssigned;

    }

    @Override
    public PraceDyplomowe showMyTopic(int idUser) {
        EntityManager entityManager = emf.createEntityManager();
        PraceDyplomowe disseration;
        List<PraceDyplomowe> results = (List<PraceDyplomowe>) entityManager.createNativeQuery("SELECT * FROM pracedyplomowe WHERE iduzytkownika='" + idUser + "'", PraceDyplomowe.class).getResultList();
        entityManager.close();
        if (!results.isEmpty()) {
            disseration = results.get(0);
            return disseration;
        } else return null;
    }
}
