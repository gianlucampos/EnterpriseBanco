package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Conta;
import jakarta.ejb.Local;

@Local
public interface ContaDAOLocal {

    public Conta create(Conta con);

    public Conta retrieve(Conta con);
    
    public Conta retrieveContaDoCliente(Long codigo);
    
    public Conta retrieveContaRecebedor(int numero);

    public Conta existeNumConta(int numero);
    
    public void update(Conta con);
       
    public double updateSaldoAplicacao(double retorno);

    public void delete(Conta con) throws java.sql.SQLException;

    public java.util.List<Conta> listaTodos();

    public boolean valida(Conta con);

}
