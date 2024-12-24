package ar.edu.unlam.pb1.dominio;

import java.util.Arrays;

import ar.edu.unlam.pb1.dominio.enums.TipoDePintura;

public class Pintureria {

	// TODO: Completar getters, setters, constructor y metodos necesarios para
	// garantizar el correcto funcionamiento.

	private String nombre;
	private LataDePintura[] latasDePintura;
	private double saldo;
	private int cantidadLatasVendidas;
	private int cantidadLatasPintura;

	public Pintureria(String nombre, int cantidadLatasPintura) {
		this.nombre = nombre;
		this.cantidadLatasPintura = cantidadLatasPintura;
		this.latasDePintura = new LataDePintura[30];
		this.inicializarLatasDePintura();

	}

	private void inicializarLatasDePintura() {

		this.latasDePintura[0] = new LataDePintura(0, "Lila", 0.15, TipoDePintura.MATE, 24);
		this.latasDePintura[1] = new LataDePintura(1, "Turquesa", 0.18, TipoDePintura.SATINADA, 41);
		this.latasDePintura[2] = new LataDePintura(2, "Marron", 0.6, TipoDePintura.MATE, 74);
		this.latasDePintura[3] = new LataDePintura(3, "Cielo", 0.4, TipoDePintura.SATINADA, 51);
		this.latasDePintura[4] = new LataDePintura(4, "Pradera pimaveral", 0.20, TipoDePintura.MATE, 121);
		this.latasDePintura[5] = new LataDePintura(5, "Oasis", 0.17, TipoDePintura.SATINADA, 31);
		this.latasDePintura[6] = new LataDePintura(6, "Caribe", 0.14, TipoDePintura.MATE, 62);
		this.latasDePintura[7] = new LataDePintura(7, "Atardeser colonial", 0.8, TipoDePintura.SATINADA, 89);

	}

	public LataDePintura obtenerLataDePinturaPorCodigo(int codigo) {
		// TODO: Se debe buscar una lata de pintura por codigo entre las latas de
		// pintura que tiene la pintureria y devolverla. En caso de no existir alguna
		// que cumpla con el codigo, devolver null.

		int indice = 0;
		boolean seEncontroLata = false;
		LataDePintura lataDelMismoCodigo = null;

		while (indice < this.latasDePintura.length && !seEncontroLata) {
			if (this.latasDePintura[indice] != null && this.latasDePintura[indice].getCodigo() == codigo) {
				lataDelMismoCodigo = this.latasDePintura[indice];
				seEncontroLata = true;
			}
			indice++;
		}

		return lataDelMismoCodigo;
	}

	public boolean agregarLataDePintura(int codigo, String nombre, double porcentajeGanancia,
			TipoDePintura tipoDePintura, int stock) {
		// TODO: Se debera instanciar una lata de pintura y agregarla al array de latas
		// de pintura.

		int indice = 0;
		boolean seAgrego = false;

		LataDePintura lataCreada = new LataDePintura(codigo, nombre, porcentajeGanancia, tipoDePintura, stock);

		while (indice < this.latasDePintura.length && !seAgrego) {
			if (this.latasDePintura[indice] == null) {
				this.latasDePintura[indice] = lataCreada;
				seAgrego = true;
			}
			indice++;
		}

		return seAgrego;
	}

	public boolean hayStock(int codigo, int cantidadDeLatas) {
		// TODO: Se debera verificar si la pintureria cuenta con stock suficiente segun
		// el codigo de la lata de pintura solicitado. Se debe devolver verdadero en
		// caso de que el stock de la lata de pintura (que cumpla con el codigo), sea
		// mayor o igual a la cantidadDeLatas deseada.

		boolean tieneStock = false;
		LataDePintura lataObtenida = obtenerLataDePinturaPorCodigo(codigo);

		if (lataObtenida != null && lataObtenida.getStock() >= cantidadDeLatas) {

			tieneStock = true;

		}

		return tieneStock;
	}

