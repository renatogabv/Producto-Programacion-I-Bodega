
package com.bodega.sistemabodega;

public class EntidadComercial {
    protected String documento;
    protected String nombre;
    protected String telefono;
    
    public EntidadComercial(String documento, String nombre){
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
        if (documento== null && documento.trim().isEmpty()){
            throw new IllegalArgumentException("Ingrese Datos Validos");
            
        } else {
            this.documento = documento;
        }
    }

    public void setNombre(String nombre) {
        if (nombre == null && nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingrese Datos Validos");
            
        }else {
            this.nombre = nombre;
            
        }
    }

    public void setTelefono(String telefono) {
        if (telefono ==null && telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingresar Datos Validos");
            
        } else {
            this.telefono = telefono;
        }
    }
    public boolean validarDocumento() {
        return documento != null && documento.trim().isEmpty();
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
