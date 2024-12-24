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
		// TODO: Escriba el codigo necesario para garantizar el correcto funcionamiento
		// del software. Para armar el menu, se debera utilizar el enum MenuPrincipal,
		// buscando llevar el codigo a ejecutarse (en cada caso del menu) a un metodo
		// apropiado (Ver los métodos incluídos a continuación).
		// Es necesario solicitar la cantidad de latas posibles de almacenar en una
		// pinturería.

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
		// TODO: El usuario debera ingresar los datos de una lata de pintura para que
		// sea agregada a las latas que posee la pintureria.
		// Cuando se agrega una lata, no debe tener el mismo codigo de una lata ya
		// existente. Es necesario validar el codigo que se ingresa. En caso existir en
		// la pintureria, una lata con el codigo ingresado, se debera mostrar un mensaje
		// apropiado y seguir solicitando el ingreso de un nuevo codigo, hasta obtener
		// un codigo no existente. Para los tipos de pintura se debera ingresar la
		// palabra MATE o SATINADA, sin importar si es en mayusculas o minusculas (o
		// mixto). El stock ingresado debe ser mayor a cero y se debe seguir solicitando
		// en caso de ingresar un valor invalido.
		// Si se agrega correctamente la lata de pintura a la pintureria, mostrar un
		// mensaje de exito, caso contrario, uno de error.

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
		// TODO: Se deberan mostrar las latas de pintura ordenadas por nombre
		// ascendente, que dispone la pintureria para
		// que el usuario pueda ingresar el codigo y la cantidad de latas que desea
		// vender. Solo se puede vender si tenemos en stock la cantidad de latas de
		// pintura ingresada.
		// En caso de ingresar un numero de latas de pintura a vender, mayor al stock de
		// esa lata de pintura,
		// mostrar un mensaje acorde y no procesar la venta. Si la cantidad es valida,
		// proceder a realizar la venta y mostrar un mensaje de exito.

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
		// TODO: Deberan mostrarse las latas de pintura mas baratas para cada tipo de
		// pintura (SATINADA o MATE) de la pintureria. Cabe destacaer que solo se puede
		// mostrar una lata por tipo de pintura. Si no existe al menos una lata para el
		// tipo, mostrar un mensaje apropiado.
		// Ejemplo: Pintura SATINADA mas barata: <pintura>
		// Ejemplo: Pintura MATE mas barata: <pintura>

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
		// TODO: Mostrar la cantidad de latas de pinturas satinadas y la cantidad de
		// latas de pinturas mate que tiene la pintureria.
		// Ejemplo: Pinturas SATINADAS: 10 - Pinturas MATE: 5

		TipoDePintura tipoDePintura = obtenerOpcionDeEnumParaTipoDePintura("\nIngresar el tipo de pintura deseado: ");

		mostrarPorPantalla("\nPinturas " + tipoDePintura.toString() + ": "
				+ pintureria.obtenerCantidadDeLatasDePinturasEnStockPorTipo(tipoDePintura));

	}

	private static void mostrarResumenPintureria(Pintureria pintureria) {
		// TODO: Se debera mostrar como resumen, las latas de pintura que tiene
		// actualmente la pintureria (con stock actualizado y precio), ordenadas por
		// nombre ascendente.
		// Ademas, debe mostrarse la cantidad de latas de pintura vendidas y el saldo
		// actual de la pintureria.

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

		// FALTA: (con stock actualizado y precio)

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
