package Filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ConexaoBD.UtilBD;
import Daos.controller.DaoControllerTimesCamp;
import Model.Login;

@WebFilter("*.jsf")
public class FilterAutenticacao implements Filter {

	@Inject
	private UtilBD utilBD;

	@Inject
	private DaoControllerTimesCamp daoControllerTimesCamp;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Login usuarioLogado = (Login) session.getAttribute("usuarioLogado");

		String passoSeguinte = (String) session.getAttribute("sorteio");

		String url = req.getServletPath();
		boolean t = url.contains("pagesCamp");
		if (!url.equalsIgnoreCase("login.jsf") && usuarioLogado == null
				&& !req.getRequestURI().contains("/javax.faces.resource/")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsf?faces-redirect=true");
			dispatcher.forward(request, response);
			return;
		} else if (t && daoControllerTimesCamp.verificarExistenciaCampeonato() && !url.contains("sorteio")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error/paginaNaoEncontrada.jsf");
			dispatcher.forward(request, response);
			/*
			 * try { ExternalContext ec =
			 * FacesContext.getCurrentInstance().getExternalContext();
			 * FacesContext.getCurrentInstance().getExternalContext().redirect(ec.
			 * getRequestContextPath() + "/error/paginaNaoEncontrada.jsf"); } catch
			 * (IOException e) { e.printStackTrace(); }
			 */
		} else if (url.contains("sorteio") && passoSeguinte == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error/paginaNaoEncontrada.jsf");
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		utilBD.getEntityManager();
	}

}
