package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Consola;
import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;
import java.util.ArrayList;

public class SistemaBodega {

    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();

        System.out.println("BIENVENIDO AL MENU");

        int op;

        do {
            System.out.println("\nQUE DESEAS REALIZAR");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Consultar Lista de Productos");
            System.out.println("3. Salir del Menu");
            System.out.println("4. Registrar Proveedor");
            System.out.println("5. Registrar Venta y Emitir Boleta");
            System.out.println("6. Salir del Menu");

            op = Consola.leerEnteroEnRango("Selecciona una opcion: ", 1, 6);

            // Bombilla yo me encargo a partir de aqui :D
            // En esta parte se hace el proceso de registro
            switch (op) {

                case 1: {
                    String nombre = Consola.leerTexto("\nIngresa el Nombre del Producto: ");
                    String categoria = Consola.leerTexto("Ingresa la Categoria del Producto: ");
                    String codigo = Consola.leerConValidacion(
                            "Ingresa el Codigo del Producto (ej. A001): ",
                            c -> Validador.esCodigoProducto(c.toUpperCase()),
                            "Formato invalido. Usa 1 a 3 letras y 1 a 6 numeros (ej. A001)."
                    ).toUpperCase();

                    boolean duplicado = false;
                    for (Producto prod : productos) {
                        if (prod.getCodigo().equals(codigo)) {
                            duplicado = true;
                            break;
                        }
                    }
                    if (duplicado) {
                        System.out.println("Ya existe un producto con el codigo " + codigo);
                        break;
                    }

                    double preciocompra = Consola.leerDecimal("Ingresa el Precio de Compra del Producto: ", 0.0);
                    double precioventa = Consola.leerDecimal("Ingresa el Precio de Venta del Producto: ", 0.0);
                    int stock = Consola.leerEntero("Ingresa el Stock del Producto: ", 0);

                    Producto p = new Producto(codigo, nombre, categoria, preciocompra, precioventa, stock);
                    productos.add(p);
                    System.out.println("PRODUCTO REGISTRADO EXITOSAMENTE");
                    break;
                }

                case 2: {
                    if (productos.isEmpty()) {
                        System.out.println("\n No hay productos registrados: \n\n");
                    } else {
                        StringBuilder ls = new StringBuilder();
                        for (Producto producto : productos) {
                            ls      .append("Codigo: ").append(producto.getCodigo())
                                    .append("\nNombre: ").append(producto.getNombre())
                                    .append("\nCategoria: ").append(producto.getCategoria())
                                    .append("\nPrecio de compra: ").append(Formato.moneda(producto.getPrecioCompra()))
                                    .append("\nPrecio de venta: ").append(Formato.moneda(producto.getPrecioVenta()))
                                    .append("\nStock: ").append(producto.getStock());
                        }
                        System.out.println(ls);
                    }
                    break;
                }

                case 3: {
                    String dni = Consola.leerConValidacion(
                            "\nIngresa el DNI del Cliente: ",
                            Validador::esDni,
                            "DNI invalido. Debe contener exactamente 8 digitos."
                    );
                    String nombre = Consola.leerTexto("Ingresa el Nombre del Cliente: ");
                    String telefono = Consola.leerConValidacion(
                            "Ingresa el Telefono (opcional, Enter para omitir): ",
                            t -> Validador.esVacio(t) || Validador.esTelefono(t),
                            "Telefono invalido. Debe tener al menos 9 digitos."
                    );

                    Cliente c = Validador.esVacio(telefono)
                            ? new Cliente(dni, nombre)
                            : new Cliente(dni, nombre, telefono);
                    clientes.add(c);
                    System.out.println("CLIENTE REGISTRADO: " + c);
                    break;
                }

                case 4: {
                    String ruc = Consola.leerConValidacion(
                            "\nIngresa el RUC del Proveedor: ",
                            Validador::esRuc,
                            "RUC invalido. Debe contener exactamente 11 digitos."
                    );
                    String razonSocial = Consola.leerTexto("Ingresa la Razon Social: ");
                    String rubro = Consola.leerTexto("Ingresa el Rubro: ");
                    String telefono = Consola.leerConValidacion(
                            "Ingresa el Telefono (opcional, Enter para omitir): ",
                            t -> Validador.esVacio(t) || Validador.esTelefono(t),
                            "Telefono invalido. Debe tener al menos 9 digitos."
                    );

                    Proveedor prov = Validador.esVacio(telefono)
                            ? new Proveedor(ruc, razonSocial, rubro)
                            : new Proveedor(ruc, razonSocial, telefono, rubro);
                    proveedores.add(prov);
                    System.out.println("PROVEEDOR REGISTRADO: " + prov);
                    break;
                }

                case 5: {
                    if (productos.isEmpty() || clientes.isEmpty()) {
                        System.out.println("Debes tener al menos un cliente y un producto registrado para vender.");
                        break;
                    }

                    String dniBuscar = Consola.leerConValidacion(
                            "\nIngresa el DNI del Cliente: ",
                            Validador::esDni,
                            "DNI invalido (8 digitos)."
                    );
                    Cliente clienteVenta = null;
                    for (Cliente c : clientes) {
                        if (c.getDocumento().equals(dniBuscar)) {
                            clienteVenta = c;
                            break;
                        }
                    }
                    if (clienteVenta == null) {
                        System.out.println("Cliente no encontrado.");
                        break;
                    }

                    Venta venta = new Venta(clienteVenta);
                    String seguir;
                    do {
                        String codBuscar = Consola.leerTexto("Codigo de producto a vender: ").toUpperCase();
                        Producto prodVenta = null;
                        for (Producto p : productos) {
                            if (p.getCodigo().equals(codBuscar)) {
                                prodVenta = p;
                                break;
                            }
                        }

                        if (prodVenta == null) {
                            System.out.println("Producto no encontrado.");
                        } else if (prodVenta.getStock() == 0) {
                            System.out.println("Producto sin stock disponible.");
                        } else {
                            int cant = Consola.leerEnteroEnRango(
                                    "Cantidad (stock disponible: " + prodVenta.getStock() + "): ",
                                    1,
                                    prodVenta.getStock()
                            );
                            if (venta.agregarDetalle(prodVenta, cant)) {
                                System.out.println("Producto agregado. Total parcial: " + Formato.moneda(venta.getTotal()));
                            }
                        }
                        seguir = Consola.leerOpcion("¿Agregar otro producto? (S/N): ", "S", "N");
                    } while (seguir.equals("S"));

                    if (venta.getDetalles().isEmpty()) {
                        System.out.println("Venta cancelada");
                        break;
                    }

                    String tipoPago = Consola.leerOpcion(
                            "Tipo de pago: ",
                            Constante.T_EFECTIVO,
                            Constante.T_TARJETA,
                            Constante.T_YAPE,
                            Constante.T_PLIN
                    );
                    venta.setTipopago(tipoPago);

                    double montoPagado = Consola.leerDecimal(
                            "Total a pagar " + Formato.moneda(venta.getTotal()) + " - Ingresa monto recibido: ",
                            venta.getTotal()
                    );
                    venta.registrarPago(montoPagado);
                    ventas.add(venta);

                    Boleta boleta = new Boleta(venta);
                    boleta.imprimirBoleta();
                    break;
                }

                case 6:
                    System.out.println("Se cerro el programa");
                    break;
            }

        } while (op != 6);
        Consola.cerrar();
    }
}

// Ya acabe mano :V
// Diogo, no lo toques porfavor, esta funcionando :v
//UPDATE: No vi el silabo y  tuve que cambiar el JOptionPane por consola
