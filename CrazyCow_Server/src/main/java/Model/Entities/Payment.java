package Model.Entities;

/**
 * Clase que representa un pago en el sistema.
 * Contiene información sobre la transacción de pago asociada a un pedido,
 * incluyendo datos de la tarjeta y monto pagado.
 *
 * Nota: En una aplicación real, los datos sensibles como número de tarjeta y CVV
 * deberían manejarse con técnicas de encriptación y cumplir con estándares PCI DSS.
 */
public class Payment {

    // ==================== ATRIBUTOS ====================

    private int payment_id;
    private int order_id;
    private String holder_name;
    private String holder_number;
    private String cvv;
    private String card_type;
    private double price;


    // ==================== MÉTODOS DE ACCESO (GETTERS/SETTERS) ====================

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getHolder_number() {
        return holder_number;
    }

    public void setHolder_number(String holder_number) {
        this.holder_number = holder_number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    // ==================== CONSTRUCTORES ====================

    public Payment(){

    }

    public Payment(int payment_id, int order_id, String holder_name, String holder_number, String cvv, String card_type, double price) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.holder_name = holder_name;
        this.holder_number = holder_number;
        this.cvv = cvv;
        this.card_type = card_type;
        this.price = price;
    }
    public Payment(String holder_name, String holder_number, String cvv, String card_type, double price) {
        this.holder_name = holder_name;
        this.holder_number = holder_number;
        this.cvv = cvv;
        this.card_type = card_type;
        this.price = price;
    }

    // ==================== METODOS ====================

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", order_id=" + order_id +
                ", holder_name='" + holder_name + '\'' +
                ", holder_number='" + holder_number + '\'' +
                ", cvv='" + cvv + '\'' +
                ", card_type='" + card_type + '\'' +
                ", price=" + price +
                '}';
    }
}
