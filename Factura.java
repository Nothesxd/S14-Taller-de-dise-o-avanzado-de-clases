package club.modelo;

public class Factura {

    private String concepto;
    private double valor;
    private String nombreConsumidor;

    public Factura(String concepto, double valor, String nombreConsumidor) {
        this.concepto = concepto;
        this.valor = valor;
        this.nombreConsumidor = nombreConsumidor;
    }

    public double getValor() {
        return valor;
    }

    public String getNombreConsumidor() {
        return nombreConsumidor;
    }
}
