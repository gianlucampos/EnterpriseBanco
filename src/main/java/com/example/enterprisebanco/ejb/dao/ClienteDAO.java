package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ClienteDAO implements ClienteDAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Cliente create(Cliente cli) {

        if (this.valida(cli)) {
            em.persist(cli);
            return cli;
        } else {
            return null;
        }
    }

    @Override
    public Cliente retrieve(Cliente cli) {
        Cliente cliRet = em.find(Cliente.class, cli.getCodigo());
        return cliRet;
    }

    @Override
    public Cliente retrieveLogin(String usuario, String senha, int numero) { //Aqui ele vai buscarr um cliente o da conta c onde essa conta c do cliente o possua tais parametros == aos de suas Entidades(numConta == ao parametro passado)

        Query query = em.createQuery("SELECT cli FROM Conta con INNER JOIN con.cliente cli where con.num_conta=:num and cli.usuario=:usu and cli.senha=:sen");
        query.setParameter("usu",usuario);
        query.setParameter("sen",senha );
        query.setParameter("num", numero);
        
        Cliente user = null;

        try {
            user = (Cliente) query.getSingleResult(); 

        } catch (Exception e) {
            
            System.out.println("Não foi válido o Login");
        }

        return user;
    }

    @Override
    public void update(Cliente cli) {
        if (this.valida(cli)) {
            em.merge(cli);
        }
    }

    @Override
    public void delete(Cliente cli) throws SQLException {
        em.remove(cli);
    }

    @Override
    public List<Cliente> listaTodos() {
        return (List<Cliente>) em.createNamedQuery("Cliente.findAll").getResultList();
    }

    @Override
    public boolean valida(Cliente cli) { //Regras de negocios: So pode cadastrar cliente com letra c

        boolean ret = false;

        if (cli != null) {
            ret = true;
        }

        return ret;
    }

}
