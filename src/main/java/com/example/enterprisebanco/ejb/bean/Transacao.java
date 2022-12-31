package com.example.enterprisebanco.ejb.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Transacao implements Serializable {//Deve ser feita 1 transacao por vez

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Temporal(TemporalType.DATE)
    private Date dtAplicacao; // Data que foi feito a aplicacao
    @Temporal(TemporalType.DATE)
    private Date dtTransferencia; // Data que foi feito a Transferencia
    @Temporal(TemporalType.DATE)
    private Date dtDeposito; // Data que foi feito a Deposito
    @Temporal(TemporalType.DATE)
    private Date dtSaque; // Data que foi feito a Saque

    @OneToOne
    private Conta conta;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Date getDtAplicacao() {
        return dtAplicacao;
    }

    public void setDtAplicacao(Date dtAplicacao) {
        this.dtAplicacao = dtAplicacao;
    }

    public Date getDtTransferencia() {
        return dtTransferencia;
    }

    public void setDtTransferencia(Date dtTransferencia) {
        this.dtTransferencia = dtTransferencia;
    }

    public Date getDtDeposito() {
        return dtDeposito;
    }

    public void setDtDeposito(Date dtDeposito) {
        this.dtDeposito = dtDeposito;
    }

    public Date getDtSaque() {
        return dtSaque;
    }

    public void setDtSaque(Date dtSaque) {
        this.dtSaque = dtSaque;
    }     

 
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transacao)) {
            return false;
        }
        Transacao other = (Transacao) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Transacao[ id=" + codigo + " ]";
    }

}
