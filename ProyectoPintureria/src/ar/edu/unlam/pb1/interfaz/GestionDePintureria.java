package ar.edu.unlam.pb1.interfaz;

import java.util.Iterator;
import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.LataDePintura;
import ar.edu.unlam.pb1.dominio.Pintureria;
import ar.edu.unlam.pb1.dominio.enums.TipoDePintura;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class GestionDePintureria {

	private static final Scanner TECLADO = new Scanner(System.in);

	public static void main(String[] args) {

		String nombre = ingresarString("\nIngrese el nombre de su pintureria: ");
		int cantidadDeLatasDePintura = ingresarNumeroEntero("\nIngresar la cantidad de latas de pintura: ");

		Pintureria pintureria = new Pintureria(nombre, cantidadDeLatasDePintura);

		MenuPrincipal opcion = null;

		do {
			opcion = obtenerOpcionDeEnumParaMenuPrincipal();
			switch (opcion) {
			case AGREGAR_LATA_PINTURA:
				agregarLataDePintura(pintureria);
				break;
			case VENDER_LATAS_PINTURA:
				venderLatasDePintura(pintureria);
				break;
			case MOSTRAR_CANTIDAD_LATAS_EN_STOCK_POR_TIPO:
				mostrarCantidadDeLatasEnStockPorTipoDePintura(pintureria);
				break;
			case MOSTRAR_LATAS_PINTURA_MAS_BARATA_POR_TIPO:
				mostrarLatasDePinturaMasBarataPorTipoDePintura(pintureria);
				break;
			case MOSTRAR_RESUMEN_PINTURERIA:
				mostrarResumenPintureria(pintureria);
				break;
			case SALIR:
				break;

			}

		} while (opcion != MenuPrincipal.SALIR);

	}

	private static void agregarLataDePintura(Pintureria pintureria) {

		mostrarPorPantalla(
				"\nIngrese los datos de la lata de pintura que desea agregar, pediremos el codigo nuevamente si el ingresado nos arroja una lata que ya existe: ");

		int codigo = 0, stock = 0;
		String nombre = ingresarString("\nNombre:");
		double porcentajeDeGanancia = ingresarDouble("\nPorcentaje de ganancia: ");
		TipoDePintura tipoDePintura = null;
		do {
			codigo = ingresarNumeroEntero("\nCodigo: ");
			stock = ingresarNumeroEntero("\nStock: ");
			tipoDePintura = obtenerOpcionDeEnumParaTipoDePintura("\nIngresar tipo de pintura: ");

		} while (pintureria.obtenerLataDePinturaPorCodigo(codigo) != null && stock <= 0 && false);

		if (pintureria.agregarLataDePintura(codigo, nombre, porcentajeDeGanancia, tipoDePintura, stock) == true) {
			mostrarPorPantalla("\nSe agrego la lata de forma exitosa");
		} else {
			mostrarPorPantalla("\nNo se pudo agregar la lata, revise los datos ingresados");
		}

	}

	private static void venderLatasDePintura(Pintureria pintureria) {

		mostrarLatasDePintura(pintureria.obtenerLatasDePinturaOrdenadasPorNombreAscendente());

		int codigo = ingresarNumeroEntero("\nIngresar el codigo de la lata que desea vender"),
				cantidadDeLatas = ingresarNumeroEntero("\nAhora ingrese la cantidad");

		if (pintureria.hayStock(codigo, cantidadDeLatas) == true) {

			pintureria.venderLatasDePintura(codigo, cantidadDeLatas);
			mostrarPorPantalla("\nLa venta se ha realizado!");
		} else {
			mostrarPorPantalla("\nLa cantidad de latas supera las que hay en stock");
		}

	}

	private static void mostrarLatasDePinturaMasBarataPorTipoDePintura(Pintureria pintureria) {

		TipoDePintura tipoDePintura = obtenerOpcionDeEnumParaTipoDePintura(
				"\nIngrese el tipo de pintura que desea buscar: ");

		LataDePintura lataDePinturaObtenida = pintureria.obtenerLataDePinturaMasBarataPorTipo(tipoDePintura);

		if (lataDePinturaObtenida == null && tipoDePintura.equals(TipoDePintura.MATE)) {
			mostrarPorPantalla("\nPintura MATE mas barata: <pintura>");
		} else if (lataDePinturaObtenida == null && tipoDePintura.equals(TipoDePintura.SATINADA)) {
			mostrarPorPantalla("\nPintura SATINADA mas barata: <pintura>");
		} else if (lataDePinturaObtenida != null) {
			mostrarPorPantalla("\nPintura" + lataDePinturaObtenida.getTipoDePintura().toString()
					+ lataDePinturaObtenida.toString() + " mas barata ");
		}

	}

	private static void mostrarCantidadDeLatasEnStockPorTipoDePintura(Pintureria pintureria) {

		TipoDePintura tipoDePintura = obtenerOpcionDeEnumParaTipoDePintura("\nIngresar el tipo de pintura deseado: ");

		mostrarPorPantalla("\nPinturas " + tipoDePintura.toString() + ": "
				+ pintureria.obtenerCantidadDeLatasDePinturasEnStockPorTipo(tipoDePintura));

	}

	private static void mostrarResumenPintureria(Pintureria pintureria) {

		LataDePintura[] latasOrdenadas = pintureria.getLatasDePintura();
		LataDePintura aux = null;

		for (int i = 0; i < latasOrdenadas.length; i++) {
			for (int j = 0; j < latasOrdenadas.length - 1; j++) {
				if (latasOrdenadas[j] != null && latasOrdenadas[j + 1] != null
						&& (latasOrdenadas[j].getNombre().compareTo(latasOrdenadas[j + 1].getNombre()) > 0)) {
					aux = latasOrdenadas[j];
					latasOrdenadas[j] = latasOrdenadas[j + 1];
					latasOrdenadas[j + 1] = aux;
				}
			}

		}

		for (int i = 0; i < latasOrdenadas.length; i++) {
			if (latasOrdenadas[i] != null) {
				mostrarPorPantalla(
						latasOrdenadas[i].toStringParaResumen() + " Precio = $" + latasOrdenadas[i].obtenerPrecio());
			}
		}
		mostrarPorPantalla("\nLatas en stock ordenadas");
		mostrarLatasDePintura(latasOrdenadas);

	}

	private static void mostrarMenuPrincipal() {

		String menu = "";

		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += "\n" + (i + 1) + ") " + MenuPrincipal.values()[i];
		}

		mostrarPorPantalla(menu);
	}

	private static TipoDePintura obtenerOpcionDeEnumParaTipoDePintura(String mensaje) {

		int opcion = 2;

		for (int i = 0; i < TipoDePintura.values().length; i++) {

			mostrarPorPantalla("\n" + (i + 1) + " " + TipoDePintura.values()[i].toString());

		}

		String opcionDeTipoString = ingresarString(mensaje);

		if (opcionDeTipoString.toUpperCase().equals(TipoDePintura.SATINADA)) {
			opcion = 0;
		} else if (opcionDeTipoString.toUpperCase().equals(TipoDePintura.MATE)) {
			opcion = 1;
		}

		TipoDePintura tipoDePinrtura = TipoDePintura.values()[opcion - 1];

		return tipoDePinrtura;
	}

	private static MenuPrincipal obtenerOpcionDeEnumParaMenuPrincipal() {
		int opcion = 0;

		do {
			mostrarMenuPrincipal();
			opcion = ingresarNumeroEntero("\nIngrese opcion: ");
		} while (opcion < 1 || opcion > MenuPrincipal.values().length);

		MenuPrincipal menuPrincipal = MenuPrincipal.values()[opcion - 1];

		return menuPrincipal;
	}

	private static int ingresarNumeroEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return TECLADO.nextInt();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return TECLADO.next();
	}

	private static double ingresarDouble(String mensaje) {
		mostrarPorPantalla(mensaje);
		return TECLADO.nextDouble();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarLatasDePintura(LataDePintura[] latasDePintura) {
		for (int i = 0; i < latasDePintura.length; i++) {
			if (latasDePintura[i] != null) {
				mostrarPorPantalla(latasDePintura[i].toString());
			}
		}
	}
}
