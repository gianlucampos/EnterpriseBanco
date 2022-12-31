//O servlet principal irá requisitar o parametro com tag = acao e decidir qual classe executar
//Testar login 
//Testar cadastro de cliente
//Testar exibir conta
package com.example.enterprisebanco.war.controller;

import com.example.enterprisebanco.ejb.dao.ClienteDAOLocal;
import com.example.enterprisebanco.ejb.dao.ContaDAOLocal;
import com.example.enterprisebanco.ejb.dao.TransacaoDAOLocal;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Principal", urlPatterns = {"/principal"})
public class Principal extends HttpServlet {

    @EJB
    ClienteDAOLocal daoCliente; //Interface JPA
    @EJB
    ContaDAOLocal daoConta;
    @EJB
    TransacaoDAOLocal daoTra;
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String acao = req.getParameter("acao"); //requisita o parametro com a tag acao
            if ("login".equals(acao)) { //se acao == login

                LoginController log = new LoginController(req, resp, daoCliente, daoConta);
                log.processo();

            } else if ("cadastro".equals(acao)) { // se acao == cadastro
                CadastraCliente cad = new CadastraCliente(req, resp, daoCliente, daoConta,daoTra); // crie um objeto do tipo tal com nome cad
                cad.processo(); // executa o metodo processo(dentro do Cadastra Cliente             

            } else if ("conta".equals(acao)) { // se acao == conta
                ExibeConta exi = new ExibeConta(req, resp, daoCliente, daoConta,daoTra);
                exi.processo();
            } else if ("logout".equals(acao)) {
                req.getSession().invalidate();
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
            }

        } catch (Exception ex) {

            Principal.dispatcherErro(req, resp, ex.getMessage());
            ex.printStackTrace();

        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    public static void dispatcherErro(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("mensagem", msg);
        RequestDispatcher dispatchererro = req.getRequestDispatcher("erro.jsp");
        dispatchererro.forward(req, resp);

    }

}
