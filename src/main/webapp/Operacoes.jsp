<%-- 
    Tela principal (quando login for validado)
    Deve mostrar Informações do cliente e da operações da conta
    Fulano de tal
    Num conta 12321
    Selecione uma das opções para consulta:
    1)Histórico (Depositou tanto as 20:32)
    2)Movimetação (Tirar/colocar dinheiro de tal conta para outra)
    3)Depósito Conta (Corrente, Poupança, Aplicações)
    4)Saque conta Corrente
    5)Consulta (Corrente, Poupança, Aplicações)
    6)Sair
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css" />

        <title>Sua conta</title>
    </head>

    <%    String nome = (String) request.getAttribute("NomeCliente");
    %>

    <script type="text/javascript">
        function historico() {

            document.getElementById("acaoCRUD").value = "historico";
            document.operacoes.submit();
        }
        function consulta() {

            document.getElementById("acaoCRUD").value = "consulta";
            document.operacoes.submit();
        }
    </script>




    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 50px"> Terminal Bancário</h1>

            <div id="login-box">

                <h1 style="color: yellow">Cliente:  <%=nome%> </h1> 

                <h1 style="color:aliceblue">Escolha uma operação</h1>

                <form id="operacoes" name="operacoes" action="principal" method="post">

                    <a style="color: white" href="#" onclick="historico()"><h3>1)Histórico</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="form_aplicacao.jsp"><h3>2)Aplicação</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="form_transferencia.jsp" ><h3>3)Transferência</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="form_deposito.jsp" ><h3>4)Depósito</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="form_saque.jsp" ><h3>5)Saque</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="#" onclick="consulta()"><h3>6)Consulta</h3>
                        <input type="hidden" name="acao" value="conta"/>
                    </a>
                    <a style="color: white" href="principal?acao=logout"><h3>7)Sair</h3>
                    </a>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>                   
                </form>
            </div>    
        </div>
    </body>
</html>