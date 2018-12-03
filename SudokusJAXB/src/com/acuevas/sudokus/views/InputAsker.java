package com.acuevas.sudokus.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Methods to ask the user input options
 *
 * @author mfontana
 */
public abstract class InputAsker {

	// TODO get the cadena String from view

	public static String pedirCadena(String texto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena = "";
		do {
			try {
				System.out.println(texto);
				cadena = br.readLine();
				if (cadena.equals("")) {
					System.out.println("You cannot leave this camp blank.");
				}
			} catch (IOException ex) {
				System.out.println("Input / Output error.");
			}
		} while (cadena.equals(""));
		return cadena;
	}

	public static double pedirDouble(String texto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double num = 0;
		boolean error;
		do {
			try {
				System.out.println(texto);
				num = Double.parseDouble(br.readLine());
				error = false;
			} catch (IOException ex) {
				System.out.println("Input / Output error.");
				error = true;
			} catch (NumberFormatException ex) {
				System.out.println("You must insert a number.");
				error = true;
			}
		} while (error);
		return num;
	}

	public static int pedirEntero(String texto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = 0;
		boolean error;
		do {
			try {
				System.out.println(texto);
				num = Integer.parseInt(br.readLine());
				error = false;
			} catch (IOException ex) {
				System.out.println("Input / Output error.");
				error = true;
			} catch (NumberFormatException ex) {
				System.out.println("You must insert an integer number.");
				error = true;
			}
		} while (error);
		return num;
	}

	// Método que indica si un String es un entero o no
	public static boolean esEntero(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	/**
	 * This method only allows the user to input yes or no
	 * 
	 * @param text
	 * @return
	 */
	public static boolean yesOrNo(String text) {
		boolean error = false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cadena = "";
		do {
			try {
				System.out.println(text);
				cadena = br.readLine();
				if (cadena.equals(""))
					System.out.println("You cannot leave this camp blank.");

				if (cadena.equalsIgnoreCase("yes") || cadena.equalsIgnoreCase("y"))
					return true;
				if (cadena.equalsIgnoreCase("no") || cadena.equalsIgnoreCase("n"))
					return false;
				System.out.println("you must insert (y)es or (n)o.");
				error = true;
			} catch (IOException ex) {
				System.out.println("Input / Output error.");
			}
		} while (error);
		return false;
	}

}