package controlador;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ejb.ClienteEJB;
import ejb.ReservaEJB;
import ejb.RestauranteEJB;
import entidad.Cliente;
import entidad.Reserva;
import entidad.Restaurante;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ControladorReserva implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EJB
	private ReservaEJB reservaEJB;
	
	@EJB
	private ClienteEJB clienteEJB;
	
	@EJB
	private RestauranteEJB restauranteEJB;
	
	private Reserva reserva;
	private Cliente cliente;
	private Restaurante restaurante;
	private String fecha;
	private String hora;
	private String mensaje;
	private String cedula;
	private String nombre;
	private String mensaje1;
	private String advertencia;
	
	public ReservaEJB getReservaEJB() {
		return reservaEJB;
	}
	public void setReservaEJB(ReservaEJB reservaEJB) {
		this.reservaEJB = reservaEJB;
	}
	public ClienteEJB getClienteEJB() {
		return clienteEJB;
	}
	public void setClienteEJB(ClienteEJB clienteEJB) {
		this.clienteEJB = clienteEJB;
	}
	public RestauranteEJB getRestauranteEJB() {
		return restauranteEJB;
	}
	public void setRestauranteEJB(RestauranteEJB restauranteEJB) {
		this.restauranteEJB = restauranteEJB;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getMensaje1() {
		return mensaje1;
	}
	public void setMensaje1(String mensaje1) {
		this.mensaje1 = mensaje1;
	}
	
	public String getAdvertencia() {
		return advertencia;
	}
	public void setAdvertencia(String advertencia) {
		this.advertencia = advertencia;
	}
	
	
	public boolean buscarCliente() {
		boolean bandera = false;
		cliente = clienteEJB.find(cedula);
		if (cliente != null) {
			bandera = true;
			mensaje = "Se encontro la persona";
		}else{
			bandera = false;
			mensaje = "No existe la persona";
		}
		return bandera;
	}
	
	public boolean buscarRestaurante() {
		boolean bandera = false;
		List<Restaurante> restaurantes = restauranteEJB.findAll();
		for (Restaurante restaurante : restaurantes) {
			if (nombre.equals(restaurante.getNombre())) {
				bandera = true;
				mensaje1 = "Se encontro el restaurante";
			}else{
				bandera = false;
				mensaje1 = "No se encontro el restaurante";
			}
		}
		
		return bandera;
	}
	
	
	
	public void generarReserva() {
	List<Restaurante> restaurantes = restauranteEJB.findAll();
	List<Cliente> clientes = clienteEJB.findAll();
	if (buscarCliente() == true && buscarRestaurante() == true) {
		for (Restaurante restaurante : restaurantes) {
				for (Cliente cliente : clientes) {
					if (validarHora() == true) {
						reservaEJB.create(new Reserva(FechaString(fecha),hora,restaurante,cliente));
						advertencia = "Se creo la cita";
					}else {
						advertencia = "Existe la fecha y hora";
					}
					
				}
			}
		}
	}
	
	private GregorianCalendar FechaString(String fecha) {
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(fecha));
			return (GregorianCalendar) calendar;
		} catch (Exception e) {
			e.printStackTrace();
			return new GregorianCalendar();
		}
	}
	
	public boolean validarHora() {
		boolean bandera = false;
		List<Reserva> reservas = reservaEJB.findAll();
		for (Reserva reserva : reservas) {
			if (reserva.getFecha().equals(FechaString(fecha)) && reserva.getHora().equals(hora)) {
				bandera = false;
			} else {
				bandera = true;
			}
		}
		return bandera;
	}

	public void buscarClienteReserva() {
		cliente = clienteEJB.find(cedula);
		if (cliente != null) {
			List<Reserva> reservas = reservaEJB.findAll();
			for (Reserva reserva : reservas) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	            format.setCalendar(reserva.getFecha());
	            fecha = format.format(reserva.getFecha().getTime());
				hora = reserva.getHora();
				mensaje = "Se encontro la reserva";
			}
		}else{
			fecha = "";
			hora = "";
			mensaje = "No se encontro la reserva";
		}
		
	}
	
	public void buscarRestauranteReserva() {
		List<Restaurante> restaurantes = restauranteEJB.findAll();
		List<Reserva> reservas = reservaEJB.findAll();
		for (Restaurante restaurante : restaurantes) {
			for (Reserva reserva : reservas) {
				if (nombre.equals(restaurante.getNombre()) && reserva.getFecha().equals(FechaString(fecha))) {
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		            format.setCalendar(reserva.getFecha());
		            fecha = format.format(reserva.getFecha().getTime());
					hora = reserva.getHora();
					mensaje = "Se encontro la reserva";
				}else {
					nombre = "";
					fecha = "";
					hora = "";
					mensaje = "No se encontro la reserva";
				}
			}
		}
		
	}
	
}