	public void venderLatasDePintura(int codigo, int cantidadDeLatas) {
		// TODO: Se debera actualizar el stock de la lata de pintura, debiendo buscarla
		// por codigo, y luego restando la cantidad de latas a vender, al stock actual
		// de la lata de pintura.
		// Tambien es necesario contabilizar cuantas latas se vendieron y acumular el
		// precio total (precio de la lata por cantidad a vender) al saldo de la
		// pintureria.
		LataDePintura lataObtenida = obtenerLataDePinturaPorCodigo(codigo);
		int stockActualizado = lataObtenida.getStock() - cantidadDeLatas;
		lataObtenida.setStock(stockActualizado);

		int cantidadDeLatasVendidas = cantidadDeLatas;
		int indice = 0;
		double precioAcumulado = 0;

		while (indice <= cantidadDeLatasVendidas) {

			precioAcumulado += lataObtenida.obtenerPrecio();
			indice++;
		}

		setSaldo(precioAcumulado);

	}

	public int obtenerCantidadDeLatasDePinturasEnStockPorTipo(TipoDePintura tipoDePintura) {
		// TODO: Se debera obtener el numero (cantidad) de latas de pintura en stock
		// (considerando el stock de cada lata de pintura) que sean del tipo de pintura
		// especificado.

		int cantidadEnStockPorTipoDePintura = 0;

		for (int i = 0; i < this.latasDePintura.length; i++) {
			if (this.latasDePintura[i] != null) {
				if (this.latasDePintura[i].getTipoDePintura().equals(tipoDePintura)) {
					{
						cantidadEnStockPorTipoDePintura += this.latasDePintura[i].getStock();
					}
				}
			}
		}
		return cantidadEnStockPorTipoDePintura;
	}

	public LataDePintura obtenerLataDePinturaMasBarataPorTipo(TipoDePintura tipoDePintura) {
		// TODO: Se debera obtener la lata de pintura mas barata que aplique al tipo de
		// pintura especificado.

		int indice = 0;
		boolean seEncontroLata = false;
		LataDePintura lataMasBarataPorTipo = null;

		while (indice < this.latasDePintura.length && !seEncontroLata) {
			if (this.latasDePintura[indice] != null) {
				if (this.latasDePintura[indice].getTipoDePintura().equals(tipoDePintura)) {
					lataMasBarataPorTipo = this.latasDePintura[indice];
					seEncontroLata = true;
				}
			}
			indice++;
		}

		int indiceParaFor = indice - 1;

		for (int i = indiceParaFor; i < this.latasDePintura.length - 1; i++) {
			if (this.latasDePintura[i] != null) {
				if (this.latasDePintura[i].getTipoDePintura().equals(tipoDePintura)
						&& this.latasDePintura[i].obtenerPrecio() < lataMasBarataPorTipo.obtenerPrecio()) {
					{
						lataMasBarataPorTipo = this.latasDePintura[i];
					}
				}
			}
		}

		return lataMasBarataPorTipo;
	}

	public LataDePintura[] obtenerLatasDePinturaOrdenadasPorNombreAscendente() {
		// TODO: Se debera devolver un array de latas de pintura ordenados por el nombre
		// de la lata de pintura de manera ascendente.
		// Ejemplo: nombre "Azul" antes que "Rojo".

		LataDePintura aux = null;

		for (int i = this.latasDePintura.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (this.latasDePintura[j] != null && this.latasDePintura[j + 1] != null
						&& (this.latasDePintura[j].getNombre().compareTo(latasDePintura[j + 1].getNombre()) > 0)) {
					aux = latasDePintura[j];
					latasDePintura[j] = latasDePintura[j + 1];
					latasDePintura[j + 1] = aux;
				}
			}

		}

		return this.latasDePintura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LataDePintura[] getLatasDePintura() {
		return latasDePintura;
	}

	public void setLatasDePintura(LataDePintura[] latasDePintura) {
		this.latasDePintura = latasDePintura;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getCantidadLatasVendidas() {
		return cantidadLatasVendidas;
	}

	public void setCantidadLatasVendidas(int cantidadLatasVendidas) {
		this.cantidadLatasVendidas = cantidadLatasVendidas;
	}

	public String toStringParaResumen() {
		return "Saldo=" + saldo + ", Cantidad de latas vendidas=" + cantidadLatasVendidas + "]";
	}

}
