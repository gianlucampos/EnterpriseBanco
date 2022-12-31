package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Cliente;
import jakarta.ejb.Local;

@Local
public interface ClienteDAOLocal {

    public Cliente create(Cliente cli);

    public Cliente retrieve(Cliente cli);
    
    public Cliente retrieveLogin(String usuario, String senha, int numero);

    public void update(Cliente cli);

    public void delete(Cliente cli) throws java.sql.SQLException;

    public java.util.List<Cliente> listaTodos();

    public boolean valida(Cliente cli);

}
