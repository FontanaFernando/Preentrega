package modelos;

public class InsumoElectronico extends Producto {
    private String protocoloComunicacion; // ej. LoRa, ESP-NOW, WiFi

    public InsumoElectronico(int id, String nombre, double precio, int stock, String protocolo) {
        super(id, nombre, precio, stock);
        this.protocoloComunicacion = protocolo;
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("[ELECTRÓNICA] " + super.toString() + " | Protocolo: " + protocoloComunicacion);
    }
}