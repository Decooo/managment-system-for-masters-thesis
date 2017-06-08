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
    public void delete(int idtematy) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("deleteTopic", Tematy.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idtematy);
        query.execute();

        entityManager.close();
    }

    @Override
    public void reserveTopic(int idTematy, int idUser) {
        EntityManager entityManager = emf.createEntityManager();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("reserveTopic", Tematy.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, idTematy)
                .setParameter(2, idUser);
        query.execute();
        entityManager.close();
    }

    @Override
    public List<Tematy> showall() {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> topic = (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy", Tematy.class).getResultList();

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

    @Override
    public List<Tematy> showFreeTopics() {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> topic = (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy WHERE status='wolny'", Tematy.class).getResultList();

        entityManager.close();
        return topic;
    }

    @Override
    public Boolean itIsAlreadyBooked(int idUser) {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> reserved = (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy WHERE iduser='" + idUser + "'", Tematy.class).getResultList();

        Boolean itIsAlreadyBooked;
        if (reserved.isEmpty() == true) {
            itIsAlreadyBooked = false;
        } else itIsAlreadyBooked = true;
        entityManager.close();
        return itIsAlreadyBooked;
    }

    @Override
    public List<Tematy> showallReservation() {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> topic = (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy WHERE status='zarezerwowany'", Tematy.class).getResultList();

        entityManager.close();
        return topic;

    }

    @Override
    public void acceptReservation(int idTematu, int idUser) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("acceptReservation", Tematy.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                .setParameter(1, idTematu)
                .setParameter(2, idUser);
        query.execute();

        entityManager.close();
    }

    @Override
    public void rejectReservation(int idTematu) {
        EntityManager entityManager = emf.createEntityManager();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("rejectReservation", Tematy.class)
                .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                .setParameter(1, idTematu);

        query.execute();

        entityManager.close();
    }

    @Override
    public Tematy findByID(int idTematu) {
        EntityManager entityManager = emf.createEntityManager();
        Tematy temat = (Tematy) entityManager.createNativeQuery("SELECT * FROM tematy WHERE idtematy='" + idTematu + "'", Tematy.class).getSingleResult();

        entityManager.close();
        return temat;
    }

    @Override
    public List<Tematy> showallMyTopics(int idUser) {
        EntityManager entityManager = emf.createEntityManager();
        List<Tematy> topic = (List<Tematy>) entityManager.createNativeQuery("SELECT * FROM tematy WHERE idpromotora='" + idUser + "'", Tematy.class).getResultList();

        entityManager.close();
        return topic;
    }
}
