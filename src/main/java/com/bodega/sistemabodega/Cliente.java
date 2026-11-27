package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;

public class Cliente extends EntidadComercial {
    
    private double fiado;
    
    public Cliente(String dni, String nombre) {
        super(dni, nombre);
        this.fiado = 0.0;
    }
    
    public Cliente(String dni, String nombre, String telefono) {
        super(dni, nombre, telefono);
        this.fiado = 0.0;
        
    }
    
    @Override
    public void setDocumento(String dni) {
        String limpio = (dni == null) ? null : dni.trim();
        if (!Validador.esDni(limpio)) {
            throw new IllegalArgumentException("DNI invalido, debe tener al menos 8 caracteres");
        } else {
            this.documento = limpio;
        }
    }
    
    public double getFiado() {
        return fiado;
    }
    
    public void setFiado(double fiado) {
        if (!Validador.esNoNegativo(fiado)) {
            throw new IllegalArgumentException("El Fiado no puede ser negativo");
        } else {
            this.fiado = Formato.redondearDosD(fiado);
        }
    }
    
    @Override
    public boolean validarDocumento() {
        return Validador.esDni(documento);
    }
    
    @Override
    public String toString() {
        return "Cliente{ "
                + "DNI = " + getDocumento()
                + ", nombre = " + getNombre()
                + ", telefono = " + (getTelefono() != null ? getTelefono() : "Sin teléfono")
                + ", fiado = " + Formato.moneda(fiado)
                + " }";
    }
}
