package modelos;

public class Equipamiento extends Producto {
    private String material; // ej. Polímero, TPU impreso, Metal

    public Equipamiento(int id, String nombre, double precio, int stock, String material) {
        super(id, nombre, precio, stock);
        this.material = material;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("[EQUIPAMIENTO] " + super.toString() + " | Material: " + material);
    }
}