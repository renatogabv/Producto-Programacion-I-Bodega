
package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;
import java.util.HashSet;
import java.util.Set;

public class Categoria {
    
 private String nombre;
    private String codigo; // Prefijo ej. CRN
    private String estado;
    private int contadorProductos;

    public Categoria(String nombre) {
        this(nombre, new HashSet<>());
    }

    public Categoria(String nombre, Set<String> codigosExistentes) {
        setNombre(nombre);
        this.codigo = generarCodigoUnico(this.nombre, codigosExistentes);
        this.estado = Constante.E_ACTIVO;
        this.contadorProductos = 0;
    }

    private static String generarCodigoUnico(String nombre, Set<String> codigosExistentes) {
        String limpio = nombre.toUpperCase().replaceAll("[^A-Z]", "");
        String consonantes = limpio.replaceAll("[AEIOU]", "");
        String base;

        if (consonantes.length() >= 3) {
            base = consonantes.substring(0, 3);
        } else if (limpio.length() >= 3) {
            base = limpio.substring(0, 3);
        } else if (!limpio.isEmpty()) {
            base = String.format("%-3s", limpio).replace(' ', 'X');
        } else {
            base = "CAT";
        }

        String candidato = base;
        int sufijo = 1;
        while (codigosExistentes != null && codigosExistentes.contains(candidato)) {
            String pref = base.substring(0, Math.min(2, base.length()));
            candidato = pref + sufijo;
            sufijo++;
        }
        return candidato;
    }

    public String generarSiguienteCodigoProducto() {
        contadorProductos++;
        return Formato.codigoProducto(this.codigo, this.contadorProductos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (Validador.esVacio(nombre)) {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void desactivar() {
        this.estado = Constante.E_INACTIVO;
    }

    public void activar() {
        this.estado = Constante.E_ACTIVO;
    }

    public boolean estaActiva() {
        return Constante.E_ACTIVO.equals(this.estado);
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " (" + estado + ")";
    }   
    
}
