package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Validador;

public class EntidadComercial {

    protected String documento;
    protected String nombre;
    protected String telefono;

    public EntidadComercial(String documento, String nombre) {
        setDocumento(documento);
        setNombre(nombre);
        this.telefono = null;

    }

    public EntidadComercial(String documento, String nombre, String telefono) {
        setDocumento(documento);
        setNombre(nombre);
        setTelefono(telefono);

    }

    public void setDocumento(String documento) {
        if (Validador.esVacio(documento)) {
            throw new IllegalArgumentException("El documento no puede quedar vacio");
        } else {
            this.documento = documento.trim();
        }
    }

    public void setNombre(String nombre) {
        if (Validador.esVacio(nombre)) {
            throw new IllegalArgumentException("El nombre no puede quedar vacio");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public void setTelefono(String telefono) {
        if (Validador.esVacio(telefono)) {
            this.telefono = null;
            return;
        }
        String limpio = telefono.trim();
        if (!Validador.esTelefono(limpio)) {
            throw new IllegalArgumentException("Teléfono inválido, debe tener 9 digitos al menos");
        }
        this.telefono = limpio;
    }

    public boolean validarDocumento() {
        return !Validador.esVacio(documento);
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
