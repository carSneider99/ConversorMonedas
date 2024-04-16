import java.util.HashMap;
import java.util.Map;

public class ConversorMonedas {

    private String result;
    private Map<String, Double> conversion_rates = new HashMap<String, Double>();

    public String getResult() {
        return result;
    }

    public double realizarConversion (double monto, String codigoMonedaOrigen, String codigoMonedaDestino) throws Exception {
        if (!conversion_rates.containsKey(codigoMonedaOrigen))
            throw new Exception("Verifique el código de la moneda origen : (" + codigoMonedaOrigen + "), es inválido");

        if (!conversion_rates.containsKey(codigoMonedaDestino))
            throw new Exception("Verifique el código de la moneda destino : (" + codigoMonedaDestino + "), es inválido");

        return monto * conversion_rates.get(codigoMonedaDestino) / conversion_rates.get(codigoMonedaOrigen);
    }
}