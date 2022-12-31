package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Transacao;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLException;
import java.util.List;


@Stateful
public class TransacaoDAO implements TransacaoDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Transacao create(Transacao tra) {

        if (this.valida(tra)) {
            em.persist(tra);
            return tra;
        } else {
            return null;
        }
    }

    @Override
    public Transacao retrieve(Transacao tra) {
        Transacao traRet = em.find(Transacao.class, tra.getCodigo());
        return traRet;
    }

    @Override
    public Transacao retrieveTransacao(Long codigo) {

        Query buscaTransacao = em.createQuery("SELECT tra FROM Transacao tra WHERE tra.conta.codigo=:cod");

        buscaTransacao.setParameter("cod", codigo);

        Transacao user = null;

        try {
            
            user = (Transacao) buscaTransacao.getSingleResult();

        } catch (Exception e) {

            System.out.println("Não existe esse cliente no banco");
        }

        return user;

    }

    @Override
    public void update(Transacao tra) {
        if (this.valida(tra)) {
            em.merge(tra);
        }
    }

    @Override
    public void delete(Transacao tra) throws SQLException {
        em.remove(tra);
    }

    @Override
    public List<Transacao> listaTodos() {
        return (List<Transacao>) em.createNamedQuery("Transacao.findAll").getResultList();
    }

    @Override
    public boolean valida(Transacao tra) {

        boolean ret = false;

        if (tra != null) {
            ret = true;
        }

        return ret;
    }

}
