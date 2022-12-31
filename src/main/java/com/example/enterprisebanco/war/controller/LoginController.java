package com.example.enterprisebanco.war.controller;

import com.example.enterprisebanco.ejb.bean.Cliente;
import com.example.enterprisebanco.ejb.dao.ClienteDAOLocal;
import com.example.enterprisebanco.ejb.dao.ContaDAOLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private ClienteDAOLocal daoCliente;
    private ContaDAOLocal daoConta;

    public LoginController(HttpServletRequest req, HttpServletResponse resp, ClienteDAOLocal daoCliente, ContaDAOLocal daoConta) {
        this.req = req;
        this.resp = resp;
        this.daoCliente = daoCliente;
        this.daoConta = daoConta;
    }

    public void processo() throws ServletException, IOException, SQLException {

        String usuario = req.getParameter("txtUsuario");// Requisitar usuario do login
        int numeroConta = Integer.parseInt(req.getParameter("numConta"));//Requisitar numeroConta do login
        String senha = req.getParameter("txtSenha");// Requisitar senha do login  
        Cliente logado = daoCliente.retrieveLogin(usuario, senha, numeroConta);//verificar se existem esses parametros, caso existam retornar os dados desse cliente
        
        if (logado != null) {// Se os parametros do login nao forem nulos 

            req.getSession().setAttribute("UsuarioLogado", Boolean.TRUE);//envia um atributo para o filter que verifica se o usuario esta logado
            req.getSession().setAttribute("Cliente", logado); //Aqui ele vai passar o cliente logado como atributo para a session

            RequestDispatcher dispatcher = req.getRequestDispatcher("Principal.jsp");
            dispatcher.forward(req, resp);

        } else {
            Principal.dispatcherErro(req, resp, "Campos Inválidos.[%s]");
        }

    }
}
