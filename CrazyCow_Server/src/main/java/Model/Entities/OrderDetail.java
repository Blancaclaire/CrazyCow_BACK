package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderDetail {


    private int order_id;
    private int product_id;
    private int quantity;

    //Getter and setter

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

    //Constructores

    public OrderDetail(){

    }

    public OrderDetail(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }


    //Metodos


    @Override
    public String toString() {
        return "OrderDetail{" +
                "order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }

    public static String toJson(OrderDetail orderDetail) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        return gson.toJson(orderDetail);
    }
}
