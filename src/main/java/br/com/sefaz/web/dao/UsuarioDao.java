package br.com.sefaz.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.sefaz.web.model.Usuario;

public class UsuarioDao {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public UsuarioDao() {
		
		emf = Persistence.createEntityManagerFactory("desafioSefaz");
		em = emf.createEntityManager();
	}


	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		List<Usuario> usuarios = null;

		try {
			usuarios = em.createQuery("from Usuario").getResultList();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.close();
		}

		return usuarios;
	}
	
	
	public void inserir (Usuario usuario) throws Exception {
//		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(usuario);		
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new Exception(e);
		}finally {
			em.close();
		}
	}
	
	public void atualizar (Usuario usuario) throws Exception {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	public void excluir (Long id) {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Usuario usuario = em.find(Usuario.class, id);
			em.remove(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	public Usuario selecionar(long id) throws Exception {
		Usuario usuario;

		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();

		try {
			usuario = em.find(Usuario.class, id);
		} finally {
			em.close();
		}

		return usuario;
	}
	
	
	public boolean loguin(String email, String senha) {
		EntityManager em = JpaResourceBean.getEntityManagerFactory().createEntityManager();
		String queryJPQL = "SELECT u FROM Usuario u WHERE u.email = :emailParam";
		
		TypedQuery<Usuario> emailDesejadp = em.createQuery(queryJPQL, Usuario.class).setParameter("emailParam", email);
		
		if(!emailDesejadp.getResultList().isEmpty()) {
			System.out.println("tem retorno");
			return true;
		}else {
			System.out.println("não tem retorno");
			return  false;
		}
	}
	
	public Usuario localizaPorEmail(String email) {
		return (Usuario) em.createQuery("from Usuario where upper(email) = "
				+ ":email").setParameter("email", email.toUpperCase()).getSingleResult();
	}
	
}
