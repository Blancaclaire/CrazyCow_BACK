//clase que implementa el acceso a datos de los clientes
//proporciona metodos para realizar operaciones CRUD sobre la entidad
//customer en la BBDD

public class CustomerDao implements IDao<Customer, String>{

    //consultas SQL predefinidas
    private final String SQL_FIND_ALL = "SELECT * FROM customer WHERE 1=1 ";
    private final String SQL_LOGIN = "SELECT * FROM customer WHERE (email = ? OR user_name = ?) AND password = ?";
    private final String SQL_REGISTER = "INSERT INTO customer(customer_id, name, surname, email, phone_number, adress, user_name, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_FIND_BY_ID = "SELECT * FROM customer WHERE customer_id = ?";
    private final String SQL_FIND_BY_EMAIL = "SELECT * FROM customer WHERE email = ?";
    private final String SQL_FIND_BY_EMAIL_AND_PASSWORD = "SELECT * FROM customer WHERE email = ? AND password = ?";
    private final String SQL_AUTHENTICATE = "SELECT * FROM customer WHERE email = ? ANd PASSWORD = ?";
    private final String SQL_UPDATE = "UPDATE customer SET name = ?, surname = ?, email = ?, phone_number = ?, adress = ?, user_name = ?, password = ? WHERE customer_id = ?";
    private final String SQL_DELETE = "DELETE FROM customer WHERE customer_id = ?";

    //añadir un nuevo cliente a la BBDD
    //con los datos del cliente a registrar
    //se devuelve el numero de filas afectadas (1 si se realizó correctamente, 0 si hubo errores)

