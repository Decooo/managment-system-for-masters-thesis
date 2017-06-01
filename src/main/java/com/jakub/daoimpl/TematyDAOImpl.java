package com.jakub.daoimpl;

import com.jakub.dao.TematyDAO;
import com.jakub.model.Tematy;
import com.jakub.model.Wiadomosci;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jakub on 01.06.2017.
 */
public class TematyDAOImpl implements TematyDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.jakub.model");


    @Override
    public List<Tematy> showall() {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> topic= (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy", Tematy.class).getResultList();

        entityManager.close();
        return topic;
    }

    @Override
    public void add(int idPromotora, String temat) {
        EntityManager entityManager = emf.createEntityManager();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addTopic", Tematy.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .setParameter(1, idPromotora)
                .setParameter(2, temat);
        query.execute();

        entityManager.close();
    }
}
