package modelos;

public abstract class Producto {
    protected int id;
    protected String nombre;
    protected double precio;
    protected int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    // Método abstracto para aplicar Polimorfismo
    public abstract void mostrarDetalle();

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Precio: $%.2f | Stock: %d", id, nombre, precio, stock);
    }
}