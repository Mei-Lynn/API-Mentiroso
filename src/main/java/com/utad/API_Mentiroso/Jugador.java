/**
 * @author Jorge Antequera Vega
 * @date 16 dic 2025
 */
package com.utad.API_Mentiroso;

import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable{
	
	private int vecesJugadas;
	private String nombre;
	private ArrayList<Integer> mano; 
	private Jugada ultimaJugada;
	
	
	
	public Jugador(int vecesJugadas, String nombre, ArrayList<Integer> mano, Jugada ultimaJugada) {
		super();
		this.vecesJugadas = vecesJugadas;
		this.nombre = nombre;
		this.mano = mano;
		this.ultimaJugada = ultimaJugada;
		
	}
	


	public Jugador(int vecesJugadas, String nombre) {
		super();
		this.vecesJugadas = vecesJugadas;
		this.nombre = nombre;
	}



	public Jugador(int vecesJugadas, String nombre, ArrayList<Integer> mano) {
		super();
		this.vecesJugadas = vecesJugadas;
		this.nombre = nombre;
		this.mano = mano;
	}

	public Jugador(String nombre) {
		super();
		this.vecesJugadas = 0;
		this.nombre = nombre;
	}



	public Jugador() {
		super();
	}


	public int getVecesJugadas() {
		return vecesJugadas;
	}


	public void setVecesJugadas(int vecesJugadas) {
		this.vecesJugadas = vecesJugadas;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ArrayList<Integer> getMano() {
		return mano;
	}


	public void setMano(ArrayList<Integer> mano) {
		this.mano = mano;
	}


	public Jugada getUltimaJugada() {
		return ultimaJugada;
	}


	public void setUltimaJugada(Jugada ultimaJugada) {
		this.ultimaJugada = ultimaJugada;
	}
	
	
	public boolean isUltimaJugadaEsVerdad() {
		return this.ultimaJugada.isEsVerdad();
	}

	public void setUltimaJugadaEsVerdad(boolean esVerdad) {
		this.ultimaJugada.setEsVerdad(esVerdad);
	}

	public ArrayList<Integer> getUltimaJugadaCartasJugadas() {
		return this.ultimaJugada.getCartasJugadas();
	}

	public void setUltimaJugadaCartasJugadas(ArrayList<Integer> cartasJugadas) {
		this.ultimaJugada.setCartasJugadas(cartasJugadas);
	}

	public String getUltimaJugadaNombre() {
		return this.ultimaJugada.getNombre();
	}

	public void setUltimaJugadaNombre(String nombre) {
		this.ultimaJugada.setNombre(nombre);
	}

	public int getUltimaJugadaPrimerNumero() {
		return this.ultimaJugada.getPrimerNumero();
	}

	public void setUltimaJugadaPrimerNumero(int primerNumero) {
		this.ultimaJugada.setPrimerNumero(primerNumero);
	}

	public int getUltimaJugadaSegundoNumero() {
		
		return this.ultimaJugada.getSegundoNumero();
	}

	public void setUltimaJugadaSegundoNumero(int segundoNumero) {
		
		this.ultimaJugada.setSegundoNumero(segundoNumero);
	}
	
	public void jugar(Jugada jugada) {
		this.ultimaJugada = jugada;
		this.vecesJugadas++;
	}
	
	
	

}
