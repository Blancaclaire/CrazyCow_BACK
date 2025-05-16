package Model.DAO;

import Model.Entities.Allergen;
import Model.Entities.Ingredient;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación del DAO para la entidad Product.
 * Gestiona todas las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para los productos en la base de datos.
 */
public class ProductDao implements IDao {

    // ==================== CONSTANTES SQL ====================

    /** Consulta SQL para obtener todos los productos */
    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTS WHERE 1=1";

    /** Consulta SQL base para eliminar productos */
    private final String SQL_DELETE = "DELETE FROM PRODUCTS WHERE ";

    /** Consulta SQL para añadir nuevos productos */
    private final String SQL_ADD = "INSERT INTO PRODUCTS (category_id, product_name, description, price, image) VALUES (?,?,?,?,?)";

    /** Consulta SQL base para actualizar productos */
    private final String SQL_UPDATE = "UPDATE PRODUCTS SET ";

    /** Consulta SQL para buscar un producto por su ID */
    private final String SQL_FIND_BY_ID = "SELECT * FROM PRODUCTS WHERE product_id = ?";

    /** Motor de conexión a la base de datos */
    private IMotorSql motorSql;


    // ==================== CONSTRUCTOR ====================

    /**
     * Constructor que inicializa el motor de base de datos.
     */
    public ProductDao() {
        motorSql = new MotorSql();
    }

    // ==================== MÉTODOS CRUD ====================

    /**
     * Añade un nuevo producto a la base de datos.
     *
     * @param bean Objeto Product a insertar
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
    @Override
    public int add(Object bean) {
        int filasAdd = 0;
        //Comprobamos que el objeto sea de tipo product
        if (bean != null && bean instanceof Product) {
            Product product = (Product) bean;
            String sql = SQL_ADD;

            try {
                motorSql.connect();

                PreparedStatement sentencia = motorSql.getConnection().prepareStatement(sql);

                sentencia.setInt(1, product.getCategory_id());
                sentencia.setString(2, product.getProduct_name());
                sentencia.setString(3, product.getDescription());
                sentencia.setDouble(4, product.getPrice());
                sentencia.setString(5, product.getImage());

                filasAdd = motorSql.executeUpdate(sentencia);


            } catch (SQLException sqlEx) {

                System.out.println("Error al añadir  el producto: " + sqlEx.getMessage());
            } finally {
                motorSql.disconnect();

            }
        } else {
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }
        return filasAdd;
    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param e Puede ser el ID del producto (Integer) o el objeto Product completo
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
    @Override
    public int delete(Object e) {


        Integer idProduct = -1; //Inicializo en -1 para que no correponda con  ningun id
        int filasEliminadas = 0;


        if (e instanceof Integer) {
            idProduct = (Integer) e;
        } else if (e instanceof Product) {
            idProduct = ((Product) e).getProduct_id();
        }
        String sql = SQL_DELETE;

        //solo se puede realizar la operacion en el caso de obtener un id válido
        if (idProduct > 0) {
            try {
                motorSql.connect();
                sql += " product_id = ?";
                PreparedStatement sentencia = motorSql.getConnection().prepareStatement(sql);
                sentencia.setInt(1, idProduct);

                filasEliminadas = motorSql.executeUpdate(sentencia);

            } catch (SQLException sqlEx) {

                System.out.println("Error al eliminar el producto: " + sqlEx.getMessage());
            } finally {
                motorSql.disconnect();
            }
        }

        return filasEliminadas;

    }

    @Override

    /**
     * Busca productos según los criterios especificados.
     *
     * @param bean Objeto Product con los criterios de búsqueda
     * @return ArrayList de Product que cumplen los criterios
     */
    public ArrayList findAll(Object bean) {
        ArrayList listProducts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = SQL_FIND_ALL;

        try {
            if (bean != null && bean instanceof Product) {
                Product product = (Product) bean;  // Si el objeto no es nulo se convierte en un objeto Producto

                motorSql.connect();

                // Construcción dinámica de la consulta
                StringBuilder sqlBuilder = new StringBuilder(sql);

                // Lista para llevar un control de cuántos parámetros vamos a necesitar
                ArrayList<Integer> parameters = new ArrayList<>();

                // Preparamos la consulta para añadir condiciones según los filtros
                if (product.getProduct_id() > 0) {
                    sqlBuilder.append(" AND product_id = ?");
                    parameters.add(product.getProduct_id());
                }

                if (product.getCategory_id() > 0) {
                    sqlBuilder.append(" AND category_id = ?");
                    parameters.add(product.getCategory_id());
                }


                // Preparamos la sentencia con la consulta construida
                ps = motorSql.getConnection().prepareStatement(sqlBuilder.toString());

                // Establecemos los parámetros en el orden que fueron añadidos
                for (int i = 0; i < parameters.size(); i++) {
                    ps.setInt(i + 1, parameters.get(i));
                }


                // Ejecutamos la consulta y obtenemos resultados
                rs = ps.executeQuery();

                // Procesamos los resultados
                while (rs.next()) {
                    Product productBd = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("image")
                    );
                    listProducts.add(productBd);

                }
            }


        } catch (SQLException e) {
            System.out.println("Error en findAll: " + e.getMessage());
        } finally {

            motorSql.disconnect();
        }

