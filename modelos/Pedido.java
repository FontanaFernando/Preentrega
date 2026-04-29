package modelos;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private List<LineaPedido> lineas;
    private double total;

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
        this.lineas = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
        total += linea.getSubtotal();
        // Reducimos el stock al confirmar la línea
        linea.getProducto().reducirStock(linea.getCantidad());
    }

    public void mostrarResumen() {
        System.out.println("\n--- TICKET DE PEDIDO #" + idPedido + " ---");
        for (LineaPedido lp : lineas) {
            System.out.println("- " + lp.getCantidad() + "x " + lp.getProducto().getNombre() + " (Subtotal: $" + lp.getSubtotal() + ")");
        }
        System.out.println("TOTAL A PAGAR: $" + total);
        System.out.println("----------------------------");
    }
}