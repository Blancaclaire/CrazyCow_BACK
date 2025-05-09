public class Customers{

    //atributos de la clase customer
    private int m_CustomerId;
    private String m_CustomerName;
    private String m_CustomerSurname;
    private String m_CustomerEmail;
    private int m_CustomerPhoneNumber;
    private String m_CustomerAddress;
    private String m_CustomerUserName;
    private String m_CustomerPassword;

    //constructor por defecto
    public Customers() {
    }

    //constructor con los atributos
    public Customers(String m_CustomerPassword, String m_CustomerUserName, String m_CustomerAddress, int m_CustomerPhoneNumber, String m_CustomerEmail, String m_CustomerSurname, String m_CustomerName, int m_CustomerId) {
        this.m_CustomerPassword = m_CustomerPassword;
        this.m_CustomerUserName = m_CustomerUserName;
        this.m_CustomerAddress = m_CustomerAddress;
        this.m_CustomerPhoneNumber = m_CustomerPhoneNumber;
        this.m_CustomerEmail = m_CustomerEmail;
        this.m_CustomerSurname = m_CustomerSurname;
        this.m_CustomerName = m_CustomerName;
        this.m_CustomerId = m_CustomerId;
    }

    //metodos getters y setters


    public int getCustomerId() {
        return m_CustomerId;
    }

    public void setCustomerId(int m_CustomerId) {
        CustomerId = m_CustomerId;
    }

    public String getCustomerName() {
        return m_CustomerName;
    }

    public void setCustomerName(String m_CustomerName) {
        CustomerName = m_CustomerName;
    }

    public String getCustomerSurname() {
        return m_CustomerSurname;
    }

    public void setCustomerSurname(String m_CustomerSurname) {
        CustomerSurname = m_CustomerSurname;
    }

    public String getCustomerEmail() {
        return m_CustomerEmail;
    }

    public void setCustomerEmail(String m_CustomerEmail) {
        CustomerEmail = m_CustomerEmail;
    }

    public int getCustomerPhoneNumber() {
        return m_CustomerPhoneNumber;
    }

    public void setCustomerPhoneNumber(int m_CustomerPhoneNumber) {
        CustomerPhoneNumber = m_CustomerPhoneNumber;
    }

    public String getCustomerAddress() {
        return m_CustomerAddress;
    }

    public void setCustomerAddress(String m_CustomerAddress) {
        CustomerAddress = m_CustomerAddress;
    }

    public String getCustomerUserName() {
        return m_CustomerUserName;
    }

    public void setCustomerUserName(String m_CustomerUserName) {
        CustomerUserName = m_CustomerUserName;
    }

    public String getCustomerPassword() {
        return m_CustomerPassword;
    }

    public void setCustomerPassword(String m_CustomerPassword) {
        CustomerPassword = m_CustomerPassword;
    }


    //override
    @Override
    public java.lang.String toString() {
        return "Customers{" +
                "m_CustomerId=" + m_CustomerId +
                ", m_CustomerName='" + m_CustomerName + '\'' +
                ", m_CustomerSurname='" + m_CustomerSurname + '\'' +
                ", m_CustomerEmail='" + m_CustomerEmail + '\'' +
                ", m_CustomerPhoneNumber=" + m_CustomerPhoneNumber +
                ", m_CustomerAddress='" + m_CustomerAddress + '\'' +
                ", m_CustomerUserName='" + m_CustomerUserName + '\'' +
                ", m_CustomerPassword='" + m_CustomerPassword + '\'' +
                '}';
    }
}