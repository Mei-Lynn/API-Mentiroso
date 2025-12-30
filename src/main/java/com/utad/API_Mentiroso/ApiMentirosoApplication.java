package com.utad.API_Mentiroso;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiMentirosoApplication {
	
	@GetMapping("/crearPartida")
	public String[] crearPartida(@RequestParam(value = "nombreDeUsuario")String name) {
		String[] respuesta = new String[6];
		Jugador j1 = new Jugador(0, name);
		Partida partida = new Partida(j1);
		j1.setMano(partida.pedirMano());
		respuesta[0] = partida.getIdSala().toString();
		respuesta[1] = ""+ j1.getMano().get(0);
		respuesta[2] = ""+ j1.getMano().get(1);
		respuesta[3] = ""+ j1.getMano().get(2);
		respuesta[4] = ""+ j1.getMano().get(3);
		respuesta[5] = ""+ j1.getMano().get(4);
		return respuesta;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiMentirosoApplication.class, args);
	}

}
