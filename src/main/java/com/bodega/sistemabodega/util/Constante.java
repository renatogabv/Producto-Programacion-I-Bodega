
package com.bodega.sistemabodega.util;


public final class Constante {
    
    //esto de aca es para que no se puedan cambiar los datos desde otro lado, sean serios y tampoco creen setters o getters
    private Constante(){}
        //Las constantes se deben colocar con mayusculas ej: yape -> YAPE (por si quieren crear más)
    
         // Constantes de impuestos
         public static final double IGV = 0.18;

         // Codigos de la SUNAT
         public static final String SERIE_BOLETA = "B001";

         // Tipos de pago
         public static final String T_EFECTIVO = "EFECTIVO";
         public static final String T_TARJETA = "TARJETA";
         public static final String T_YAPE = "YAPE";
         public static final String T_PLIN = "PLIN";

         // Unidades predefinidas
         public static final String U_UNIDAD = "UND";
         public static final String U_KILO = "KG";
         public static final String U_LITRO = "LT";

         // Motivos de baja de producto
         public static final String M_VENCIMIENTO = "VENCIMIENTO";
         public static final String M_MERMA = "MERMA";
         public static final String M_ERROR = "ERROR_REGISTRO";

         // Estados
         public static final String E_ACTIVO = "ACTIVO";
         public static final String E_INACTIVO = "INACTIVO";
}
