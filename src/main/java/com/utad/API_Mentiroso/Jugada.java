package com.utad.API_Mentiroso;

import java.util.ArrayList;

public class Jugada {
	private boolean esVerdad;
	private ArrayList<Integer> mano;
	private String nombre;
	private int primerNumero;
	private int segundoNumero;

	public Jugada() {
	}

	public boolean isEsVerdad() {
		return esVerdad;
	}

	public void setEsVerdad(boolean esVerdad) {
		this.esVerdad = esVerdad;
	}

	public ArrayList<Integer> getMano() {
		return mano;
	}

	public void setMano(ArrayList<Integer> mano) {
		this.mano = mano;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrimerNumero() {
		return primerNumero;
	}

	public void setPrimerNumero(int primerNumero) {
		this.primerNumero = primerNumero;
	}

	public int getSegundoNumero() {
		return segundoNumero;
	}

	public void setSegundoNumero(int segundoNumero) {
		this.segundoNumero = segundoNumero;
	}

	public Jugada jugadaElegida(String nombreJugada) {
		Jugada jugada = new Jugada();
		switch (nombreJugada) {
		case "Carta Alta":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setEsVerdad(comprobarCartaAlta(primerNumero));
			break;
		case "Pareja":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setEsVerdad(comprobarPareja(primerNumero));
			break;
		case "Trio":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setEsVerdad(comprobarTrio(primerNumero));
			break;
		case "Doble Pareja":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setSegundoNumero(segundoNumero);
			jugada.setEsVerdad(comprobarDoblePareja(primerNumero, segundoNumero));
			break;
		case "Full":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setSegundoNumero(segundoNumero);
			jugada.setEsVerdad(comprobarFull(primerNumero, segundoNumero));
			break;
		case "Poker":
			jugada.setNombre(nombreJugada);
			jugada.setPrimerNumero(primerNumero);
			jugada.setEsVerdad(comprobarPoker(primerNumero));
			break;
		}
		return jugada;

	}

	public boolean comprobarCartaAlta(int numero) {
		if (mano.contains(numero)) {
			this.esVerdad = true;
		} else
			this.esVerdad = false;
		return this.esVerdad;
	}

	public boolean comprobarPareja(int numero) {
		int contador = 0;
		for (Integer cartas : mano) {
			if (cartas == numero) {
				contador++;
			}
		}
		if (contador >= 2) {
			this.esVerdad = true;
		} else {
			this.esVerdad = false;
		}
		return this.esVerdad;
	}

	public boolean comprobarTrio(int numero) {
		int contador = 0;
		for (Integer cartas : mano) {
			if (cartas == numero) {
				contador++;
			}
		}
		if (contador >= 3) {
			this.esVerdad = true;
		} else {
			this.esVerdad = false;
		}
		return this.esVerdad;
	}

	public boolean comprobarDoblePareja(int numero1, int numero2) {
		int contadorNumero1 = 0;
		int contadorNumero2 = 0;
		for (Integer cartas : mano) {
			if (cartas == numero1) {
				contadorNumero1++;
			} else if (cartas == numero2) {
				contadorNumero2++;
			}
		}
		if (contadorNumero1 >= 2 && contadorNumero2 >= 2) {
			this.esVerdad = true;
		} else {
			this.esVerdad = false;
		}
		return this.esVerdad;
	}

	public boolean comprobarFull(int numero1, int numero2) {
		int contadorNumero1 = 0;
		int contadorNumero2 = 0;
		for (Integer cartas : mano) {
			if (cartas == numero1) {
				contadorNumero1++;
			} else if (cartas == numero2) {
				contadorNumero2++;
			}
		}
		if (contadorNumero1 == 2 && contadorNumero2 == 3 || contadorNumero1 == 3 && contadorNumero2 == 2) {
			this.esVerdad = true;
		} else {
			this.esVerdad = false;
		}
		return this.esVerdad;
	}

	public boolean comprobarPoker(int numero) {
		int contador = 0;
		for (Integer cartas : mano) {
			if (cartas == numero) {
				contador++;
			}
		}
		if (contador == 4) {
			this.esVerdad = true;
		} else {
			this.esVerdad = false;
		}
		return this.esVerdad;
	}
}
