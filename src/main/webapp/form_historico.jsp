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

        <%
            String aplicou = (String) request.getAttribute("Aplicou");
            String transferiu = (String) request.getAttribute("Transferiu");
            String depositou = (String) request.getAttribute("Depositou");
            String sacou = (String) request.getAttribute("Sacou");
        %>

        <title>Sua conta</title>
    </head>

    <body style="background-color: #1e4f8a">
        <div style="padding: 30px 0 0 250px;">

            <h1 style="color: black; position: relative; top: 45px; left: 50px">Suas transações</h1>
            <div id="login-box">

                <form class="formHistorico" id="formHistorico" name="formHistorico" action="principal" method="POST">

                    <input type="hidden" name="acao" value="conta"/>
                    <a style="color: white;position: relative; top: 250px" href="principal?acao=conta"><h3>Voltar</h3></a>

                    <% if ("true".equals(request.getAttribute("Aplicado"))) {
                            request.setAttribute("Excessao", "true");
                    %>

                    <h3> Você aplicou em <%=aplicou%></h3> <%-- Só quero que apareca aqui quando tiver uma data, caso contrario nao mostra a data que foi aplicado --%>                    

                    <% }%>

                    <%if ("true".equals(request.getAttribute("Transferido"))) {
                            request.setAttribute("Excessao", "true");
                    %>

                    <h3> Você transferiu em <%=transferiu%></h3> <%-- Só quero que apareca aqui quando tiver uma data, caso contrario nao mostra data que foi transferido --%>                    

                    <% }%>

                    <%if ("true".equals(request.getAttribute("Depositato"))) {
                            request.setAttribute("Excessao", "true");
                    %>

                    <h3> Você depositou em <%=depositou%></h3> <%-- Só quero que apareca aqui quando tiver uma data, caso contrario nao mostra data que foi depositado --%>                    

                    <% }%>

                    <%  if ("true".equals(request.getAttribute("Sacado"))) {
                            request.setAttribute("Excessao", "true");
                    %>

                    <h3> Você sacou em <%=sacou%></h3> <%-- Só quero que apareca aqui quando tiver uma data, caso contrario nao mostra data que foi sacado --%>                    

                    <% }%>


                    <% if (!"true".equals(request.getAttribute("Excessao"))) { %>

                    <h3> Você não executou nenhuma operação atualmente</h3> <%-- Caso o cliente nao tenha feito nenhuma operacao --%>

                    <% }%>

                </form>
            </div>        
        </div>
    </body>
</html>