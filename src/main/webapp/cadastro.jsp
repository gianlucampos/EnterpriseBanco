
<%-- Deve cadastrar um cliente no banco
     Será necessário um nº de conta, agencia e senha para completar o cadastro
--%>

<%@page import="com.example.enterprisebanco.ejb.bean.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de conta</title>

        <link href="css/layout.css" rel="stylesheet" type="text/css"/>
    </head>
    <script type="text/javascript">

        function limpar() {
            document.getElementById("txtNome").value = "";
            document.getElementById("txtCpf").value = "";
            document.getElementById("txtTelefone").value = "";
            document.getElementById("txtEmail").value = "";
            document.getElementById("txtUsuario").value = "";
            document.getElementById("txtSenha").value = "";
        }

        function salvar() {
            document.getElementById("acaoCRUD").value = "salvar";
            document.formcad.submit();
        }

    </script>


    <body style="background-color: #1e4f8a">

        <h1 style="position: relative; left: 300px;top: 80px"> Cadastre sua Conta </h1>

        <div style="padding: 50px 0 0 250px;">

            <div id="login-box">

                <form id="formcad" name="formcad" action="principal" method="post">
                    <h1 style="position: relative;"> Preencha os Campos abaixo: </h1>

                    <%
                        Cliente cliente = (Cliente) request.getAttribute("cliente"); //Instancia um objeto do tipo cliente
                    %>
                    <label> Nome: </label><br>

                    <input type="text" name="txtNome" id="txtNome" size="20"value="<%=cliente != null ? cliente.getNome() : ""%>"/><br>

                    <label> CPF: </label><br>

                    <input type="text" name="txtCpf" id="txtCpf" size="20"value="<%=cliente != null ? cliente.getCpf() : ""%>"/><br>

                    <label> Telefone </label><br>

                    <input type="text" name="txtTelefone"id="txtTelefone" size="20"value="<%=cliente != null ? cliente.getTelefone() : ""%>"/><br>
                    <label> Email </label><br>

                    <input type="text" name="txtEmail"id="txtEmail" size="20"value="<%=cliente != null ? cliente.getEmail() : ""%>"/><br>

                    <label> Nome de Usuario </label><br>

                    <input type="text" name="txtUsuario"id="txtUsuario" size="20"value="<%=cliente != null ? cliente.getUsuario() : ""%>"/><br>

                    <label> Senha </label><br>

                    <input type="password" name="txtSenha"id="txtSenha" size="20"value="<%=cliente != null ? cliente.getSenha() : ""%>"/><br>

                    <br>

                    <input type="hidden" name="acaoCRUD" id="acaoCRUD">


                    <input class="buttons" type="button" onclick="salvar()"  name="acaoCRUD"value="Salvar"/>
                    <input class="buttons" type="button" onclick="limpar()"  value="Limpar"/>


                    <a style="color: aliceblue ;position: relative;left: 50px; top: 0px " class="labvolar" href="index.jsp">Voltar</a>
                    <input type="hidden" name="acao" value="cadastro"/>    <%-- joga pro servlet acao == cadastro --%>
                </form>
            </div>
        </div>
    </body>
</html>