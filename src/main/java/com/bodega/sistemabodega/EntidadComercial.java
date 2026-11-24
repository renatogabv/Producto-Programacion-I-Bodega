
package com.bodega.sistemabodega;

public class EntidadComercial {
    protected String documento;
    protected String nombre;
    protected String telefono;
    
    public EntidadComercial(String documento, String nombre){
        setDocumento(documento);
        setNombre(nombre);
            
    }
    public EntidadComercial(String documento, String nombre, String telefono) {
        setDocumento(documento);
        setNombre(nombre);
        setTelefono(telefono);
        
    }

    public void setDocumento(String documento) {
        if (documento== "" && documento.isEmpty() ){
            throw new IllegalArgumentException("Ingrese Datos Validos");
            
        } else {
            this.documento = documento;
        }
    }

    public void setNombre(String nombre) {
        if (nombre == "" && nombre.isEmpty()) {
            throw new IllegalArgumentException("Ingrese Datos Validos");
            
        }else {
            this.nombre = nombre;
            
        }
    }

    public void setTelefono(String telefono) {
        if (telefono =="" && telefono.isEmpty()) {
            throw new IllegalArgumentException("Ingresar Datos Validos");
            
        } else {
            this.telefono = telefono;
        }
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
//Esta es la parte de la herencia
/*Cualquier cosa lo editan si algo anda mal
ademas tengan en cuenta que es la primera version
*/