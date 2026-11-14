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

}
