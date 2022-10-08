package br.com.erivelton.canchafut.conexaoBD;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class UtilBD {
	private static EntityManagerFactory emf;
	
	static {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("testeTCC");
		}
	}
	
	public static EntityManager getEntityMangaer() {
		return emf.createEntityManager();
	}
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public Object getPrimaryKey(Object entidade) {
		return emf.getPersistenceUnitUtil().getIdentifier(entidade);
	}
}
