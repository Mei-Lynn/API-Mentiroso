/**
 * @author Jorge Antequera Vega
 * @date 16 dic 2025
 */
package com.utad.API_Mentiroso;

import java.util.ArrayList;
import java.util.UUID;

public class Partida {
	private UUID idSala = UUID.randomUUID();
	private ArrayList<Integer> baraja = new ArrayList<>();
	private ArrayList<Jugador> jugadores = new ArrayList<>();
	private Jugador jugadorActual;
	private Boolean aceptaJugadores;

	public Partida() {
		llenarBaraja();
	}

	public Partida(UUID idSala, ArrayList<Jugador> jugadores, Jugador jugadorActual) {
		super();
		this.idSala = idSala;
		llenarBaraja();
		this.jugadores = jugadores;
		this.jugadorActual = jugadorActual;
		this.aceptaJugadores = true;
	}

	public Partida(Jugador jugador) {
		jugadores.add(jugador);
		jugadorActual = jugador;
		aceptaJugadores = true;
		llenarBaraja();
	}

	public Partida(UUID id) {
		this.idSala = id;
		this.aceptaJugadores = true;
		llenarBaraja();
	}

	private void llenarBaraja() {
		for (int f = 1; f <= 13; f++) {
			for (int i = 0; i < 4; i++) {
				baraja.add(f);
			}
		}
	}

	public UUID getIdSala() {
		return idSala;
	}

	public void setIdSala(UUID idSala) {
		this.idSala = idSala;
	}

	public ArrayList<Integer> getBaraja() {
		return baraja;
	}

	public void setBaraja(ArrayList<Integer> baraja) {
		this.baraja = baraja;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	public Boolean getAceptaJugadores() {
		return aceptaJugadores;
	}

	public void setAceptaJugadores(Boolean aceptaJugadores) {
		this.aceptaJugadores = aceptaJugadores;
	}

	public void anadirJugador(Jugador jugador) {
		jugadores.add(jugador);
	}

	public void eliminarJugador(Jugador jugador) {
		jugadores.remove(jugador);

	}

	public Jugador findPlayerByUsername(String usr) {
		for (Jugador jugador : jugadores) {
			if (jugador.getNombre().equals(usr)) {
				return jugador;
			}
		}
		return null;
	}

	public Boolean subirJugada(Jugador player, Jugada jugada) {
		jugadores.get(jugadores.indexOf(player)).jugar(jugada);
		try {
			jugadorActual = jugadores.get(jugadores.indexOf(player) + 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			jugadorActual = jugadores.get(0);
		}
		return true;
	}

	public ArrayList<Integer> pedirMano() {

		ArrayList<Integer> mano = new ArrayList<Integer>();
		if (baraja.size() > 4) {
			for (int i = 0; i < 5; i++) {
				int numeroAleatorio = (int) (Math.round(Math.random() * (baraja.size() - 1)));
				mano.set(i, baraja.get(numeroAleatorio));
				baraja.remove(mano.get(i));
			}
			return mano;
		} else {
			return null;
		}

	}

	public boolean estaLlena() {
		return (jugadores.size() >= 10);
	}
}
