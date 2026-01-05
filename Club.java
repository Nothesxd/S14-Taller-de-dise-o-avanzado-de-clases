package club.modelo;

import club.excepciones.*;
import java.util.ArrayList;
import java.util.List;

public class Club {

    private List<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public void afiliarSocio(Socio socio)
            throws OperacionNoPermitidaException, LimiteSociosVIPException {

        if (buscarSocioOpcional(socio.getCedula()) != null) {
            throw new OperacionNoPermitidaException("Ya existe un socio con esa cédula");
        }

        if (socio.esVIP() && contarSociosVIP() >= 3) {
            throw new LimiteSociosVIPException("No se permiten más de 3 socios VIP");
        }

        socios.add(socio);
    }

    private int contarSociosVIP() {
        return (int) socios.stream()
                .filter(Socio::esVIP)
                .count();
    }

    private Socio buscarSocioOpcional(String cedula) {
        return socios.stream()
                .filter(s -> s.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    private Socio buscarSocio(String cedula) throws SocioNoExisteException {
        Socio socio = buscarSocioOpcional(cedula);
        if (socio == null) {
            throw new SocioNoExisteException("No existe un socio con la cédula ingresada");
        }
        return socio;
    }

    public double totalConsumosSocio(String cedula) throws SocioNoExisteException {
        return buscarSocio(cedula).totalFacturas();
    }

    public boolean sePuedeEliminarSocio(String cedula)
            throws SocioNoExisteException, OperacionNoPermitidaException {

        Socio socio = buscarSocio(cedula);

        if (socio.esVIP()) {
            throw new OperacionNoPermitidaException("No se puede eliminar un socio VIP");
        }

        if (socio.tieneFacturasPendientes()) {
            throw new OperacionNoPermitidaException("El socio tiene facturas pendientes");
        }

        if (socio.getAutorizados().size() > 1) {
            throw new OperacionNoPermitidaException("El socio tiene más de un autorizado");
        }

        return true;
    }
}
