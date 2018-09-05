package br.senai.sp.jaguariuna.sccv.filter;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senai.sp.jaguariuna.sccv.mBeans.AlunoIndexMBean;

public class UserFilter implements Filter {

	private AlunoIndexMBean alunoIndexMBean;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		alunoIndexMBean = (AlunoIndexMBean) ((HttpServletRequest) request).getSession().getAttribute("alunoIndexMBean");

		if (alunoIndexMBean != null) {
			if (alunoIndexMBean.getUsuario() != null) {
				chain.doFilter(request, response);
			} else {
				redirecionar(request, response);
			}
		}else {
			redirecionar(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	private void redirecionar(ServletRequest request, ServletResponse response) throws IOException {
		String path = ((HttpServletRequest) request).getContextPath();
		((HttpServletResponse) response).sendRedirect(path + "/aluno/index.xhtml");
	}

}
