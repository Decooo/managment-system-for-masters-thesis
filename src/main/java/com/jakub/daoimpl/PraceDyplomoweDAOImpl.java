package com.jakub.daoimpl;

import com.jakub.dao.PraceDyplomoweDAO;
import com.jakub.model.PraceDyplomowe;
import com.jakub.model.Tematy;

import javax.persistence.*;
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

    @Override
    public void addDissertation(byte[] pdf, int idUser) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addDissertation", PraceDyplomowe.class)
                .registerStoredProcedureParameter(1, byte[].class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, pdf)
                .setParameter(2, idUser);
        query.execute();

        entityManager.close();
    }

    @Override
    public PraceDyplomowe findDissertation(String idTematy) {
        EntityManager entityManager = emf.createEntityManager();
        PraceDyplomowe praceDyplomowe = (PraceDyplomowe) entityManager.createNativeQuery("SELECT * FROM pracedyplomowe WHERE idtematy='" + idTematy + "'", PraceDyplomowe.class).getSingleResult();

        entityManager.close();
        return praceDyplomowe;
    }

    @Override
    public List<PraceDyplomowe> showall() {
        EntityManager entityManager = emf.createEntityManager();
        List<PraceDyplomowe> topic = (List<PraceDyplomowe>) entityManager.createNativeQuery("SELECT * FROM pracedyplomowe", PraceDyplomowe.class).getResultList();

        entityManager.close();
        return topic;
    }

}
