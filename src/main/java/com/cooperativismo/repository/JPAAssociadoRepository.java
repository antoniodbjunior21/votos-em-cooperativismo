package com.cooperativismo.repository;

import com.cooperativismo.model.Associado;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class JPAAssociadoRepository {

    private final EntityManager em;

    public JPAAssociadoRepository(EntityManager em) {
        this.em = em;
    }
    public Associado buscarPor(String email, String senha){
        try {
            Query query = em.createQuery("SELECT u FROM Associado u WHERE u.email = :email AND u.senha = :senha ");
            query.setParameter("email", email.trim());
            query.setParameter("senha", senha);
            return (Associado) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

}
