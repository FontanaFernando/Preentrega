import modelos.*;
import excepciones.StockInsuficienteException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Producto> inventario = new ArrayList<>();
    private static List<Pedido> historialPedidos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int contadorProductos = 1;
    private static int contadorPedidos = 1;

    public static void main(String[] args) {
        // Datos precargados para facilitar las pruebas de la pre-entrega
        inventario.add(new InsumoElectronico(contadorProductos++, "Módulo ESP32-C3", 4500.0, 15, "WiFi/Bluetooth"));
        inventario.add(new Equipamiento(contadorProductos++, "Carcasa Impresa en 3D", 1200.0, 5, "Filamento TPU"));
        inventario.add(new Equipamiento(contadorProductos++, "SCAR GBBR Charging Handle", 18500.0, 2, "Metal"));
        inventario.add(new InsumoElectronico(contadorProductos++, "Mina de Proximidad Arrojable", 8500.0, 8, "ESP-NOW"));

        int opcion = 0;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN Y PEDIDOS ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Buscar/Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Crear un Pedido");
            System.out.println("6. Listar Pedidos Realizados");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1: agregarProducto(); break;
                    case 2: listarProductos(); break;
                    case 3: actualizarProducto(); break;
                    case 4: eliminarProducto(); break;
                    case 5: crearPedido(); break;
                    case 6: listarPedidos(); break;
                    case 7: System.out.println("Cerrando sistema..."); break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
                scanner.nextLine(); // Limpiar buffer basura
            }
        } while (opcion != 7);
    }

    private static void agregarProducto() {
        System.out.println("Tipo de producto (1: Electrónica, 2: Equipamiento): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock inicial: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Protocolo: ");
            String protocolo = scanner.nextLine();
            inventario.add(new InsumoElectronico(contadorProductos++, nombre, precio, stock, protocolo));
        } else {
            System.out.print("Material: ");
            String material = scanner.nextLine();
            inventario.add(new Equipamiento(contadorProductos++, nombre, precio, stock, material));
        }
        System.out.println("Producto agregado exitosamente.");
    }

    private static void listarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("\n--- INVENTARIO ACTUAL ---");
        for (Producto p : inventario) {
            p.mostrarDetalle(); // Demostración de Polimorfismo
        }
    }

    private static void actualizarProducto() {
        System.out.print("Ingrese ID del producto a buscar/actualizar: ");
        int id = scanner.nextInt();
        Producto encontrado = buscarPorId(id);

        if (encontrado != null) {
            encontrado.mostrarDetalle();
            System.out.print("Nuevo precio (ingrese 0 para mantener): ");
            double nPrecio = scanner.nextDouble();
            if (nPrecio > 0) encontrado.setPrecio(nPrecio);

            System.out.print("Nuevo stock (ingrese -1 para mantener): ");
            int nStock = scanner.nextInt();
            if (nStock >= 0) encontrado.setStock(nStock);
            
            System.out.println("Producto actualizado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void eliminarProducto() {
        System.out.print("Ingrese ID del producto a eliminar: ");
        int id = scanner.nextInt();
        Producto encontrado = buscarPorId(id);

        if (encontrado != null) {
            System.out.print("¿Seguro que desea eliminar '" + encontrado.getNombre() + "'? (S/N): ");
            String confirmacion = scanner.next();
            if (confirmacion.equalsIgnoreCase("S")) {
                inventario.remove(encontrado);
                System.out.println("Producto eliminado.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void crearPedido() {
        if (inventario.isEmpty()) {
            System.out.println("No hay productos para pedir.");
            return;
        }

        Pedido nuevoPedido = new Pedido(contadorPedidos++);
        boolean agregando = true;

        while (agregando) {
            listarProductos();
            System.out.print("\nIngrese ID del producto a pedir (0 para finalizar): ");
            int id = scanner.nextInt();
            if (id == 0) break;

            Producto p = buscarPorId(id);
            if (p != null) {
                System.out.print("Cantidad requerida: ");
                int cant = scanner.nextInt();

                try {
                    if (cant > p.getStock()) {
                        throw new StockInsuficienteException("Stock insuficiente. Disponible: " + p.getStock());
                    }
                    if (cant > 0) {
                        nuevoPedido.agregarLinea(new LineaPedido(p, cant));
                        System.out.println("Agregado al pedido.");
                    }
                } catch (StockInsuficienteException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            } else {
                System.out.println("Producto no válido.");
            }
        }

        historialPedidos.add(nuevoPedido);
        nuevoPedido.mostrarResumen();
    }

    private static void listarPedidos() {
        if (historialPedidos.isEmpty()) {
            System.out.println("No se han realizado pedidos.");
            return;
        }
        for (Pedido p : historialPedidos) {
            p.mostrarResumen();
        }
    }

    private static Producto buscarPorId(int id) {
        for (Producto p : inventario) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}