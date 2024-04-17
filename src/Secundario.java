import java.util.Scanner;

public class Secundario {
    public static void main(String[] args) {

        ConversorMonedas conversorMonedas = ConsultarTasasCambio.consultarTasasCambio();

        Scanner scanner = new Scanner(System.in);
        double montoAConvertir;
        double montoConvertido;
        String[] codigosMonedas = new String[2];

        // Se realiza el bucle para conversión de monedas
        while (true){
            System.out.print("""
                    ************************************************
                    Sea bienvenido/a al Conversor de monedas =)
                    ************************************************
                    """);

            System.out.print("Digite código de la moneda origen (USD, ARS, BRL, COL), si ingresa salir, finalizará el programa: ");
            codigosMonedas[0] = scanner.next();

            if (codigosMonedas[0].equalsIgnoreCase("salir"))
                break;

            System.out.print("Digite código de la moneda destino (USD, ARS, BRL, COL): ");
            codigosMonedas[1] = scanner.next();

            try {
                System.out.print("Ingresa el valor que deseas convertir: ");
                montoAConvertir = Double.parseDouble(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un monto válido :(, corrija");
                continue;
            }

            try {
                montoConvertido = conversorMonedas.realizarConversion(montoAConvertir, codigosMonedas[0], codigosMonedas[1]);
            } catch (Exception e) {
                System.out.println("Valide los códigos de las monedas\n" + e.getMessage());
                continue;
            }

            System.out.println("El valor " + montoAConvertir + " [" + codigosMonedas[0] + "] " +
                    "corresponde al valor final de ==>> " + montoConvertido +  " [" + codigosMonedas[1] + "]\n");
        }

        System.out.println("Gracias por utilizar el conversor de monedas");
    }
}