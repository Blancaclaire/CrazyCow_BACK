package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Clase que representa un detalle de pedido en el sistema.
 * Contiene información sobre los productos específicos que forman parte de un pedido
 * y la cantidad solicitada de cada uno.
 */
public class OrderDetail {

    // ==================== ATRIBUTOS ====================

    private int order_id;
    private int product_id;
    private int quantity;

    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ==================== CONSTRUCTORES ====================

    public OrderDetail(){

    }

    public OrderDetail(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public OrderDetail(int product_id, int quantity) {

        this.product_id = product_id;
        this.quantity = quantity;
    }


    // ==================== METODOS ====================


    @Override
    public String toString() {
        return "OrderDetail{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Convierte un detalle de pedido a formato JSON
     * @param orderDetail Detalle a convertir
     * @return Cadena JSON con la representación del detalle
     */
    public static String toJson(OrderDetail orderDetail) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(orderDetail);
    }
}
