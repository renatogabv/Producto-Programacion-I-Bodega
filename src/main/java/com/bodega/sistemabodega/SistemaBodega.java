package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Consola;
import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SistemaBodega {

public static void main(String[] args) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        ArrayList<Venta> ventas = new ArrayList<>();
        ArrayList<Abastecimiento> abastecimientos = new ArrayList<>();
        ArrayList<BajaProducto> bajas = new ArrayList<>();

        // Categorias iniciales de ejemplo
        Set<String> codigosCat = new HashSet<>();
        Categoria catComida = new Categoria("Comida", codigosCat);
        codigosCat.add(catComida.getCodigo());
        categorias.add(catComida);

        System.out.println("BIENVENIDO AL SISTEMA DE BODEGA");

        int op;
        do {
            System.out.println("\nQUE DESEAS REALIZAR");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Consultar Lista de Productos");
            System.out.println("3. Registrar Cliente");
            System.out.println("4. Registrar Proveedor");
            System.out.println("5. Registrar Venta y Emitir Boleta");
            System.out.println("6. Gestionar Categorias (Crear / Listar / Eliminar)");
            System.out.println("7. Reabastecer Stock (Asociar Proveedor)");
            System.out.println("8. Dar de Baja Producto (con Motivo)");
            System.out.println("9. Consultar Historial de Ventas");
            System.out.println("10. Salir del Menu");

            op = Consola.leerEnteroEnRango("Selecciona una opcion: ", 1, 10);

            switch (op) {
                case 1: {
                    ArrayList<Categoria> activas = new ArrayList<>();
                    for (Categoria cat : categorias) {
                        if (cat.estaActiva()) {
                            activas.add(cat);
                        }
                    }
                    if (activas.isEmpty()) {
                        System.out.println("No hay categorias activas. Crea una primero en la opcion 6.");
                        break;
                    }

                    System.out.println("\nSelecciona una categoria:");
                    for (int i = 0; i < activas.size(); i++) {
                        System.out.println((i + 1) + ". " + activas.get(i));
                    }
                    int idxCat = Consola.leerEnteroEnRango("Opcion de categoria: ", 1, activas.size()) - 1;
                    Categoria catSel = activas.get(idxCat);

                    String nombre = Consola.leerTexto("Ingresa el Nombre del Producto: ");
                    String unidad = Consola.leerOpcion(
                            "Selecciona Unidad (" + Constante.U_UNIDAD + ", " + Constante.U_KILO + ", " + Constante.U_LITRO + "): ",
                            Constante.U_UNIDAD,
                            Constante.U_KILO,
                            Constante.U_LITRO
                    );
                    double preciocompra = Consola.leerDecimal("Ingresa el Precio de Compra: ", 0.0);
                    double precioventa = Consola.leerDecimal("Ingresa el Precio de Venta: ", 0.0);
                    int stock = Consola.leerEntero("Ingresa el Stock inicial: ", 0);

                    Producto p = new Producto(catSel, nombre, unidad, preciocompra, precioventa, stock);
                    productos.add(p);
                    System.out.println("PRODUCTO REGISTRADO con codigo: " + p.getCodigo());
                    break;
                }

                case 2: {
                    if (productos.isEmpty()) {
                        System.out.println("\nNo hay productos registrados.\n");
                    } else {
                        StringBuilder ls = new StringBuilder();
                        for (Producto producto : productos) {
                            ls.append("\n----------------------\n")
                              .append("Codigo: ").append(producto.getCodigo())
                              .append("\nNombre: ").append(producto.getNombre())
                              .append("\nCategoria: ").append(producto.getNombreCategoria())
                              .append("\nUnidad: ").append(producto.getUnidad())
                              .append("\nPrecio de compra: ").append(Formato.moneda(producto.getPrecioCompra()))
                              .append("\nPrecio de venta: ").append(Formato.moneda(producto.getPrecioVenta()))
                              .append("\nStock: ").append(producto.getStock()).append(" ").append(producto.getUnidad())
                              .append("\n----------------------\n");
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
                                    "Cantidad en " + prodVenta.getUnidad() + " (stock: " + prodVenta.getStock() + "): ",
                                    1,
                                    prodVenta.getStock()
                            );
                            if (venta.agregarDetalle(prodVenta, cant)) {
                                System.out.println("Producto agregado. Total parcial: " + Formato.moneda(venta.getTotal()));
                            }
                        }
                        seguir = Consola.leerOpcion("Agregar otro producto? (S/N): ", "S", "N");
                    } while (seguir.equals("S"));

                    if (venta.getDetalles().isEmpty()) {
                        System.out.println("Venta cancelada.");
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

                case 6: {
                    System.out.println("\n1. Crear categoria");
                    System.out.println("2. Listar categorias");
                    System.out.println("3. Eliminar categoria (independiente de productos)");
                    int subOp = Consola.leerEnteroEnRango("Elige accion: ", 1, 3);

                    if (subOp == 1) {
                        String nomCat = Consola.leerTexto("Nombre de la nueva categoria: ");
                        Categoria nueva = new Categoria(nomCat, codigosCat);
                        codigosCat.add(nueva.getCodigo());
                        categorias.add(nueva);
                        System.out.println("Categoria creada: " + nueva);
                    } else if (subOp == 2) {
                        if (categorias.isEmpty()) {
                            System.out.println("No hay categorias.");
                        } else {
                            for (int i = 0; i < categorias.size(); i++) {
                                System.out.println((i + 1) + ". " + categorias.get(i));
                            }
                        }
                    } else {
                        if (categorias.isEmpty()) {
                            System.out.println("No hay categorias para eliminar.");
                        } else {
                            for (int i = 0; i < categorias.size(); i++) {
                                System.out.println((i + 1) + ". " + categorias.get(i));
                            }
                            int idxDel = Consola.leerEnteroEnRango("Numero de categoria a eliminar: ", 1, categorias.size()) - 1;
                            Categoria eliminada = categorias.remove(idxDel);
                            for (Producto p : productos) {
                                if (p.getCategoria() == eliminada) {
                                    p.desvincularCategoria();
                                }
                            }
                            System.out.println("Categoria eliminada (" + eliminada.getNombre() + "). Los productos mantienen su nombre de categoria y codigo.");
                        }
                    }
                    break;
                }

                case 7: {
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    String codProd = Consola.leerTexto("\nCodigo del producto a reabastecer: ").toUpperCase();
                    Producto prodSel = null;
                    for (Producto p : productos) {
                        if (p.getCodigo().equals(codProd)) {
                            prodSel = p;
                            break;
                        }
                    }
                    if (prodSel == null) {
                        System.out.println("Producto no encontrado.");
                        break;
                    }

                    Proveedor provSel = null;
                    if (!proveedores.isEmpty()) {
                        System.out.println("Selecciona proveedor:");
                        for (int i = 0; i < proveedores.size(); i++) {
                            System.out.println((i + 1) + ". " + proveedores.get(i));
                        }
                        int idxProv = Consola.leerEnteroEnRango("Proveedor: ", 1, proveedores.size()) - 1;
                        provSel = proveedores.get(idxProv);
                    } else {
                        System.out.println("No hay proveedores registrados aun; se sumara stock sin registro de proveedor.");
                    }

                    int cantSumar = Consola.leerEntero("Cantidad a agregar (" + prodSel.getUnidad() + "): ", 1);
                    double costoUnit = Consola.leerDecimal("Nuevo costo unitario (actual " + Formato.moneda(prodSel.getPrecioCompra()) + "): ", 0.0);

                    if (provSel != null) {
                        Abastecimiento ab = new Abastecimiento(provSel, prodSel, cantSumar, costoUnit);
                        abastecimientos.add(ab);
                        System.out.println("Abastecimiento registrado: " + ab);
                    } else {
                        prodSel.aumentarStock(cantSumar);
                        prodSel.setPrecioCompra(costoUnit);
                        System.out.println("Stock actualizado. Nuevo stock: " + prodSel.getStock() + " " + prodSel.getUnidad());
                    }
                    break;
                }

                case 8: {
                    if (productos.isEmpty()) {
                        System.out.println("No hay productos registrados.");
                        break;
                    }
                    String codBaja = Consola.leerTexto("\nCodigo del producto a dar de baja: ").toUpperCase();
                    Producto prodBaja = null;
                    for (Producto p : productos) {
                        if (p.getCodigo().equals(codBaja)) {
                            prodBaja = p;
                            break;
                        }
                    }
                    if (prodBaja == null) {
                        System.out.println("Producto no encontrado.");
                        break;
                    }
                    if (prodBaja.getStock() <= 0) {
                        System.out.println("El producto no tiene stock disponible para dar de baja.");
                        break;
                    }

                    int cantBaja = Consola.leerEnteroEnRango(
                            "Cantidad a retirar (" + prodBaja.getUnidad() + ", stock: " + prodBaja.getStock() + "): ",
                            1,
                            prodBaja.getStock()
                    );
                    String motivo = Consola.leerOpcion(
                            "Motivo (" + Constante.M_VENCIMIENTO + ", " + Constante.M_MERMA + ", " + Constante.M_ERROR + "): ",
                            Constante.M_VENCIMIENTO,
                            Constante.M_MERMA,
                            Constante.M_ERROR
                    );

                    BajaProducto bp = new BajaProducto(prodBaja, cantBaja, motivo);
                    bajas.add(bp);
                    System.out.println("Baja registrada: " + bp);
                    break;
                }

                case 9: {
                    if (ventas.isEmpty()) {
                        System.out.println("\nNo hay ventas registradas.");
                    } else {
                        double totalGeneral = 0.0;
                        double gananciaGeneral = 0.0;
                        System.out.println("\n===== HISTORIAL DE VENTAS =====");
                        for (Venta v : ventas) {
                            Boleta b = new Boleta(v);
                            System.out.println("Comprobante : " + b.getNumeroBoleta()
                                    + " | Fecha: " + Formato.fechaHora(v.getFechaHora())
                                    + " | Cliente: " + v.getCliente().getNombre()
                                    + " | Pago: " + v.getTipopago()
                                    + " | Total: " + Formato.moneda(v.getTotal())
                                    + " | Ganancia: " + Formato.moneda(v.getGanancia()));
                            totalGeneral = Formato.redondearDosD(totalGeneral + v.getTotal());
                            gananciaGeneral = Formato.redondearDosD(gananciaGeneral + v.getGanancia());
                        }
                        System.out.println("--------------------------------");
                        System.out.println("Total vendido  : " + Formato.moneda(totalGeneral));
                        System.out.println("Ganancia total : " + Formato.moneda(gananciaGeneral));
                    }
                    break;
                }

                case 10:
                    System.out.println("Se cerro el programa");
                    break;
            }
        } while (op != 10);

        Consola.cerrar();
    }
}

// Ya acabe mano :V
// Diogo, no lo toques porfavor, esta funcionando :v
//UPDATE: No vi el silabo y  tuve que cambiar el JOptionPane por consola
