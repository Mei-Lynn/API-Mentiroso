package com.utad.API_Mentiroso;

import java.util.ArrayList;

public class Jugada {
	private boolean esVerdad;
	private ArrayList<Integer> cartasJugadas;
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

	public ArrayList<Integer> getCartasJugadas() {
		return cartasJugadas;
	}

	public void setCartasJugadas(ArrayList<Integer> cartasJugadas) {
		this.cartasJugadas = cartasJugadas;
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

}
