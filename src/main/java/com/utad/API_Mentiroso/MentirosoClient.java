package com.utad.API_Mentiroso;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class MentirosoClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    private static String currentSalaID = "";
    private static String myUsername = "";

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL MENTIROSO (U-TAD) ===");
        System.out.println("1. Crear nueva partida");
        System.out.println("2. Unirse a partida existente");
        int opcion = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduce tu nombre de usuario: ");
        myUsername = scanner.nextLine();

        if (opcion == 1) {
            crearPartida();
        } else {
            System.out.print("Introduce el ID de la sala: ");
            currentSalaID = scanner.nextLine();
            unirsePartida();
        }

        // Bucle de juego simplificado
        boolean juegoActivo = true;
        while (juegoActivo) {
            System.out.println("\n--- Menú de Juego ---");
            System.out.println("1. Ver jugada anterior");
            System.out.println("2. Realizar jugada (Subir mano)");
            System.out.println("3. Salir");
            int accion = Integer.parseInt(scanner.nextLine());

            switch (accion) {
                case 1 ->
                    verJugadaAnterior();
                case 2 ->
                    subirMano();
                case 3 ->
                    juegoActivo = false;
            }
        }
    }

    private static void crearPartida() {
        try {
            String url = BASE_URL + "/crear?username=" + myUsername;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Partida creada con éxito.");
            System.out.println("Respuesta del servidor: " + response.body());
            // Aquí podrías parsear el JSON para extraer el salaID automáticamente
            try {
                ObjectMapper mapper = new ObjectMapper();
                // Leemos el String como un árbol de nodos
                JsonNode rootNode = mapper.readTree(response.body());

                // Obtenemos el valor de salaID
                currentSalaID = rootNode.get("salaID").asString();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Error al crear partida: " + e.getMessage());
        }
    }

    private static void unirsePartida() {
        try {
            String url = BASE_URL + "/unirse?salaID=" + currentSalaID + "&username=" + myUsername;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Resultado de unión: " + response.body());
        } catch (Exception e) {
            System.err.println("Error al unirse: " + e.getMessage());
        }
    }

    private static void verJugadaAnterior() {
        try {
            // Nota: En tu controlador, el parámetro es 'gameID'
            String url = BASE_URL + "/anterior?gameID=" + currentSalaID + "&username=" + myUsername;
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(url);
            System.out.println("La última jugada fue: " + response.body());
        } catch (Exception e) {
            System.err.println("Error al consultar jugada: " + e.getMessage());
        }
    }

    private static void subirMano() {
        try {
            System.out.print("Tipo de jugada (Pareja, Trio, Poker...): ");
            String play = scanner.nextLine();
            System.out.print("Valor de la carta (1-13): ");
            String n1 = scanner.nextLine();
            System.out.print("Segundo valor (si es Doble Pareja o Full, sino 0): ");
            String n2 = scanner.nextLine();

            String url = String.format("%s/subir?gameID=%s&username=%s&play=%s&n1=%s&n2=%s",
                    BASE_URL, currentSalaID, myUsername, play.replace(" ", "%20"), n1, n2);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(url);
            System.out.println("Servidor dice: " + response.body());
        } catch (Exception e) {
            System.err.println("Error al subir mano: " + e.getMessage());
        }
    }
}
