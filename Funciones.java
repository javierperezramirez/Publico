package com.javierperezramirez.juegopelonacomilona.com;

import java.util.Random;

public class Funciones {

	/**
	 * funcion que dado un tamaño, crea un array de ese tamaño, con todas las
	 * posiciones rellenas con el string "_"
	 * 
	 * @param tamaño tamaño del array
	 * @return null si el tamaño no esta entre 5 y 15, un array del tamaño indicado
	 *         con todas sus posiciones rellenas con "_" en caso contrario
	 */
	public static String[] generaTablero(byte tamaño) {

		if (tamaño < 5 || tamaño > 15) {
			return null;
		}

		String[] ret = new String[tamaño];

		for (byte i = 0; i < tamaño; i++) {
			ret[i] = "_";
		}

		return ret;

	}
	
	public static String[] generaTableroSuperior(byte tamaño) {

		if (tamaño < 5 || tamaño > 15) {
			return null;
		}

		String[] ret = new String[tamaño];

		for (byte i = 0; i < tamaño; i++) {
			ret[i] = " ";
		}

		return ret;

	}

	/**
	 * Imprime el tablero[] en un string
	 * 
	 * @param tablero
	 * @return
	 */
	public static String imprimeTablero(String[] tablero, String[] tumbas) {

		String ret = "| ";

		for (byte i = 0; i < tablero.length - 1; i++) {
			if (tablero[i].equals("_")) {
				ret += tumbas[i] + "\t";
			} else {
				ret += tablero[i] + "\t";
			}
		}
		if (tablero.length < 11) {
			if (tablero[tablero.length - 1].equals("_")) {
				ret += tumbas[tablero.length - 1] + " |\n| ";
			} else {
				ret += tablero[tablero.length - 1] + " |\n| ";
			}
		} else {
			if (tablero[tablero.length - 1].equals("_")) {
				ret += tumbas[tablero.length - 1] + "  |\n| ";
			} else {
				ret += tablero[tablero.length - 1] + "  |\n| ";
			}
		}

		for (byte i = 0; i < tablero.length - 1; i++) {
			ret += i + "\t";
		}

		return ret + (tablero.length - 1) + " |";

	}
	
	public static String imprimeTableroSuperior(String[] tablero, String[] tumbas) {
		
		String ret = "| ";
		
		for (byte i = 0; i < tablero.length; i++) {
			if (i<tablero.length-1) {
				if (tablero[i].equals("_")) {
					ret += (" " + "\t");
				}
				if (tablero[i].equals("*")) {
					ret += ("*" + "\t");
				}
				if (tablero[i].equals("@")) {
					ret += ("@" + "\t");
				}
		
			} else {
				if (tablero[i].equals("_")) {
					ret += (" " + "  |");
				}
				if (tablero[i].equals("*")) {
					ret += ("*" + "  |");
				}
				if (tablero[i].equals("@")) {
					ret += ("@" + "  |");
				}
		
			}

		}
		
		return ret;
	}
	
	public static String imprimeTableroSuperior2(String[] tablero, String[] tumbas) {
		
		String ret = "| ";
		
		for (byte i = 0; i < tablero.length; i++) {
			if (i<tablero.length-1) {
				if (tablero[i].equals("_")) {
					ret += (" " + "\t");
				}
				if (tablero[i].equals("*")) {
					ret += (" " + "\t");
				}
				if (tablero[i].equals("@")) {
					ret += ("@" + "\t");
				}
		
			} else {
				if (tablero[i].equals("_")) {
					ret += (" " + "  |");
				}
				if (tablero[i].equals("*")) {
					ret += (" " + "  |");
				}
				if (tablero[i].equals("@")) {
					ret += ("@" + "  |");
				}
		
			}

		}
		
		return ret;
	}

	/**
	 * sortea una posicion aleatoria del array (entre 0 y tablero.length-1)
	 * comprueba que en esa posicion hay "_", si no es asi, sortea otra vez la
	 * posicion hasta que sea posible
	 * 
	 * @param tablero
	 * @param elemento
	 */
	public static byte colocaElemento(String[] tablero, char elemento) {

		Random rd = new Random();
		byte aleatorio = (byte) rd.nextInt(tablero.length);

		while (!tablero[aleatorio].equals("_")) {
			aleatorio = (byte) rd.nextInt(tablero.length);
		}
		tablero[aleatorio] = "" + elemento;
		return aleatorio;
	}

	public static byte posicionCondenado() {

		Random rd = new Random();
		byte ret = 0;

		do {
			ret = (byte) rd.nextInt(-1, 2);
		} while (ret == 0);

		return ret;
	}
	
	public static byte tableroCondenado (byte posicion, byte direccion, byte tablero) {
		
		byte ret = 0;
		
		if ((posicion == 0) && (direccion == -1)) {
			ret = (byte) (tablero - 1);
		}
		if ((posicion == tablero - 1) && (direccion == 1)) {
			ret = (byte) (tablero * -1);
		}
		
		return ret;
		
	}

}
