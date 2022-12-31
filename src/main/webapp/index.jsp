<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page errorPage="Erro_Login.jsp"%>--%>
<%
// Evitar cache das páginas
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Login </title>

        <link href="css/layout.css" rel="stylesheet" type="text/css"/>
    </head>

    <body style="background-color: #1e4f8a">

        <%
            if ("true".equals(request.getAttribute("sucesso"))) {

              String scota=(String)  request.getAttribute("numeroConta");

        %>

        <script>
            alert("O numero da sua conta é:" + <%=scota%>);// Quero passar o numero conta aqui
        </script>

        <%
            }
        %>



        <a style= "color: white" href="cadastro.jsp"><h5>Não possui uma conta ? Cadastre se clicando aqui!</h5></a>

        <div style="padding: 50px 0 0 250px;">
            <div id="login-box">

                <form class="form-signin"  action="principal" method="POST">

                    <h1 style="position: relative;top: -15px;left: 80px">  Banco Virtual</h1>
                    <h1 style="position: relative;top: -22px;left: 50px">Acesse sua conta </h1>  


                    <div id="login-box-name">Usuário</div>
                    <div id="login-box-field">
                        <input name="txtUsuario" class="form-login" id="txtUsuario" value="" size="30" maxlength="2048" /></div>

                    <div id="login-box-name">Conta</div>
                    <div id="login-box-field">
                        <input name="numConta"  class="form-login" id="numConta" value="" size="30" maxlength="2048" /></div>

                    <div id="login-box-name">Senha</div>
                    <div id="login-box-field">
                        <div>
                            <input name="txtSenha"type="password" class="form-login" id="txtSenha" title="senha" value="" size="30" maxlength="2048" /></div>

                        <button class="botao" type="submit" value=""></button>
                        <input type="hidden" id="acao" name="acao" value="login"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
