<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css" />

        <%
            if ("true".equals(request.getAttribute("SucessoAplic"))) {

                String investido = (String) request.getAttribute("investido");
                String lucro = (String) request.getAttribute("lucro");
                String retorno = (String) request.getAttribute("retorno");
        %>

        <script>

            alert("Investimento feito com sucesso" + "\nVocê investiu R$" +<%=investido%> + "\nSeus lucros foram de " + <%=lucro%> + "%" + "\nSeu retorno é: R$" +<%=retorno%>);

        </script>

        <%        }

        %>

        <%            if ("true".equals(request.getAttribute("SaldoAplicacaoInsuficiente"))) {

        %>

        <script>

            alert("Saldo insuficiente");

        </script>

        <%        }
        %>

        <script>
            function salvarAplicacao() {

                document.getElementById("acaoCRUD").value = "aplicacao";
                document.formaplica.submit();

            }
        </script>


        <title>Sua conta</title>
    </head>

    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 50px">Aplicação</h1>
            <div id="login-box">

                <form name="formaplica" id="formaplica" action="principal" method="POST">

                    <h3>Digite quanto você deseja aplicar</h3>

                    <input type="text" name="investimento" id="investimento" size="10"value=""/><br>
                    <br>
                    <br>
                    <input type="button" onclick="salvarAplicacao()"  name="acaoCRUD"value="Investir"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" id="acaoCRUD" value="conta"/>
                    <a style="color: white;position: relative; top: 150px" href="principal?acao=conta"><h3>Voltar</h3></a>

                </form>
            </div>        
        </div>
    </body>
</html>