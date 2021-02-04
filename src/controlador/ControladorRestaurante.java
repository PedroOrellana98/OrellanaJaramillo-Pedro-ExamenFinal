package controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ejb.RestauranteEJB;
import entidad.Restaurante;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ControladorRestaurante implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private RestauranteEJB restauranteEJB;

	private Restaurante restaurante;
	private String nombre;
	private String direccion;
	private String telefono;
	private String mensaje;
	
	public RestauranteEJB getRestauranteEJB() {
		return restauranteEJB;
	}

	public void setRestauranteEJB(RestauranteEJB restauranteEJB) {
		this.restauranteEJB = restauranteEJB;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void crearRestaurante() {

		List<Restaurante> restaurantes = restauranteEJB.findAll();
		
			restauranteEJB.create(new Restaurante(nombre, direccion, telefono));
			mensaje = "Se encontro el restaurante";
		
			//mensaje = "No se creo el restaurante";
		
	}
}
