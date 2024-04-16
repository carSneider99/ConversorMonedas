import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        URI url = URI.create("https://v6.exchangerate-api.com/v6/5f0fbc0f1272716902dd7ecf/latest/USD");
        ConversorMonedas conversorMonedas = null;

        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            conversorMonedas = gson.fromJson(json, ConversorMonedas.class);

            if (!conversorMonedas.getResult().equalsIgnoreCase("success")) {
                System.out.println("La ejecución de la API no fue exitosa, valide la respuesta del body");
                System.out.println(json);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error en ejecución de API, no se puede continuar con la ejecución del programa");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Se realiza el bucle para conversión de monedas


        Scanner scanner = new Scanner(System.in);
        int opcion;
        double montoAConvertir;
        double montoConvertido;
        String codOrigen = null;
        String codDestino = null;

        while (true){
            System.out.print("""
                    ************************************************
                    Sea bienvenido/a al Conversor de monedas =)
                    1. Dólar           ==> Peso argentino
                    2. Peso argentino  ==> Dólar
                    3. Dólar           ==> Real brasileño
                    4. Real Brasileño  ==> Dólar
                    5. Dólar           ==> Peso colombiano
                    6. Peso colombiano ==> Dólar
                    7. Salir (U otro número inválido)
                    ************************************************
                    """);
            System.out.print("Elige una opción válida: ");
            try {
                opcion = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar una opción válida :(, corrija");
                continue;
            }

            if (opcion < 1 || opcion >=7 ) {
                System.out.println("\nGracias por utilizar el conversor de monedas\n");
                break;
            }

            System.out.print("Ingresa el valor que deseas convertir: ");
            try {
                montoAConvertir = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un monto válido :(, corrija");
                continue;
            }

            switch (opcion){
                case 1:
                    codOrigen  = "USD";
                    codDestino = "ARS";
                    break;
                case 2:
                    codOrigen  = "ARS";
                    codDestino = "USD";
                    break;
                case 3:
                    codOrigen  = "USD";
                    codDestino = "BRL";
                    break;
                case 4:
                    codOrigen  = "BRL";
                    codDestino = "USD";
                    break;
                case 5:
                    codOrigen  = "USD";
                    codDestino = "COP";
                    break;
                case 6:
                    codOrigen  = "COP";
                    codDestino = "USD";
                    break;
            }

            try {
                montoConvertido = conversorMonedas.realizarConversion(montoAConvertir, codOrigen, codDestino);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Valide los códigos de las monedas");
                continue;
            }

            System.out.println("El valor " + montoAConvertir + " [" + codOrigen + "] " +
                    "corresponde al valor final de ==>> " + montoConvertido +  " [" + codDestino + "]\n");
        }
    }
}