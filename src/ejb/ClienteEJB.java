package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidad.Cliente;

@Stateless
public class ClienteEJB extends AbstractFacade<Cliente> {

	@PersistenceContext(unitName = "OrellanaJaramillo-Pedro-ExamenFinal")
	private EntityManager entityManager;

	public ClienteEJB() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
