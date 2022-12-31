package com.example.enterprisebanco.ejb.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.Serializable;


@Entity
public class Conta implements Serializable {//Cada conta deverá ter um 1 cliente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private int num_conta;
    private double saldoPoupanca;
    private double saldoCorrente;
    private double saldoAplicacao;

    @OneToOne
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSaldoPoupanca(Double saldoPoupanca) {
        this.saldoPoupanca = saldoPoupanca;
    }

    public double getSaldoPoupanca() {
        return saldoPoupanca;
    }

    public void setSaldoCorrente(Double saldoCorrente) {
        this.saldoCorrente = saldoCorrente;
    }

    public double getSaldoCorrente() {
        return saldoCorrente;
    }

    public void setSaldoAplicacao(Double saldoAplicacao) {
        this.saldoAplicacao = saldoAplicacao;
    }

    public double getSaldoAplicacao() {
        return saldoAplicacao;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {

        this.num_conta = num_conta;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Conta[ id=" + codigo + " ]";
    }

    public Double setSaldoCorrente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
