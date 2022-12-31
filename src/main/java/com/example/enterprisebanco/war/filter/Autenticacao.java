package com.example.enterprisebanco.war.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mertins
 */
public class Autenticacao implements Filter {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public Autenticacao() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Logger log = Logger.getLogger(Autenticacao.class.getName());
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Boolean autenticado = (Boolean) req.getSession().getAttribute("UsuarioLogado");
        if (autenticado == null || !autenticado) {
            log.log(Level.INFO, String.format("Sem usuário autenticado [%s]", autenticado));
            if (!acessoPermitido(req)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
                return;
            }
        }
        log.log(Level.INFO, String.format("Usuário autenticado [%s]", autenticado));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private boolean acessoPermitido(HttpServletRequest req) {
        boolean ret = false;
        String pagina = req.getRequestURI();
        String acao = req.getParameter("acao");
        if (pagina != null && "login".equals(acao) && pagina.endsWith("principal")) {
            Logger.getLogger(Autenticacao.class.getName()).log(Level.INFO, String.format("Acesso autorizado sem autenticação à página [%s]", pagina));
            ret = true;
        }
        return ret;
    }
}
