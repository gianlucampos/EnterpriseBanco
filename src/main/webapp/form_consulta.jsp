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

    <% 
        String saldoCorrente = (String) request.getAttribute("SaldoCorrente");
        String saldoPoupanca = (String) request.getAttribute("SaldoPoupanca");
        String saldoAplicacao = (String) request.getAttribute("SaldoAplicacao");        
    %>


    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">
            
            <h1 style="color: black; position: relative; top: 45px; left: 50px">Consulta de conta</h1>
            <div id="login-box">

                <form class="form-signin"  action="principal" method="POST">

                    <h1>Saldo:</h1>

                    <h1>Conta corrente</h1>
                    <h3 style="color: greenyellow">R$:<%=saldoCorrente%></h3>
                    <h1>Conta Poupanca</h1>
                    <h3 style="color: greenyellow">R$:<%=saldoPoupanca%></h3>
                    <h1>Aplicações</h1>
                    <h3 style="color: greenyellow">R$:<%=saldoAplicacao%></h3>
                    
                    <input type="hidden" name="acao" value="conta"/>
                    <a style="color: white;position: relative; left: 300px" href="principal?acao=conta"><h3>Voltar</h3></a>

                </form>
            </div>        
        </div>
    </body>
</html>