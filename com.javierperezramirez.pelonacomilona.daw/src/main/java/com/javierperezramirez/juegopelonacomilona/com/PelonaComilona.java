package com.javierperezramirez.juegopelonacomilona.com;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase del juego de la pelona, contiene el juego de la pelona comiendose a
 * condenados
 * 
 * @author Javier Pérez Ramírez
 * @version 9.0
 *
 */
public class PelonaComilona {

	/**
	 * Clase MAIN que contiene el juego de la pelona comiendose a condenados hasta
	 * cumplir el numero objetivo
	 * 
	 * @param args Si no tiene argumentos, las teclas predeterminadas para
	 *             desplazarse son la A y la D. Si tiene un argumento, sera la tecla
	 *             de desplazamiento a la izquierda, si tiene dos argumentos, el
	 *             segundo sera la tecla de desplazamiento a la derecha.
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		byte tamaño;
		String[] tablero;
		String[] tumbas;
		String[] superior;

		char teclaIzda = 'a';
		char teclaDcha = 'd';
		
		/*-------------------------------------------------------------ARGUMENTOS FORMA 1------------------------------------------*/
		/*
		if (args.length < 1) {
			teclaIzda = 'a'; // Si los argumentos son 0 (menor que 1), asignamos la tecla 'a' para la
								// izquierda
		} else {
			teclaIzda = args[0].charAt(0); // Si no, asignamos el valor de args[0] para la izquierda
		}

		if (args.length < 2) {
			teclaDcha = 'd'; // Si los argumentos son menor que 2, asignamos la tecla 'd' para la derecha
		} else {
			teclaDcha = args[1].charAt(0); // Si no, asignamos el valor de args[1] para la derecha
		}
		*/
		
		/*---------------------------------------------------------------ARGUMENTOS FORMA 2--------------------------------------*/
		// PASO 2
		for (byte i=0;i<args.length;i++) {
			if (args[i].equals("-teclaIzda")) {
				teclaIzda = args[i+1].charAt(0);
			}
			if (args[i].equals("-teclaDcha")) {
				teclaDcha = args[i+1].charAt(0);
			}
		}
	
		//--------------------------------------------------------------------------------------------------------------------------

		System.out.println("###########  QUE VIENE LA PELONA  ###########\n");
		do {
			System.out.println("Dime el tamaño del tablero de juego (5 - 15)");
			tamaño = Byte.parseByte(sc.nextLine());
		} while ((tamaño < 5) || (tamaño > 15));

		System.out.println("A cuantas personas quieres comerte?");
		byte muertesObjetivo = Byte.parseByte(sc.nextLine());
		byte contadorMuertes = 0;
		short contadorTurnos = 0;
		byte contadorTrampa = 0;

		// Generamos el tablero original sin personajes
		tablero = Funciones.generaTablero(tamaño);
		// Generamos el tablero de tumbas
		tumbas = Funciones.generaTablero(tamaño);
		// Generamos el tablero superior
		superior = Funciones.generaTablero(tamaño);

		
		System.out.println("\nTablero inicial:\n\n" + Funciones.imprimeTableroSuperior2(tablero, tumbas) + "\n" + Funciones.imprimeTableroSuperior(tablero, tumbas) + "\n" + Funciones.imprimeTablero(tablero, tumbas));
		byte posicionCalavera = Funciones.colocaElemento(tablero, '@');
		byte posicionCondenado = Funciones.colocaElemento(tablero, '*');
		System.out.println("\nTablero tras colocar la pelona:\n\n" + Funciones.imprimeTableroSuperior2(tablero, tumbas) + "\n" + Funciones.imprimeTableroSuperior(tablero, tumbas) + "\n" + Funciones.imprimeTablero(tablero, tumbas));

		// Bucle del juego, bucle del juego, bucle del juego, bucle del juego, bucle del
		// juego, bucle del juego,

