//Deve controlar as ações do Principal.jsp
//    1)Histórico (Depositou tanto as 20:32)
//    2)Aplicação (Investimento)
//    3)Transferência (Tirar/colocar dinheiro de tal conta para outra)
//    4)Depósito Conta (Corrente, Poupança, Aplicações)
//    5)Saque conta Corrente
//    6)Consulta (Corrente, Poupança, Aplicações)
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
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExibeConta {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private ClienteDAOLocal daoCliente;
    private ContaDAOLocal daoConta;
    private TransacaoDAOLocal daoTra;

    public ExibeConta(HttpServletRequest req, HttpServletResponse resp, ClienteDAOLocal daoCliente, ContaDAOLocal daoConta, TransacaoDAOLocal daoTra) {
        this.req = req;
        this.resp = resp;
        this.daoCliente = daoCliente;
        this.daoConta = daoConta;
        this.daoTra = daoTra;
    }

    public void processo() throws ServletException, IOException, SQLException { //decide o que fazer

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente");//Requisita a sessao da qual possui o cliente logado
        String NomeCliente = clienteLogado.getNome();//Pegar o nome do cliente logado
        req.setAttribute("NomeCliente", NomeCliente); //Passar atributo pro jsp como parametro

        String acaoCRUD = req.getParameter("acaoCRUD"); //requisita o parametro pela tag acaoCRUD do CadastroConta.jsp
        if ("historico".equals(acaoCRUD)) { //testa o parametro se for = salvar executa a função salvar
            this.historico();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_historico.jsp");
            dispatcher.forward(req, resp);
        } else if ("aplicacao".equals(acaoCRUD)) {
            this.aplicacao();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_aplicacao.jsp");
            dispatcher.forward(req, resp);
        } else if ("transferencia".equals(acaoCRUD)) {
            this.transferencia();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_transferencia.jsp");
            dispatcher.forward(req, resp);
        } else if ("deposito".equals(acaoCRUD)) {
            this.deposito();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_deposito.jsp"); //Despacha para o jsp
            dispatcher.forward(req, resp);
        } else if ("saque".equals(acaoCRUD)) {
            this.saque();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_saque.jsp");
            dispatcher.forward(req, resp);
        } else if ("consulta".equals(acaoCRUD)) {
            this.consulta();
            RequestDispatcher dispatcher = req.getRequestDispatcher("form_consulta.jsp"); //Despacha para o jsp
            dispatcher.forward(req, resp);
        } else {

            RequestDispatcher dispatcher = req.getRequestDispatcher("Operacoes.jsp");
            dispatcher.forward(req, resp);

        }
    }

    private void historico() throws ServletException, IOException { // Mostrar as operações feitas na conta

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente");//Requisita a sessao da qual possui o cliente logado
        Long codigo = clienteLogado.getCodigo();//Ṕegar o ID do cliente 
        Conta cliente = daoConta.retrieveContaDoCliente(codigo);// Pegar a conta do cliente
        Long codigoConta = cliente.getCodigo();//Pegar o codigo da conta do cliente
        Transacao transacao = daoTra.retrieveTransacao(codigoConta);//A transacao vai buscar o ID da conta

        Date dataAplicacao = transacao.getDtAplicacao();//Aqui ele vai colocar a data em que o cliente fez uma aplicacao
        if (dataAplicacao == null) {
            req.setAttribute("Aplicado", "false");
        } else {
            SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
            String data = formatoBrasileiro.format(dataAplicacao);
            req.setAttribute("Aplicou", String.valueOf(data));
            req.setAttribute("Aplicado", "true");

        }

        Date dataTransferencia = transacao.getDtTransferencia();//Aqui ele vai colocar a data em que o cliente fez uma transferencia
        if (dataTransferencia == null) {
            req.setAttribute("Transferido", "false");
        } else {

            SimpleDateFormat formatoBrasileiro2 = new SimpleDateFormat("dd/MM/yyyy");
            String data2 = formatoBrasileiro2.format(dataTransferencia);
            req.setAttribute("Transferiu", String.valueOf(data2));
            req.setAttribute("Transferido", "true");

        }

        Date dataDeposito = transacao.getDtDeposito();//Aqui ele vai colocar a data em que o cliente fez um saque
        if (dataDeposito == null) {
            req.setAttribute("Depositato", "false");
        } else {
            SimpleDateFormat formatoBrasileiro3 = new SimpleDateFormat("dd/MM/yyyy");
            String data3 = formatoBrasileiro3.format(dataDeposito);
            req.setAttribute("Depositou", String.valueOf(data3));
            req.setAttribute("Depositato", "true");

        }

        Date dataSaque = transacao.getDtSaque();//Aqui ele vai colocar a data em que o cliente fez uma aplicacao
        if (dataSaque == null) {
            req.setAttribute("Transferido", "false");
        } else {
            SimpleDateFormat formatoBrasileiro4 = new SimpleDateFormat("dd/MM/yyyy");
            String data4 = formatoBrasileiro4.format(dataSaque);
            req.setAttribute("Sacou", String.valueOf(data4));
            req.setAttribute("Sacado", "true");

        }

    }

    private void aplicacao() throws ServletException, IOException {// Investimentos de conta

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente"); //Requisitei a sessao do cliente que esta logado
        Long codigo = clienteLogado.getCodigo(); //Pegar o codigo
        Conta cliente = daoConta.retrieveContaDoCliente(codigo);
        Double investido = Double.parseDouble(req.getParameter("investimento"));
        Double saldoApli = cliente.getSaldoAplicacao();

        if (saldoApli < investido) {//Teste pra ver se existe saldo suficiente pra retirar

            req.setAttribute("SaldoAplicacaoInsuficiente", "true");//caso nao tenha ira aparecer saldo insuficiente

        } else {

            Double retira =  saldoApli - investido;
            cliente.setSaldoAplicacao(retira);
            daoConta.update(cliente);//Retirar o saldo para poder investir
            Double numero = (Math.random() * 100);
            Double numero2 = (Math.random() * 100);
            Double aleatorio = numero - numero2;//Gerando um numero aleatorio - ou + para somar com o investimento
            Double lucro = investido * (aleatorio / 100);// O retorno será o lucro ou prejuízo, do dinheiro aplicado
            Double retorno = lucro + investido;
            Double saldoAtual = cliente.getSaldoAplicacao() + retorno;                                            // Qual a diferença entre Double e double ?
            cliente.setSaldoAplicacao(saldoAtual);//Agora o saldo do cliente sera o retorno da aplicacao
            daoConta.update(cliente);

            req.setAttribute("SucessoAplic", "true");
            req.setAttribute("investido", String.valueOf(investido));//Passando informacoes sobre o investimento pro jsp
            req.setAttribute("lucro", String.valueOf(lucro));
            req.setAttribute("retorno", String.valueOf(retorno));//Passando o retorno pro jsp

            Long codigoConta = cliente.getCodigo();//Pegar o codigo da conta do cliente
            Transacao transacao = daoTra.retrieveTransacao(codigoConta);//A transacao vai buscar o ID da conta
            Date dataMovimentacao = new Date();//Setando a data atual, falta o horario
            transacao.setDtAplicacao(dataMovimentacao);// Colocar aqui a dataAplicacao em que essa operacao foi feita
            daoTra.update(transacao);

        }
    }

    private void transferencia() throws ServletException, IOException { //Aqui você deve passar dinheiro de uma conta pra outra(Joao passa R$100,00 para Maria por exemplo)

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente"); //Requisitei a sessao do cliente que esta logado
        Long codigo = clienteLogado.getCodigo(); //Pegar o ID do cliente logado para poder acessar sua conta 
        Conta clientePagador = daoConta.retrieveContaDoCliente(codigo);// buscar a conta do cliente atraves do id do cliente
        Double quantia = Double.parseDouble(req.getParameter("quantiatrans"));// quantia a ser passada
        int numConta = Integer.parseInt(req.getParameter("numContatrans"));// numero da conta a ser depositada
        Conta clienteRecebedor = daoConta.retrieveContaRecebedor(numConta);//buscar o cliente atraves do numero da conta

        if (clienteRecebedor == null) {

            req.setAttribute("ContaInvalida", "true");//caso queira passar o transferir o dinheiro pra propria conta, isso não deve ser permitido

        } else {

            if (clientePagador.getNum_conta() == clienteRecebedor.getNum_conta()) {

                req.setAttribute("TransferindoPraSi", "true");//caso queira passar o transferir o dinheiro pra propria conta, isso não deve ser permitido

            } else {

                String opcaoConta = req.getParameter("ContaTrans");// conta a ser retirada o dinheiro(Poupanca, corrente)
                if ("1".equals(opcaoConta)) {

                    Double saldoPagador = clientePagador.getSaldoCorrente(); //saldo contaCorrente
                    if (saldoPagador < quantia) {

                        req.setAttribute("SaldoInsuficiente", "true");//caso true ira aparecer saldo insuficiente
                    } else {

                        Double saldoAtual = saldoPagador - quantia; //Retirar o valor de la
                        clientePagador.setSaldoCorrente(saldoAtual);
                        daoConta.update(clientePagador); // colocar o valor do saldo da conta do doador
                        Double saldoRecebedor = clienteRecebedor.getSaldoCorrente();
                        Double saldoAtualRecebedor = saldoRecebedor + quantia;// coloca o valor no novo saldo recebedor
                        clienteRecebedor.setSaldoCorrente(saldoAtualRecebedor);
                        daoConta.update(clienteRecebedor);
                        req.setAttribute("SucessoTransferencia", "true");

                    }

                } else if ("2".equals(opcaoConta)) {

                    Double saldoPagador = clientePagador.getSaldoPoupanca(); //saldo contaCorrente
                    if (saldoPagador < quantia) {

                        req.setAttribute("SaldoInsuficiente", "true");//caso true ira aparecer saldo insuficiente
                    } else {

                        Double saldoAtual = saldoPagador - quantia; //Retirar o valor de la
                        clientePagador.setSaldoPoupanca(saldoAtual);
                        daoConta.update(clientePagador); // colocar o valor do saldo da conta do doador
                        Double saldoRecebedor = clienteRecebedor.getSaldoPoupanca();
                        Double saldoAtualRecebedor = saldoRecebedor + quantia;// coloca o valor no novo saldo recebedor
                        clienteRecebedor.setSaldoPoupanca(saldoAtualRecebedor);
                        daoConta.update(clienteRecebedor);
                        req.setAttribute("SucessoTransferencia", "true");

                    }

                }
                Long codigoConta = clientePagador.getCodigo();//Pegar o codigo da conta do cliente
                Transacao transacao = daoTra.retrieveTransacao(codigoConta);//A transacao vai buscar o ID da conta
                Date dataMovimentacao = new Date();//Setando a data atual, falta o horario
                transacao.setDtTransferencia(dataMovimentacao);// Colocar aqui a dataAplicacao em que essa operacao foi feita
                daoTra.update(transacao);
            }
        }
    }

    private void deposito() throws ServletException, IOException { //Ele irá passar o dinheiro de um tipo de conta para outra

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente"); //Requisitei a sessao do cliente que esta logado
        Long codigo = clienteLogado.getCodigo(); //Pegar o codigo do cliente logado
        Conta cliente = daoConta.retrieveContaDoCliente(codigo);// buscar a conta do cliente atraves do id do cliente
        int numero = cliente.getNum_conta();
        Double quantia = Double.parseDouble(req.getParameter("quantia"));// Requisita a quantia para ser depositada

        String opcaoConta = req.getParameter("Contadep");
        if ("1".equals(opcaoConta)) {

            Double valor = cliente.getSaldoCorrente();
            Double saldoAtual = quantia + valor;
            cliente.setSaldoCorrente(saldoAtual);
            daoConta.update(cliente);

        } else if ("2".equals(opcaoConta)) {//depositar na poupanca

            Double valor = cliente.getSaldoPoupanca();
            Double saldoAtual = quantia + valor;

            cliente.setSaldoPoupanca(saldoAtual);
            daoConta.update(cliente);

        } else if ("3".equals(opcaoConta)) {

            Double valor = cliente.getSaldoAplicacao();
            Double saldoAtual = quantia + valor;

            cliente.setSaldoAplicacao(saldoAtual);
            daoConta.update(cliente);
        }

        req.setAttribute("SucessoDep", "true");
        Long codigoConta = cliente.getCodigo();//Pegar o codigo da conta do cliente
        Transacao transacao = daoTra.retrieveTransacao(codigoConta);//A transacao vai buscar o ID da conta
        Date dataMovimentacao = new Date();//Setando a data atual, falta o horario
        transacao.setDtDeposito(dataMovimentacao);// Colocar aqui a dataAplicacao em que essa operacao foi feita
        daoTra.update(transacao);

    }

    private void saque() throws ServletException, IOException {

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente"); //Requisitei a sessao do cliente que esta logado
        Long codigo = clienteLogado.getCodigo(); //Pegar o codigo
        Conta cliente = daoConta.retrieveContaDoCliente(codigo);// buscar a conta do cliente atraves do id do cliente
        Double quantiaSaque = Double.parseDouble(req.getParameter("quantiasaq"));//requisetei a quantia sacada

        String opcaoConta = req.getParameter("Contasaq");
        if ("1".equals(opcaoConta)) {

            Double valor = cliente.getSaldoCorrente();

            if (valor < quantiaSaque) {//Teste pra ver se existe saldo suficiente pra retirar

                req.setAttribute("SaldoInsuficiente", "true");//caso nao tenha ira aparecer saldo insuficiente

            } else {

                Double saldoAtual = valor - quantiaSaque;
                cliente.setSaldoCorrente(saldoAtual);
                daoConta.update(cliente);
                req.setAttribute("SucessoSaq", "true");
            }

        } else if ("2".equals(opcaoConta)) {//vai retirar da poupanca

            Double valor = cliente.getSaldoPoupanca();

            if (valor < quantiaSaque) {

                req.setAttribute("SaldoInsuficiente", "true");

            } else {

                Double saldoAtual = valor - quantiaSaque;
                cliente.setSaldoPoupanca(saldoAtual);
                daoConta.update(cliente);
                req.setAttribute("SucessoSaq", "true");

            }
        }

        req.setAttribute("SucessoDep", "true");
        Long codigoConta = cliente.getCodigo();//Pegar o codigo da conta do cliente
        Transacao transacao = daoTra.retrieveTransacao(codigoConta);//A transacao vai buscar o ID da conta
        Date dataMovimentacao = new Date();
        transacao.setDtSaque(dataMovimentacao);// Colocar aqui a dataAplicacao em que essa operacao foi feita
        daoTra.update(transacao);//Um problema o banco de dados nao fica com a hora salva caso a operacao seja novamente :(

    }

    private void consulta() throws ServletException, IOException {

        Cliente clienteLogado = (Cliente) req.getSession().getAttribute("Cliente");//Requisita a sessao da qual possui o cliente logado
        Long codigo = clienteLogado.getCodigo();//Ṕegar o ID do cliente 
        Conta cliente = daoConta.retrieveContaDoCliente(codigo);// Pegar a conta do cliente

//        Ele precisa pegar o retorno do metodo do EJB, ou seja, a conta e passar ela para um objeto para poder acessar a conta do cliente
        Double saldoCorrente = cliente.getSaldoCorrente();
        Double saldoPoupanca = cliente.getSaldoPoupanca();
        Double saldoAplicacao = cliente.getSaldoAplicacao();

        req.setAttribute("SaldoCorrente", String.valueOf(saldoCorrente));
        req.setAttribute("SaldoPoupanca", String.valueOf(saldoPoupanca));
        req.setAttribute("SaldoAplicacao", String.valueOf(saldoAplicacao));

    }

}
