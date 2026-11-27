/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bodega.sistemabodega.util;

/**
 *
 * @author Lerovix
 */
public final class Constante {
    
    //esto de aca es para que no se puedan cambiar los datos desde otro lado, sean serios y tampoco creen setters o getters
    private Constante(){}
        //Las constantes se deben colocar con mayusculas ej: yape -> YAPE (por si quieren crear más)
    
    
        //Constantes de impuestos
        public static final double IGV = 0.18;
        
        //codigos de la sunat
        public static final String SERIE_BOLETA = "B001";
        
        //Tipos de pago
        public static final String T_EFECTIVO = "EFECTIVO";
        public static final String T_TARJETA = "TARJETA"; //desconosco si lo usan si alguien lo ve innecesario lo borra
        public static final String T_YAPE = "YAPE";
        public static final String T_PLIN = "PLIN";
        
        //estados
        public static final String E_ACTIVO = "ACTIVO";
        public static final String E_INACTIVO = "INACTIVO";
        
    
    
    
    
    
    
    
    
    
}
