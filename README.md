Clases Servidor:
- Main
	. Partidas [Map(UUID, Partida)]
	- Partida		
		* ID [UUID]
		* Baraja [List(Int)]
		* Jugadores [List(Jugador)] //Usada para calcular los turnos en orden de llegada\
		-> Jugador
			* Veces jugadas [Int]
			* Nombre/ID [String/UUID]
			* Mano [List(Int)]
			* UltimaJugada [Jugada]\
			  -> Jugada
				+ EsVerdad [Boolean]
				+ CartasJugadas [List(Int)] //Cartas reales jugadas
				+ Nombre [String] //Ej.: Pareja, Full...
				+ Numero1 [Int] // Para todas las manos
				+ Numero2 [Int] // Para full house y doble pareja\
  				-> Métodos:
	  				+ Getters y Setters de los atributos
   					+ jugadaElegida (String nombreJugada) : Jugada.
      				+ comprobarCartaAlata (int numero) : boolean.
          			+ comprobarPareja (int numero) : boolean.
            		+ comprobarTrio (int numero) : boolean.
           			+ comprobarDoblePareja (int numero1, int numero2) : boolean.
           			+ comprobarFull (int numero1, int numero2) : boolean.
 					+ comprobarPoker (int numero) : boolean.
		* JugadorActual [Jugador]
  		* AceptaJugadores [Boolean] //Atributo calculado

Cliente:
- Pantalla crear/unir
- Pantalla con mano

API:
- Endpoint 1 "Crear":
	- Provee:
		+ ID de la sala
		+ Mano para la partida
	- Recibe:
		+ Nombre de usuario

- Endpoint 2 "Unirse":
	- Provee:
		+ Si te ha metido o no a la sala
		+ Mano, en caso de poder unirte
	- Recibe:
		+ ID de la sala
		+ Nombre de usuario

- Endpoint 3 "Anterior":
	- Provee:
		+ La jugada anterior
	- Recibe:
		+ El jugador que la solicita
		+ La ID de la sala del jugador

- Endpoint 4 "Subir Mano":
	- Provee:
		+ Si la jugada es posible/valida (ej.: dentro de su turno)
	- Recibe:
		+ Jugada en cuestion
