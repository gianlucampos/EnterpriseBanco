package com.example.enterprisebanco.ejb.dao;

import com.example.enterprisebanco.ejb.bean.Transacao;
import jakarta.ejb.Local;

@Local
public interface TransacaoDAOLocal {
    
     public Transacao create(Transacao tra);

    public Transacao retrieve(Transacao tra);
    
    public Transacao retrieveTransacao(Long codigo);
    
    public void update(Transacao tra);

    public void delete(Transacao tra) throws java.sql.SQLException;

    public java.util.List<Transacao> listaTodos();

    public boolean valida(Transacao tra);
    
}
