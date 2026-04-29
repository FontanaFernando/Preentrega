# Sistema de Gestión de Intendencia Táctica y Electrónica

Este proyecto es una aplicación de consola desarrollada en Java puro (Java SE) diseñada para gestionar el inventario y los pedidos de equipamiento táctico y componentes electrónicos (como módulos ESP32, sensores y réplicas). 

Fue desarrollado como **Pre-entrega** para el programa de **Back-End / Java de Talento Tech**.

## 🚀 Características Principales

El sistema cumple con los requisitos fundamentales de la cursada, implementando:

*   **Programación Orientada a Objetos (POO):** Uso extensivo de clases, objetos y encapsulamiento para proteger los datos.
*   **Herencia y Polimorfismo:** Una clase abstracta base (`Producto`) de la cual heredan categorías específicas (`InsumoElectronico` y `Equipamiento`), sobrescribiendo métodos como `mostrarDetalle()`.
*   **Gestión de Colecciones:** Uso de `ArrayList` e interfaces `List` para almacenar el inventario dinámico y el historial de pedidos en tiempo de ejecución.
*   **Manejo de Excepciones:** Creación de excepciones personalizadas (ej. `StockInsuficienteException`) y captura de errores de entrada del usuario (`InputMismatchException`).
*   **Lógica de Negocio y Pedidos:** Capacidad de crear carritos de compras (`Pedido` y `LineaPedido`), calculando subtotales y descontando el stock automáticamente tras la confirmación.

## 📂 Estructura del Proyecto

El código fuente está organizado en paquetes (módulos) para mantener una arquitectura limpia:
```text
src/
 ├── modelos/
 │    ├── Producto.java             # Clase abstracta base
 │    ├── InsumoElectronico.java    # Hereda de Producto
 │    ├── Equipamiento.java         # Hereda de Producto
 │    ├── LineaPedido.java          # Relación producto-cantidad
 │    └── Pedido.java               # Gestión de ticket y subtotales
 ├── excepciones/
 │    └── StockInsuficienteException.java  # Excepción personalizada
 └── Main.java                      # Lógica de control y menú interactivo
