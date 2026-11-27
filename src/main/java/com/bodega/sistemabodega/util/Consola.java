/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bodega.sistemabodega.util;

import java.util.Scanner;

/**
 *
 * @author Lerovix
 */
public final class Consola {

    private static final Scanner sc = new Scanner(System.in);
    private static boolean cerrado = false;

    private Consola() {
    }
public static int leerEntero(String msg, int min) {
        while (true) {
            try {
                System.out.print(msg);
                int v = Integer.parseInt(sc.nextLine().trim());
                if (v < min) {
                    System.out.println("Debe ser >= " + min);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido");
            }
        }
    }

    public static int leerEnteroEnRango(String msg, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El mínimo (" + min + ") no puede ser mayor que el máximo (" + max + ")");
        }
        while (true) {
            int v = leerEntero(msg, min);
            if (v <= max) {
                return v;
            }
            System.out.println("Debe ser <= " + max);
        }
    }

    public static double leerDecimal(String msg, double min) {
        while (true) {
            try {
                System.out.print(msg);
                double v = Double.parseDouble(sc.nextLine().trim());
                if (Double.isNaN(v) || Double.isInfinite(v)) {
                    System.out.println("Ingresa un número válido");
                    continue;
                }
                if (v < min) {
                    System.out.println("Debe ser >= " + min);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido");
            }
        }
    }

    public static String leerTexto(String msg) {
        while (true) {
            System.out.print(msg);
            String v = sc.nextLine().trim();
            if (!v.isEmpty()) {
                return v;
            }
            System.out.println("Este campo no puede quedar vacío");
        }
    }

    public static String leerTextoOpcional(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    public static String leerConValidacion(String msg, Validador.Regla regla, String error) {
        while (true) {
            System.out.print(msg);
            String v = sc.nextLine().trim();
            if (regla.cumple(v)) {
                return v;
            }
            System.out.println(error);
        }
    }

    public static String leerOpcion(String msg, String... opciones) {
        if (opciones == null || opciones.length == 0) {
            throw new IllegalArgumentException("Debe indicar al menos una opción válida");
        }
        while (true) {
            System.out.print(msg);
            String v = sc.nextLine().trim();
            for (String op : opciones) {
                if (op != null && op.equalsIgnoreCase(v)) {
                    return op.toUpperCase();
                }
            }
            System.out.println("Opción inválida. Válidas: " + String.join(", ", opciones));
        }
    }

    public static void cerrar() {
        if (!cerrado) {
            sc.close();
            cerrado = true;
        }
    }
}
