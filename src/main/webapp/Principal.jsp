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
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css" />

        <title>Sua conta</title>
    </head>
    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">
             <h1 style="color: black; position: relative; top: 45px; left: 50px">Seja bem vindo </h1>
            <div id="login-box">


                <a style="color: aliceblue" href="principal?acao=conta"><h1>Acesse sua conta por aqui</h1>
                </a>

                <a style="color: white; position: fixed; top: 450px" href="principal?acao=logout"><h3>Sair</h3>
                    <input type="hidden" name="acao" value="logout"/>
                </a>

            </div>    
        </div>
    </body>
</html>