        return listProducts;


    }

    /**
     * Busca un producto por su ID y carga sus ingredientes y alérgenos asociados.
     *
     * @param productId ID del producto a buscar
     * @return Objeto Product completo o null si no se encuentra
     */
    public Product findById(int productId) {
        Product product = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SQL_FIND_BY_ID;

        try {
            motorSql.connect();
            ps = motorSql.getConnection().prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("product_id"),
                        rs.getInt("category_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("image")
                );

                // **Obtener los ingredientes del producto
                IngredientDao ingredientDao = new IngredientDao();
                ArrayList<Ingredient> ingredients = ingredientDao.findAll(product);
                product.setListIngredients(ingredients);

                // **Obtener los alergenos del producto
                AllergenDao allergenDao = new AllergenDao();
                ArrayList<Allergen> allergens = allergenDao.findAll(product);
                product.setListAllergen(allergens);

            }

        } catch (SQLException e) {
            System.out.println("Error al obtener producto: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return product;
    }

    /**
     * Actualiza un producto existente en la base de datos.
     *
     * @param bean Objeto Product con los datos actualizados
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
    @Override
    public int update(Object bean) {
        int filasUpdate = 0;

        if (bean != null && bean instanceof Product) {

            Product product = (Product) bean;

            //Comprobamos la existencia del id del producto
            if (product.getProduct_id() > 0) {
                try {

                    motorSql.connect();

                    StringBuilder builder = new StringBuilder(SQL_UPDATE);
                    ArrayList<Object> parameters = new ArrayList<Object>(); //Almacena los valores que se deben actualizar
                    boolean firstParam = true; //Controla la consulta y evita comas innecesarias

                    //Para cada campo Product se verifica si tiene un valor válido, si es asi se agrega a la consulta y al array de parameters

                    if (product.getCategory_id() > 0) {
                        builder.append(firstParam ? " category_id = ?" : ", category_id = ?"); //Comprueba si es el primer parametro o no para añadirle una coma
                        parameters.add(product.getCategory_id());
                        firstParam = false;
                    }
                    if (product.getProduct_name() != null) {
                        builder.append(firstParam ? " product_name = ?" : ", product_name = ?");
                        parameters.add(product.getProduct_name());
                        firstParam = false;
                    }

                    if (product.getDescription() != null) {
                        builder.append(firstParam ? " description = ?" : ", description = ?");
                        parameters.add(product.getDescription());
                        firstParam = false;
                    }

                    if (product.getPrice() > 0) {
                        builder.append(firstParam ? " price = ?" : ", price = ?");
                        parameters.add(product.getPrice());
                        firstParam = false;
                    }

                    if (product.getImage() != null) {
                        builder.append(firstParam ? " image = ?" : ", image = ?");
                        parameters.add(product.getImage());
                        firstParam = false;
                    }

                    // Añadimos la condición WHERE para identificar el producto a actualizar
                    builder.append(" WHERE product_id = ?");
                    parameters.add(product.getProduct_id());


                    // Preparamos la sentencia
                    PreparedStatement sentencia = motorSql.getConnection().prepareStatement(builder.toString());

                    // Establecemos los parámetros
                    for (int i = 0; i < parameters.size(); i++) {
                        Object param = parameters.get(i); //Almacena el valor de cada campo a actualizar

                        //Verificamos el tipo de dato de param y lo asignamos  alaconsulta con sentencia
                        //i+1 porque los paraemtros comienzan en 1 en PreparedStatement
                        if (param instanceof Integer) {
                            sentencia.setInt(i + 1, (Integer) param);
                        } else if (param instanceof Double) {
                            sentencia.setDouble(i + 1, (Double) param);
                        } else if (param instanceof String) {
                            sentencia.setString(i + 1, (String) param);
                        }


                    }

                    filasUpdate = motorSql.executeUpdate(sentencia);


                } catch (SQLException sqlEx) {
                    System.out.println("Error updating product" + sqlEx.getMessage());
                } finally {
                    motorSql.disconnect();
                }
            } else {
                System.out.println("Error: No se puede actualizar un producto sin ID válido");
            }

        } else {
            System.out.println("Error: The object received is not a valid product or is null.");
        }

        return filasUpdate;
    }
}
