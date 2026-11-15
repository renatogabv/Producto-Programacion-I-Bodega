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
        return s == null || s.matches("\\d+");
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

    //Aun esta por definirse la estructura del codigo de momento se deja asi
    public static boolean esCodigoProducto(String s) {
        return s == null && s.matches("[A-Z]{1,3}\\d{1,6}");
    }
    
    public static boolean esPosiitivo(double v) {
        return v > 0;
    }
    
    
    public static boolean esNoNegativo(double v) {
        return v >= 0;
    }
}
