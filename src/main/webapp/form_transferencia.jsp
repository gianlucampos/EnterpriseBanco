<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/layout.css" rel="stylesheet" type="text/css" />

        <title>Sua conta</title>
    </head>

    <%
        if ("true".equals(request.getAttribute("TransferindoPraSi"))) {

    %>

    <script>

        alert("Seu espertinho, quer transferir dinheiro pra sua própria conta neh ?\n Operação invalida\n Você so pode transferir dinheiro para uma conta diferente");

    </script>

    <%          }%>

    <%
        if ("true".equals(request.getAttribute("ContaInvalida"))) {

    %>

    <script>

        alert("Número de Conta invalido \n Digite o numero da conta novamente");

    </script>

    <%          }%>


    <% if ("true".equals(request.getAttribute("SucessoTransferencia"))) {


    %>

    <script>

        alert("Retirada feita com sucesso");

    </script>

    <%          }%>

    <% if ("true".equals(request.getAttribute("SaldoInsuficiente"))) {

    %>

    <script>

        alert("Saldo insuficiente");

    </script>

    <%        }
    %>

    <script>

        function salvar() {

            var tipoConta = document.getElementById("tipoContatransf").selectedIndex;
            if (tipoConta == 1) { //Verificar qual foi a opcao selecionada

                document.getElementById("ContaTrans").value = 1;

            } else if (tipoConta == 2) {

                document.getElementById("ContaTrans").value = 2;

            }
            document.getElementById("acaoCRUD").value = "transferencia";
            document.formtransferencia.submit();
        }


    </script>

    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 35px">Transferência de conta</h1>

            <div id="login-box">

                <form class="form-trans" id="formtransferencia" name="formtransferencia"  action="principal" method="POST">

                    <h3>De qual conta você deseja retirar o dinheiro ?</h3>

                    <select id="tipoContatransf">
                        <option value=""></option>
                        <option value="corrente">corrente</option>
                        <option value="poupanca">poupanca</option> 
                    </select>

                    <br>
                    <h3> Numero da conta da pessoa que vai receber: </h3>

                    <input type="text" name="numContatrans" id="numContatrans" size="10"value=""/><br>

                    <h3> Digite a quantia da grana: </h3>

                    <input type="text" name="quantiatrans" id="quantiatrans" size="10"value=""/><br>
                    <br>
                    <br>
                    <input style="position: relative" type="button" onclick="salvar()" value="Salvar"/>
                    <input type="hidden" id="acaoCRUD" name="acaoCRUD" value=""/>
                    <input type="hidden" name="acao" value="conta"/>
                    <input type="hidden" id="ContaTrans" name="ContaTrans" value=""/> 
                    <a style="color: white;position: relative; top: -20px; left: 310px" href="principal?acao=conta"><h3>Voltar</h3></a>

                </form>
            </div>        
        </div>
    </body>
</html>