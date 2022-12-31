<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css" />

        <title>Sua conta</title>
    </head>

    <%
        if ("true".equals(request.getAttribute("SucessoSaq"))) {


    %>

    <script>

        alert("Retirada feita com sucesso");

    </script>

    <%        }

    %>

    <%
        if("true".equals(request.getAttribute("SaldoInsuficiente"))){
        
    %>

    <script>

        alert("Saldo insuficiente");

    </script>

    <%
    }
    %>


    <script type="text/javascript">

        function saque() {

            var tipoContadep = document.getElementById("tipoContaSaque").selectedIndex;// Pegar a opcao selecionada do combobox

            if (tipoContadep == 1) { //Verificar qual foi a opcao selecionada

                document.getElementById("Contasaq").value = "1";

            } else if (tipoContadep == 2) {

                document.getElementById("Contasaq").value = "2";

            }
            document.getElementById("acaoCRUD").value = "saque";
            document.formsaque.submit();
        }
    </script>


    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 50px">Saque</h1>


            <div id="login-box">

                <form class="formsaque" name="formsaque" id="formsaque" action="principal" method="POST">

                    <h3>Selecione a conta</h3>

                    <select id="tipoContaSaque">

                        <option value=""></option>
                        <option value="corrente">corrente</option>
                        <option value="poupanca">poupanca</option>

                    </select>

                    <br>
                    <h3> Digite a quantia: </h3>

                    <input type="text" name="quantiasaq" id="quantiasaq" size="10"value=""/><br>
                    <br>
                    <br>

                    <input style="position: relative; top: 50px" type="button" onclick="saque()" value="ok"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" value="conta"/>
                    <input type="hidden" id="Contasaq" name="Contasaq" value=""/> 
                    <a style="color: white;position: relative; top: 50px; left: 310px" href="principal?acao=conta"><h3>Voltar</h3></a>

                </form>
            </div>        
        </div>
    </body>
</html>