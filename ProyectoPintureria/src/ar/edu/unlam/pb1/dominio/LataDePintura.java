package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.TipoDePintura;

public class LataDePintura {


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
