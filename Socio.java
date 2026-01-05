package club.modelo;

import club.excepciones.*;
import java.util.ArrayList;
import java.util.List;

public class Socio {

    private String cedula;
    private String nombre;
    private double fondos;
    private TipoSuscripcion tipo;
    private List<Factura> facturas;
    private List<String> autorizados;

    public Socio(String cedula, String nombre, TipoSuscripcion tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.facturas = new ArrayList<>();
        this.autorizados = new ArrayList<>();
        this.fondos = tipo == TipoSuscripcion.VIP ? 100 : 50;
    }

    public String getCedula() {
        return cedula;
    }

    public boolean esVIP() {
        return tipo == TipoSuscripcion.VIP;
    }

    public List<String> getAutorizados() {
        return autorizados;
    }

    public void aumentarFondos(double monto) throws OperacionNoPermitidaException {
        double limite = tipo == TipoSuscripcion.VIP ? 5000 : 1000;
        if (fondos + monto > limite) {
            throw new OperacionNoPermitidaException("Se excede el límite máximo de fondos");
        }
        fondos += monto;
    }

    public void registrarConsumo(String concepto, double valor, String nombre)
            throws FondosInsuficientesException {

        if (fondos < valor) {
            throw new FondosInsuficientesException("Fondos insuficientes para realizar el consumo");
        }
        facturas.add(new Factura(concepto, valor, nombre));
    }

    public void pagarFactura(Factura factura) throws FondosInsuficientesException {
        if (fondos < factura.getValor()) {
            throw new FondosInsuficientesException("Fondos insuficientes para pagar la factura");
        }
        fondos -= factura.getValor();
        facturas.remove(factura);
    }

    public boolean tieneFacturasPendientes() {
        return !facturas.isEmpty();
    }

    public double totalFacturas() {
        return facturas.stream()
                .mapToDouble(Factura::getValor)
                .sum();
    }
}
