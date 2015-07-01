package asktechforum.conection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoJPARemoto extends ConexaoAbs{

	private EntityManager em;
	private static EntityManagerFactory emf;
	
	static{
		emf = Persistence
				.createEntityManagerFactory("AskTechForumRemote");
	}
	
	@Override
	public void conectar() {
		this.em = ConexaoJPARemoto.emf.createEntityManager();
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

}
