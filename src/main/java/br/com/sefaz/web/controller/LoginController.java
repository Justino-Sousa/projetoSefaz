package br.com.sefaz.web.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.web.dao.UsuarioDao;
import br.com.sefaz.web.model.Usuario;

@SuppressWarnings("serial")
@ManagedBean(name = "loguinController")
@SessionScoped
public class LoginController implements Serializable{
	private UsuarioDao UsuarioDao;
	private Usuario usuarioLogado;
	private String email;
	private String senha;
	
	public LoginController() {
		UsuarioDao = new UsuarioDao();
	}
	
	public String paginaLoguin() {
		return "/login";
	}
	
	public String efetuarLogin() {
		if(UsuarioDao.loguin(email, senha)) {
			usuarioLogado = UsuarioDao.localizaPorEmail(email);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Login efetuado com Sucesso!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			return"/index";
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Loguin não efetuado, Usuário ou senha Inválidos."));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "/login";
		}
	}
	
	
	public String efetuarLogout() {
		usuarioLogado = null;
		return "/index";
	}
	
	
	public UsuarioDao getDao() {
		return UsuarioDao;
	}
	public void setDao(UsuarioDao dao) {
		this.UsuarioDao = dao;
	}
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
