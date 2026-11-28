/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bodega.sistemabodega.util;

/**
 *
 * @author Lerovix
 */
public class Validador {

    //validador privado es para que nada pueda cambiar la información NO CAMBIAR
    private Validador() {
    }

@FunctionalInterface
    public interface Regla {
        boolean cumple(String valor);
    }

    public static boolean esVacio(String s) {
        return s == null || s.isBlank();
    }

    public static boolean esSoloDigitos(String s) {
        return s != null && s.matches("\\d+");
    }

    public static boolean esDni(String s) {
        return esSoloDigitos(s) && s.length() == 8;
    }

    public static boolean esRuc(String s) {
        return esSoloDigitos(s) && s.length() == 11;
    }

    public static boolean esTelefono(String s) {
        return esSoloDigitos(s) && s.length() >= 9;
    }

    // Formato: PREFIJO-CORRELATIVO (ej. CRN-00001)
    public static boolean esCodigoProducto(String s) {
        return s != null && s.matches("[A-Z]{2,5}-\\d{5}");
    }

    public static boolean esUnidadValida(String u) {
        if (esVacio(u)) {
            return false;
        }
        String norm = u.trim().toUpperCase();
        return norm.equals(Constante.U_UNIDAD)
                || norm.equals(Constante.U_KILO)
                || norm.equals(Constante.U_LITRO);
    }

    public static boolean esMotivoBajaValido(String m) {
        if (esVacio(m)) {
            return false;
        }
        String norm = m.trim().toUpperCase();
        return norm.equals(Constante.M_VENCIMIENTO)
                || norm.equals(Constante.M_MERMA)
                || norm.equals(Constante.M_ERROR);
    }

    public static boolean esPositivo(double v) {
        return v > 0;
    }

    public static boolean esNoNegativo(double v) {
        return v >= 0;
    }
}
