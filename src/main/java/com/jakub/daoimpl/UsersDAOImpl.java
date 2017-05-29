package com.jakub.daoimpl;

import com.jakub.dao.UsersDAO;
import com.jakub.model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Created by Jakub on 26.05.2017.
 */
public class UsersDAOImpl implements UsersDAO {

    @Autowired
    SessionFactory sessionFactory;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.jakub.model");


    @Override
    public Users findUser(String login) {
        EntityManager entityManager = emf.createEntityManager();
        Users user = (Users) entityManager.createNativeQuery("SELECT * FROM users WHERE login='" + login + "'", Users.class).getSingleResult();

        entityManager.close();
        return user;

    }

    @Override
    public Users findUserByID(int id) {
        EntityManager entityManager = emf.createEntityManager();
        Users user = (Users) entityManager.createNativeQuery("SELECT * FROM users WHERE iduser='" + id + "'", Users.class).getSingleResult();

        entityManager.close();
        return user;
    }

    @Override
    public void add(String login, String haslo, String imie, String nazwisko, String rola) {
        EntityManager entityManager = emf.createEntityManager();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("addusers", Users.class)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                .setParameter(1, login)
                .setParameter(2, haslo)
                .setParameter(3, imie)
                .setParameter(4, nazwisko)
                .setParameter(5, rola);
        query.execute();

        entityManager.close();
    }
}
