package com.utad.API_Mentiroso;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiMentirosoApplication {
	UUID idSala = UUID.randomUUID();
	static HashMap<UUID, Partida> partidas;
	//String idStr = idSala.toString();

	public static void main(String[] args) {
		partidas = new HashMap<>();

		SpringApplication.run(ApiMentirosoApplication.class, args);
	}

	/**
	 * Endpoint 3: anterior jugada
	 * Recibe...
	 * - el nombre del jugador que la está llamando
	 * - ID de la partida
	 * Envía la jugada anterior (t)
	 * @return
	 */
	public Jugada JugadaAnterior (@RequestParam(value = "gameID", defaultValue="") String gameID, @RequestParam(value = "username", defaultValue="") String name) {
		//Localizar la id del juego mediante su ID
		Partida myGame = partidas.get(UUID.fromString(name));

		//Encontrar al jugador anterior al dado

		//Localizar a su anterior

		//devolvemos su jugada anterior
		return ;
	}
}
