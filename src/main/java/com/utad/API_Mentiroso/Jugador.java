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
	private Jugador jugadorActual;
	private boolean aceptaJugadores;
	
	
	public Jugador(int vecesJugadas, String nombre, ArrayList<Integer> mano, Jugada ultimaJugada, Jugador jugadorActual,
			boolean aceptaJugadores) {
		super();
		this.vecesJugadas = vecesJugadas;
		this.nombre = nombre;
		this.mano = mano;
		this.ultimaJugada = ultimaJugada;
		this.jugadorActual = jugadorActual;
		this.aceptaJugadores = aceptaJugadores;
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


	public Jugador getJugadorActual() {
		return jugadorActual;
	}


	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}


	public boolean isAceptaJugadores() {
		return aceptaJugadores;
	}


	public void setAceptaJugadores(boolean aceptaJugadores) {
		this.aceptaJugadores = aceptaJugadores;
	}
	
	
	
	

}
