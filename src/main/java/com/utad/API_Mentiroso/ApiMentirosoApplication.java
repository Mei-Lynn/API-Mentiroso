package com.utad.API_Mentiroso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiMentirosoApplication {

    UUID idSala = UUID.randomUUID();
    static HashMap<UUID, Partida> partidas;
    // String idStr = idSala.toString();

    public static void main(String[] args) {
        partidas = new HashMap<>();

        SpringApplication.run(ApiMentirosoApplication.class, args);
    }

    // Endpoint 1
    @GetMapping("/crear")
    public HashMap<String, Object> crearPartida(@RequestParam(value = "username") String username) {

        // Futura respuesta a devolver
        HashMap<String, Object> response = new HashMap<>();
        // Crear partida
        UUID salaID = UUID.randomUUID();
        Partida partida = new Partida(salaID);
        // Crear jugador
        Jugador jugador = new Jugador(username);

        // Repartir mano
        jugador.setMano(partida.pedirMano());

        // Añadir jugador a la partida
        partida.anadirJugador(jugador);

        // Guardar partida
        partidas.put(salaID, partida);

        // Respuesta
        response.put("salaID", salaID.toString());
        response.put("mano", jugador.getMano());

        return response;
    }

    // Endpoint 2
    @GetMapping("/unirse")
    public HashMap<String, Object> unirsePartida(@RequestParam(value = "salaID") String salaID,
            @RequestParam(value = "username") String username) {

        // Futura respuesta a devolver
        HashMap<String, Object> response = new HashMap<>();

        UUID id = UUID.fromString(salaID);
        Partida partida = partidas.get(id);

        // No existe la partida
        if (partida == null || !partida.estaLlena()) {
            response.put("ok", false);
            return response;
        }

        // Crear jugador
        Jugador jugador = new Jugador(username);
        ArrayList<Integer> newHand = partida.pedirMano();
        if (newHand == null) {
            jugador.setMano(newHand);
        }

        // Añadir a la partida
        partida.anadirJugador(jugador);

        response.put("ok", true);
        response.put("mano", jugador.getMano());

        return response;
    }

    /**
     * Endpoint 3: anterior jugada Recibe... - el nombre del jugador que la está
     * llamando - ID de la partida Envía la jugada anterior (t)
     *
     * @return
     */
    public Jugada JugadaAnterior(@RequestParam(value = "gameID", defaultValue = "") String gameID,
            @RequestParam(value = "username", defaultValue = "") String name) {
        // Localizar la id del juego mediante su ID
        if (name.isEmpty()) {
            return null;
        } else {
            Partida myGame = partidas.get(UUID.fromString(name));

            // Encontrar al jugador dado
			ArrayList<Jugador> jugadores = myGame.getJugadores();
			Jugador target = null;
			for (Jugador jugador : jugadores) {
				if (jugador.getNombre().equals(name)) {
					target = jugador;
				}
			}
			// Localizar a su anterior
			// y devolvemos su jugada anterior
            return jugadores.get(jugadores.indexOf(target) - 1).getUltimaJugada();
        }
    }
}
