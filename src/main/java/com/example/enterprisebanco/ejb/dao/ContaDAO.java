package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Conta;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLException;
import java.util.List;


@Stateless
public class ContaDAO implements ContaDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Conta create(Conta con) {

        if (this.valida(con)) {
            em.persist(con);
            return con;
        } else {
            return null;
        }
    }

    @Override
    public Conta retrieve(Conta con) {
        Conta conRet = em.find(Conta.class, con.getCodigo());
        return conRet;
    }

    @Override
    public Conta retrieveContaDoCliente(Long codigo) {//Pegar a conta do cliente

        //Traga a conta onde a conta tal possua o cliente com esse codigo
        Query buscaCliente = em.createQuery("SELECT cc FROM Conta cc WHERE cc.cliente.codigo=:cod");

        buscaCliente.setParameter("cod", codigo);

        Conta user = null;

        try {
            user = (Conta) buscaCliente.getSingleResult();

        } catch (Exception e) {

            System.out.println("Não existe esse cliente no banco");
        }

        return user;
    }

    @Override
    public Conta retrieveContaRecebedor(int numero) {//Pegar a conta do cliente

        //Traga a conta do cliente que possua esse numero
        Query buscaCliente = em.createQuery("SELECT cc FROM Conta cc WHERE cc.num_conta=:num");

        buscaCliente.setParameter("num", numero);

        Conta user = null;

        try {
            user = (Conta) buscaCliente.getSingleResult();

        } catch (Exception e) {

            System.out.println("Não existe esse numero de conta no banco");
        }

        return user;
    }

    @Override
    public Conta existeNumConta(int numero) {

        Query buscaNumConta = em.createQuery("SELECT c FROM Conta c where c.num_conta=:num");

        buscaNumConta.setParameter("num", numero);

        Conta user = null;

        try {

            user = (Conta) buscaNumConta.getSingleResult();

        } catch (Exception e) {

            System.out.println("Não existe esse numero de conta aqui");
            return null;

        }

        return user;
    }

    @Override
    public void update(Conta con) {
        if (this.valida(con)) {
            em.merge(con);
        }
    }

    @Override
    public double updateSaldoAplicacao(double retorno) {

        Query updateSaldo = em.createQuery("UPDATE Conta cc SET cc.saldoAplicacao = cc.saldoAplicacao * :ret/100 + cc.saldoAplicacao");

        try {

            updateSaldo.setParameter("ret", retorno);

        } catch (Exception e) {

            System.out.println("Não foi possível alterar o saldo de aplicacao");
        }

        return updateSaldo.executeUpdate();
    }

    @Override
    public void delete(Conta con) throws SQLException {
        em.remove(con);
    }

    @Override
    public List<Conta> listaTodos() {
        return (List<Conta>) em.createNamedQuery("Conta.findAll").getResultList();
    }

    @Override
    public boolean valida(Conta con) {

        boolean ret = false;

        if (con != null) {
            ret = true;
        }

        return ret;
    }

}
