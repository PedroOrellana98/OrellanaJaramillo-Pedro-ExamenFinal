package controlador;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ejb.ClienteEJB;
import ejb.RestauranteEJB;
import entidad.Cliente;
import entidad.Restaurante;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ControladorCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClienteEJB clienteEJB;
	
	@EJB
	private RestauranteEJB restauranteEJB;
	
	private Cliente cliente;
	private Restaurante restaurante;
	private String nombre;
	private String apellido;
	private String cedula;
	private String correo;
	private String direccion;
	private String telefono;
	private String mensaje;
	
	public ClienteEJB getClienteEJB() {
		return clienteEJB;
	}
	public void setClienteEJB(ClienteEJB clienteEJB) {
		this.clienteEJB = clienteEJB;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	
	public void buscarCliente() {
		cliente = clienteEJB.find(cedula);
		if(cliente != null) {
			cedula = cliente.getCedula();
			nombre = cliente.getNombre();
			apellido = cliente.getApellido();
			telefono = cliente.getTelefono();
			direccion = cliente.getDireccion();
			correo = cliente.getCorreo();
			mensaje = "Se encontro la persona";
		}else{
			cedula = "";
			nombre = "";
			apellido = "";
			telefono = "";
			correo = "";
			direccion = "";
			mensaje = "No existe la persona";
		}
		
	}
	
	public void crearCliente() {
		List<Restaurante> restaurantes = restauranteEJB.findAll();
		for (Restaurante restaurante : restaurantes) {
				clienteEJB.create(new Cliente(cedula, nombre, apellido, correo, direccion, telefono, restaurante));
				mensaje = "Se encontro la persona";
		}
	}
	
}
