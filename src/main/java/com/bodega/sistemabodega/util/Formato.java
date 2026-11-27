
package com.bodega.sistemabodega.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Formato {

    private Formato() {
    }

    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static String numBoleta(int correlativo) {
        return String.format("%s-%04d", Constante.SERIE_BOLETA, correlativo);
    }

    public static String codigoProducto(String prefijo, int correlativo) {
        return String.format("%s-%05d", prefijo.trim().toUpperCase(), correlativo);
    }

    public static String fecha(LocalDate f) {
        return f.format(FECHA);
    }

    public static String fechaHora(LocalDateTime f) {
        return f.format(FECHA_HORA);
    }

    public static String moneda(double monto) {
        return String.format("S/ %.2f", monto);
    }

    public static double redondearDosD(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
