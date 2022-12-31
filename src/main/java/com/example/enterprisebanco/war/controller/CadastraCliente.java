//Pegar os atributos da classe cliente e salvar no banco
//Pegar do CadastroConta.jsp o que foi inserido
package com.example.enterprisebanco.war.controller;


import com.example.enterprisebanco.ejb.bean.Cliente;
import com.example.enterprisebanco.ejb.bean.Conta;
import com.example.enterprisebanco.ejb.bean.Transacao;
import com.example.enterprisebanco.ejb.dao.ClienteDAOLocal;
import com.example.enterprisebanco.ejb.dao.ContaDAOLocal;
import com.example.enterprisebanco.ejb.dao.TransacaoDAOLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class CadastraCliente {

    private ClienteDAOLocal daoCliente;
    private ContaDAOLocal daoConta;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private TransacaoDAOLocal daoTra;

    public CadastraCliente(HttpServletRequest req, HttpServletResponse resp, ClienteDAOLocal daoCliente, ContaDAOLocal daoConta, TransacaoDAOLocal daoTra) {
        this.req = req;
        this.resp = resp;
        this.daoCliente = daoCliente;
        this.daoConta = daoConta;
        this.daoTra = daoTra;

    }

    public void processo() throws ServletException, IOException, SQLException { //decide o que fazer

        String acaoCRUD = req.getParameter("acaoCRUD"); //requisita o parametro pela tag acaoCRUD do CadastroConta.jsp

        if ("salvar".equals(acaoCRUD)) { //testa o parametro se for = salvar executa a função salvar
            this.salvar();
        } else {
            // caso nao caia no if ele redireciona para CadastroConta.jsp

            RequestDispatcher dispatcher = req.getRequestDispatcher("CadastroConta.jsp");
            dispatcher.forward(req, resp);

        }

    }

    private void salvar() throws ServletException, IOException { // Vai pegar os parametros colocados nas labels do CadastroConta.jsp

        String nome = req.getParameter("txtNome");
        int cpf = Integer.parseInt(req.getParameter("txtCpf"));
        int telefone = Integer.parseInt(req.getParameter("txtTelefone"));
        String email = req.getParameter("txtEmail");
        String usuario = req.getParameter("txtUsuario");
        String senha = req.getParameter("txtSenha");

        Cliente cli = new Cliente(); // Cria um objeto do tipo Cliente
        cli.setNome(nome);           // Preenche o objeto com todos os parametros passados nas labels do CadastroConta.jsp
        cli.setCpf(cpf);
        cli.setTelefone(telefone);
        cli.setEmail(email);
        cli.setUsuario(usuario);
        cli.setSenha(senha);

        daoCliente.create(cli); //Cria tuplas e preenche com os atributos passados pelas labels 

        int num_conta = (int) (Math.random() * 10000); //Pegar esse num_Conta e mostrar no alert
        Conta x = daoConta.existeNumConta(num_conta);// Teste pra ver se esse numero gerado existe no banco

        //Se x == null significa que não existe esse numero de conta no banco,
        //Se x != null o num_conta existe no banco, e deve ser gerado outro no lugar
        if (x != null) {

            do {

                num_conta = (int) (Math.random() * 10000);

            } while (x == null);
        }

        Conta con = new Conta();

        con.setCliente(cli);// Digo que o nome do cliente cadastrado pertence a esta conta (Conta = ID Clinte )
        con.setNum_conta(num_conta);//Coloco um nº aleatório de conta(que não deve repetir-se)
        con.setSaldoCorrente(1000.0);//Todos que se cadastram tem em suas contas R$1000.0,deixei assim só pra demonstração do trabalho mesmo
        con.setSaldoPoupanca(1000.0);
        con.setSaldoAplicacao(1000.0);

        daoConta.create(con);//Quando for cadastrado o cliente será criada no banco essa conta contendo o id do cliente cadastrado

        Transacao tra = new Transacao();
        
        tra.setConta(con);
        
        daoTra.create(tra);

        req.setAttribute("sucesso", "true");
        req.setAttribute("numeroConta", String.valueOf(num_conta));//Mostrar numero conta ao cadastrar com sucesso, para que o cliente saiba fazer o seu login

        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);

    }

}
