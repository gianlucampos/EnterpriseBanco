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
        if ("true".equals(request.getAttribute("SucessoDep"))) {


    %>

    <script>

        alert("Depósito efetuado com sucesso");

    </script>

    <%        }

    %>

    <script>

        function depositar() {

            var tipoConta = document.getElementById("tipoConta").selectedIndex;// Pegar a opcao selecionada do combobox

            if (tipoConta == 1) { //Verificar qual foi a opcao selecionada

                document.getElementById("Contadep").value = "1";

            } else if (tipoConta == 2) {

                document.getElementById("Contadep").value = "2";

            } else if (tipoConta == 3) {
                document.getElementById("Contadep").value = "3";
            }
            document.getElementById("acaoCRUD").value = "deposito";
            document.formdeposito.submit();
        }
    </script>


    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 50px">Depósito</h1>

            <div id="login-box">

                <form id="formdeposito" name="formdeposito"  action="principal" method="POST">                    

                    <h3>Selecione a conta para qual você deseja depositar </h3>

                    <select id="tipoConta">
                        <option value=""></option>
                        <option value="corrente">corrente</option>
                        <option value="poupanca">poupanca</option>
                        <option value="aplicacao">aplicacao</option>
                    </select>

                    <h3> Insira a quantia </h3>

                    <input type="text" name="quantia" id="quantia" size="10"value=""/><br>
                    <br>
                    <br>

                    <input style="position: relative" type="button" onclick="depositar()" name="acaoCRUD" value="Depositar"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" value="conta"/>
                    <input type="hidden" id="Contadep" name="Contadep" value=""/> 
                    <a style="color: white;position: fixed; top: 445px; left: 650px" href="principal?acao=conta"><h3>Voltar</h3></a>
                </form>
            </div>        
        </div>
    </body>
</html>