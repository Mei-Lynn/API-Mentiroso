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
        if (partida == null || partida.estaLlena()) {
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
     * llamando - ID de la partida
     *
     * Envía la jugada anterior del jugador anterior al recibido o null si falla
     *
     * @return
     */
	@GetMapping("/anterior")
    public Jugada JugadaAnterior(
            @RequestParam(value = "gameID", defaultValue = "") String gameID,
            @RequestParam(value = "username", defaultValue = "") String name
    ) {
        // Localizar la id del juego mediante su ID
        if (name.isEmpty() || gameID.isEmpty()) {
            return null;
        } else {
            Partida myGame = partidas.get(UUID.fromString(name));

            // Encontrar al jugador dado
            ArrayList<Jugador> jugadores = myGame.getJugadores();
            Jugador target = myGame.findPlayerByUsername(name);

            if (target != null) {
                // Localizar a su anterior
                // y devolvemos su jugada anterior
                try {
                    return jugadores.get(jugadores.indexOf(target) - 1).getUltimaJugada();
                } catch (ArrayIndexOutOfBoundsException e) {
                    return jugadores.get(jugadores.size()).getUltimaJugada();
                }
            } else {
                return null;
            }
        }
    }

    /**
     * Endpoint 4: Subir mano Recibe... - ID de la partida - el nombre del
     * jugador - el nombre de la jugada - el primer numero de la jugada
     * (obligatorio) - el segundo numero de la jugada (opcional)
     *
     * devuelve un mensaje de texto con el resultado
     */
	@GetMapping("/subir")	
    public String subirMano(
            @RequestParam(value = "gameID", defaultValue = "") String gameID,
            @RequestParam(value = "username", defaultValue = "") String name,
            @RequestParam(value = "play", defaultValue = "") String play,
            @RequestParam(value = "n1", defaultValue = "") String n1,
            @RequestParam(value = "n2", defaultValue = "0") String n2
    ) {
        if (name.isEmpty() || gameID.isEmpty()) {
            return "Error de entrada";
        } else {
            Partida myGame = partidas.get(UUID.fromString(name));
            Jugador target = myGame.findPlayerByUsername(name);

            if (myGame.getJugadorActual() == target) {
                // Encontrar al jugador dado
                ArrayList<Integer> hand = target.getMano();

                Jugada jugada = new Jugada();
                try {
                    jugada.setPrimerNumero(Integer.parseInt(n1));
                    jugada.setSegundoNumero(Integer.parseInt(n2));
                } catch (NumberFormatException e) {
                    return "Error de entrada";
                }
                jugada.jugadaElegida(play);

                return "Mano subida";

            } else {
                return "No es el turno de este jugador";
            }
        }
        //return false;
    }
}
