package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entidad.Reserva;

@Stateless
public class ReservaEJB extends AbstractFacade<Reserva> {
	
	@PersistenceContext(unitName = "OrellanaJaramillo-Pedro-ExamenFinal")
	private EntityManager entityManager;

	public ReservaEJB() {
		super(Reserva.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return entityManager;
	}

}
