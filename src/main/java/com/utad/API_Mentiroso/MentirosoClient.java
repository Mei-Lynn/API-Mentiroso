package com.utad.API_Mentiroso;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.time.Duration;

public class MentirosoClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    private static final Scanner scanner = new Scanner(System.in);
    
    private static String currentSalaID = "";
    private static String myUsername = "";
    private static String myHand = "[]"; // Guardamos la mano actual para mostrarla

    public static void main(String[] args) {
        mostrarCabecera();
        
        System.out.println("1. 🆕 Crear nueva partida");
        System.out.println("2. 🤝 Unirse a partida existente");
        int opcion = leerEntero("Selecciona una opción: ");

        System.out.print("👤 Introduce tu nombre de usuario: ");
        myUsername = scanner.nextLine().trim();

        if (opcion == 1) {
            ejecutarAccion(BASE_URL + "/crear?username=" + myUsername, "crear");
        } else {
            System.out.print("🔑 Introduce el ID de la sala: ");
            currentSalaID = scanner.nextLine().trim();
            ejecutarAccion(BASE_URL + "/unirse?salaID=" + currentSalaID + "&username=" + myUsername, "unirse");
        }

        menuPrincipal();
    }

    private static void menuPrincipal() {
        boolean juegoActivo = true;
        while (juegoActivo) {
            System.out.println("\n===============================");
            System.out.println("   SALA: " + (currentSalaID.isEmpty() ? "Desconocida" : currentSalaID));
            System.out.println("   JUGADOR: " + myUsername);
            System.out.println("   TU MANO: " + myHand);
            System.out.println("===============================");
            System.out.println("1. 👀 Ver jugada anterior");
            System.out.println("2. 🃏 Realizar jugada (Subir mano)");
            System.out.println("3. 🚪 Salir");
            
            int accion = leerEntero("Acción: ");

            switch (accion) {
                case 1 -> verJugadaAnterior();
                case 2 -> subirMano();
                case 3 -> {
                    System.out.println("Saliendo del juego...");
                    juegoActivo = false;
                }
                default -> System.out.println("⚠️ Opción no válida.");
            }
        }
    }

    private static void ejecutarAccion(String url, String tipo) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url.replace(" ", "%20")))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String body = response.body();
                System.out.println("✅ Éxito: " + body);
                
                // Extraer SalaID y Mano de forma rústica (sin librerías externas de JSON)
                if (tipo.equals("crear")) {
                    currentSalaID = extraerValor(body, "salaID");
                }
                if (body.contains("mano")) {
                    myHand = extraerValor(body, "mano");
                }
            } else {
                System.err.println("❌ Error del servidor (Código " + response.statusCode() + ")");
            }
        } catch (Exception e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }
    }

    private static void verJugadaAnterior() {
        // Corregido: El endpoint en el controlador es /anterior
        String url = String.format("%s/anterior?gameID=%s&username=%s", 
                     BASE_URL, currentSalaID, myUsername);
        ejecutarAccion(url, "consulta");
    }

    private static void subirMano() {
        System.out.println("\n--- DETALLES DE LA JUGADA ---");
        System.out.print("¿Qué dices que lanzas? (Pareja, Trio, Poker...): ");
        String play = scanner.nextLine().trim();
        int n1 = leerEntero("Valor de la carta principal (1-13): ");
        int n2 = leerEntero("Segundo valor (Doble Pareja/Full, sino 0): ");

        // Corregido: El endpoint en el controlador es /subir
        String url = String.format("%s/subir?gameID=%s&username=%s&play=%s&n1=%d&n2=%d",
                     BASE_URL, currentSalaID, myUsername, play, n1, n2);

        ejecutarAccion(url, "subir");
    }

    // --- MÉTODOS AUXILIARES ---

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Por favor, introduce un número válido.");
            }
        }
    }

    private static String extraerValor(String json, String clave) {
        try {
            if (!json.contains(clave)) return "No encontrado";
            int inicio = json.indexOf(clave) + clave.length() + 3; // +3 por ": " o ":"
            int fin = json.indexOf(",", inicio);
            if (fin == -1) fin = json.indexOf("}", inicio);
            return json.substring(inicio, fin).replace("\"", "").trim();
        } catch (Exception e) {
            return "Error al parsear";
        }
    }

    private static void mostrarCabecera() {
        System.out.println("***************************************");
        System.out.println("* BIENVENIDO AL MENTIROSO        *");
        System.out.println("* U-TAD                  *");
        System.out.println("***************************************\n");
    }
}