    @Override
    public int add(Customer customer){
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = mysqlConn.getConnection();
            ps = conn.prepareStatement(SQL_REGISTER);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getSurname());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPhoneNumber());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getUserName());
            ps.setString(8, customer.getPassword());

            return ps.executeUpdate();
        } catch (SQLException e){
            //se pone err en vez de out porque se imprime un problema
            System.err.println("Error al registrar el cliente" + e.getMessage());
            return 0;
        } finally {
            try{
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e){
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    //Autentica a un cliente mediante su email o contraseña
    //se devuelve el objeto customer si la autentificacion fue exitosa, mull en caso contrario
    public Customer authenticate(String email, String password){
        Customer customer = null;
        MySQLConnection mySQLConn = new MySQLConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = mySQLConn.getConnection();
            ps = conn.prepareStatement(SQL_AUTHENTICATE);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si hay resultados, mapear los datos al objeto customer
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLExeption e){
            // Error en SQL durante la autenticación
            System.err.println("Error al autenticar cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return customer;

    }

    //realiza el inicio de sesion de un cliente usando email/nombre de usuario y contraseña.
    //objeto customer si el login fue existoso, null es caso contrario
    public Customer login(String emailOrUsername, String password) {
        Customer customer = null;
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = mysqlConn.getConnection();
            ps = connection.prepareStatement(SQL_LOGIN);
            // Permite login con email o username
            ps.setString(1, emailOrUsername);
            ps.setString(2, emailOrUsername);
            ps.setString(3, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Mapeo del resultado a objeto Customer
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Error durante el login: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return customer;
    }

    //busca un cliente por su email y contraseña
    //si el objeto customer si se encuentra, null en caso contrario
    public Customer findByEmailAndPassword(String email, String password) {
        Customer customer = null;
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = mysqlConn.getConnection();
            ps = conn.prepareStatement(SQL_FIND_BY_EMAIL_AND_PASSWORD);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Crea y puebla el objeto customer con los datos de la BD
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por email y contraseña: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return customer;
    }

    //elimina un cliente de la BBDD por su ID
    //devuelve numero de filas afectadas (1 si se realizo correctamente, 0 si hubo error)
    @Override
    public int delete(String id) {
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = mysqlConn.getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setString(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            return 0;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    //actualiza los datos de un cliente en la BBDD
    //devuelve el numero de filas afectadas(1 si se realizo correctamente, 0 si hubo error)
    @Override
    public int update(Customer customer) {
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = mysqlConn.getConnection();
            ps = connection.prepareStatement(SQL_UPDATE);
            // Asigna todos los campos para actualizar
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getUserName());
            ps.setString(7, customer.getPassword());
            ps.setString(8, customer.getCustomerId()); // WHERE de la consulta
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return 0;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    //busca cliente que coincidan con los criterios del filtro
    //devuelve la lista de clientes que cumplen con los criterios

    @Override
    public ArrayList<Customer> findAll(Customer filter) {
        ArrayList<Customer> customers = new ArrayList<>();
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Construye la consulta SQL dinámicamente según el filtro
            StringBuilder sql = new StringBuilder(SQL_FIND_ALL);

            if (filter != null) {
                // Añade condiciones WHERE según los atributos del filtro
                if (filter.getCustomerId() != null && !filter.getCustomerId().isEmpty()) {
                    sql.append(" AND customer_id = ?");
                }
                if (filter.getName() != null && !filter.getName().isEmpty()) {
                    sql.append(" AND name = ?");
                }
                if (filter.getSurname() != null && !filter.getSurname().isEmpty()) {
                    sql.append(" AND surname = ?");
                }
                if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                    sql.append(" AND email = ?");
                }
                if (filter.getUserName() != null && !filter.getUserName().isEmpty()) {
                    sql.append(" AND user_name = ?");
                }
                if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
                    sql.append(" AND phone_number = ?");
                }
                if (filter.getAddress() != null && !filter.getAddress().isEmpty()) {
                    sql.append(" AND address = ?");
                }
            }

            connection = mysqlConn.getConnection();
            ps = connection.prepareStatement(sql.toString());

            // Establece parámetros si el filtro no es nulo
            if (filter != null) {
                int paramIndex = 1;
                // Asigna valores a los parámetros (?) según el orden de aparición
                if (filter.getCustomerId() != null && !filter.getCustomerId().isEmpty()) {
                    ps.setString(paramIndex++, filter.getCustomerId());
                }
                if (filter.getName() != null && !filter.getName().isEmpty()) {
                    ps.setString(paramIndex++, filter.getName());
                }
                if (filter.getSurname() != null && !filter.getSurname().isEmpty()) {
                    ps.setString(paramIndex++, filter.getSurname());
                }
                if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                    ps.setString(paramIndex++, filter.getEmail());
                }
                if (filter.getUserName() != null && !filter.getUserName().isEmpty()) {
                    ps.setString(paramIndex++, filter.getUserName());
                }
                if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
                    ps.setString(paramIndex++, filter.getPhoneNumber());
                }
                if (filter.getAddress() != null && !filter.getAddress().isEmpty()) {
                    ps.setString(paramIndex, filter.getAddress());
                }
            }

            System.out.println("Ejecutando SQL: " + sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                // Crea un objeto Customer por cada fila y lo añade a la lista
                Customer customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }

            System.out.println("Número de clientes encontrados: " + customers.size());
        } catch (SQLException e) {
            System.err.println("Error al buscar clientes: " + e.getMessage());
            customers.clear();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return customers;
    }


    //busca un cliente por su email
    //devuelve objeto customer si se encuentra, null en caso contrario

    public Customer findByEmail(String email) {
        Customer customer = null;
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = mysqlConn.getConnection();
            ps = connection.prepareStatement(SQL_FIND_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Crea y puebla objeto con datos del cliente
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por email: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return customer;
    }


    //busca un cliente por su id
    //devuelve el objeto customer si se encuentra, null en caso contrario

    public Customer findById(String id) {
        Customer customer = null;
        MySQLConnection mysqlConn = new MySQLConnection();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = mysqlConn.getConnection();
            ps = connection.prepareStatement(SQL_FIND_BY_ID);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Crea y mapea cliente con datos obtenidos
                customer = new Customer();
                customer.setCustomerId(rs.getString("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));
                customer.setUserName(rs.getString("user_name"));
                customer.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por ID: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return customer;
    }

}