package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePintura;

public class LataDePintura {

	// TODO: Completar getters, setters, constructor y metodos necesarios para
	// garantizar el correcto funcionamiento.

	private final double PRECIO_BASE = 1000;

	private int codigo;
	private String nombre;
	private TipoDePintura tipoDePintura;
	private int stock;
	private double porcentajeDeGanancia;

	public LataDePintura(int codigo, String nombre, double porcentajeDeGanancia, TipoDePintura tipoDePintura,
			int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.porcentajeDeGanancia = porcentajeDeGanancia;
		this.tipoDePintura = tipoDePintura;
		this.stock = stock;
	}

	public double obtenerPrecio() {
		// TODO: Calcular y obtener el precio de la lata de pintura, el cual se calcula
		// segun su TipoDePintura.
		// Todas las latas de pinturas son blancas y tienen un precio base. En caso de
		// ser satinadas, tonalizar la pintura blanca cuesta un 15% extra. En cambio,
		// tonalizar las pinturas mate, cuesta un 5%, pero incluye otro 3% de impuestos
		// (calculado sobre el precio base) que se debe agregar al precio final. No
		// olvidar agregar el porcentaje de ganancia, tambien calculado sobre el precio
		// base.

		double precioTonalizarSatinada = PRECIO_BASE * 0.15;
		double precioTonalizarMate = PRECIO_BASE * 0.08;
		double precioGanancia = PRECIO_BASE * getPorcentajeDeGanancia();

		double precio = 0;
		switch (getTipoDePintura()) {
		case SATINADA:
			precio = PRECIO_BASE + precioTonalizarSatinada + precioGanancia;
			break;
		case MATE:
			precio = PRECIO_BASE + precioTonalizarMate + precioGanancia;
			break;
		}
		return precio;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDePintura getTipoDePintura() {
		return tipoDePintura;
	}

	public void setTipoDePintura(TipoDePintura tipoDePintura) {
		this.tipoDePintura = tipoDePintura;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPorcentajeDeGanancia() {
		return porcentajeDeGanancia;
	}

	public void setPorcentajeDeGanancia(double porcentajeDeGanancia) {
		this.porcentajeDeGanancia = porcentajeDeGanancia;
	}
	
	public String toStringParaResumen() {
		return "[Codigo=" + codigo + ", nombre=" + nombre + ", tipoDePintura=" + tipoDePintura
				+ ", stock=" + stock +"]";
	}
	
	@Override
	public String toString() {
		return "LataDePintura [codigo=" + codigo + ", nombre=" + nombre + ", tipoDePintura=" + tipoDePintura
				+ ", stock=" + stock + ", porcentajeDeGanancia=" + porcentajeDeGanancia + "]";
	}

	

}
