package club.app;

import club.modelo.*;
import club.excepciones.*;

public class Main {

    public static void main(String[] args) {

        Club club = new Club();

        try {
            Socio socio = new Socio("0101", "Juan Pérez", TipoSuscripcion.REGULAR);
            club.afiliarSocio(socio);

            socio.aumentarFondos(300);
            socio.registrarConsumo("Piscina", 50, "Juan Pérez");

            System.out.println("Total de consumos: " +
                    club.totalConsumosSocio("0101"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
