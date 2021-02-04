package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidad.Restaurante;

@Stateless
public class RestauranteEJB extends AbstractFacade<Restaurante>{
	
	@PersistenceContext(unitName = "OrellanaJaramillo-Pedro-ExamenFinal")
	private EntityManager entityManager;

	public RestauranteEJB() {
		super(Restaurante.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