		for (contadorTurnos = 0; muertesObjetivo > contadorMuertes; contadorTurnos++) {

			// 1-Obtener la entrada, calculamos la posicion en tablero de la calavera MOVIMIENTO DE LA CALAVERA

			byte direccionMovimiento = 0;
			byte direccionMovimiento2 = 0;
			byte direccionCondenado = 0;

			System.out.println("A donde quieres moverte? (Izquierda - " + teclaIzda + " / Derecha - " + teclaDcha + ")");
			char direccion = sc.nextLine().charAt(0);

			if (direccion == teclaIzda) {
				direccionMovimiento2 = -1;
				if (posicionCalavera != 0) {
					direccionMovimiento = -1;
				} else {
					direccionMovimiento = (byte) (tablero.length - 1);
				}
			} else if (direccion == teclaDcha) {
				direccionMovimiento2 = 1;
				if (posicionCalavera != tablero.length - 1) {
					direccionMovimiento = 1;
				} else {
					direccionMovimiento = (byte) (posicionCalavera * -1);
				}
			}

			// 2-Calcular las consecuencias

			// 2.1-La calavera se mueve por el tablero con las teclas A y D MOVIMIENTO DE LA CALAVERA

			tablero[posicionCalavera] = "_";
			posicionCalavera += direccionMovimiento;
			tablero[posicionCalavera] = "@";

			// 2.2-Si la muerte pilla al condenado, el condenado la espicha y aparece una
			// tumba en esa casilla

			if (posicionCalavera == posicionCondenado) {
				contadorMuertes++;
				tumbas[posicionCalavera] = "+";
				if (contadorMuertes < muertesObjetivo) {
					posicionCondenado = Funciones.colocaElemento(tablero, '*');
				}
			}

			// 2.3-El condenado se mueve por el tablero aleatoriamente MOVIMIENTO DEL CONDENADO

			tablero[posicionCondenado] = "_";
			
			do {
				
				if (contadorMuertes<(muertesObjetivo/2)) {
					direccionCondenado = Funciones.posicionCondenado();
					if ((posicionCondenado == 0) && (direccionCondenado == -1)) {
						direccionCondenado = (byte) (tablero.length - 1);
					}
					if ((posicionCondenado == tablero.length - 1) && (direccionCondenado == 1)) {
						direccionCondenado = (byte) (posicionCondenado * -1);
					}
					
				} else {
					direccionCondenado = direccionMovimiento2; //Asignamos al condenado el mismo movimiento que la calavera (-1 ó 1)

					if ((tumbas[posicionCondenado]=="+")) { //Cuando el condenado pase por una cruz se queda un turno sin mover
						direccionCondenado = 0; //Dejamos al condenado sin movimiento
						contadorTrampa +=1; //Contador de turno sin mover para el condenado
						if (contadorTrampa == 2) {
							direccionCondenado = direccionMovimiento2; //Tras un turno sin mover volvemos a asignar al condenado el mismo movimiento que la calavera (-1 ó 1)
							if ((posicionCondenado == 0) && (direccionCondenado == -1)) {
								direccionCondenado = (byte) (tablero.length - 1);
							}
							if ((posicionCondenado == tablero.length - 1) && (direccionCondenado == 1)) {
								direccionCondenado = (byte) (posicionCondenado * -1);
							}
							contadorTrampa = 0;
						}
					} else {
						if ((posicionCondenado == 0) && (direccionCondenado == -1)) {
							direccionCondenado = (byte) (tablero.length - 1);
						}
						if ((posicionCondenado == tablero.length - 1) && (direccionCondenado == 1)) {
							direccionCondenado = (byte) (posicionCondenado * -1);
						}
					}
				}
					
				posicionCondenado += direccionCondenado; // Se mueve el condenado por el tablero
				
			} while (tablero[posicionCondenado] == "@"); //Repite el movimiento para que el condenado no caiga en la misma casilla que la calavera
			
			if (contadorMuertes < muertesObjetivo) {
				tablero[posicionCondenado] = "*";
			}

			// 3-Imprimir la siguiente imagen del tablero y los jugadores actuales

			System.out.println("\n\nMuertes: " + contadorMuertes + "/" + muertesObjetivo);
			System.out.println(Funciones.imprimeTableroSuperior2(tablero, tumbas) + "\n" + Funciones.imprimeTableroSuperior(tablero, tumbas) + "\n" + Funciones.imprimeTablero(tablero, tumbas));

		}

		System.out.println("\nFELICIDADES, HAS CONSEGUIDO EL OBJETIVO EN " + contadorTurnos + " TURNOS!!!");

	}

}
