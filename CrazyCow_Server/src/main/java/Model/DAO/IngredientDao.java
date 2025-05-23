package Model.DAO;

import Model.Entities.Ingredient;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación del DAO para gestionar ingredientes en el sistema.
 * Proporciona operaciones para buscar ingredientes asociados a productos.
 */
public class IngredientDao implements IDao {

    // Consulta SQL para encontrar ingredientes por producto
    private final String SQL_FIND_BY_PRODUCT = "SELECT ING.ingredient_id, ING.ingredient_name FROM INGREDIENTS ING inner join INGREDIENTS_PRODUCTS IP ON ING.ingredient_id=IP.ingredient_id inner join PRODUCTS PRO ON PRO.product_id=IP.product_id  where PRO.product_id=? ";
    private IMotorSql motorSql;

    /**
     * Constructor que inicializa el motor de base de datos.
     */
    public IngredientDao() {
        motorSql = new MotorSql();
    }


    // ==================== MÉTODOS CRUD ====================

    @Override
    public int add(Object bean) {
        return 0;
    }

    @Override
    public int delete(Object e) {
        return 0;
    }

    @Override
    public int update(Object bean) {
        return 0;
    }

    /**
     * Busca todos los ingredientes asociados a un producto específico.
     * @param bean Producto del que se quieren obtener los ingredientes (debe ser de tipo Product)
     * @return ArrayList de objetos Ingredient asociados al producto
     */
    @Override
    public ArrayList findAll(Object bean) {

        ArrayList listIngredients = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SQL_FIND_BY_PRODUCT;

        try {

            if (bean != null && bean instanceof Product) {

                motorSql.connect();
                Product product = (Product) bean;
                ps = motorSql.getConnection().prepareStatement(SQL_FIND_BY_PRODUCT);
                ps.setInt(1, product.getProduct_id());

                rs = ps.executeQuery();

                while (rs.next()) {
                    Ingredient ingredient = new Ingredient(
                            rs.getInt("ingredient_id"),
                            rs.getString("ingredient_name")
                    );
                    listIngredients.add(ingredient);

                }
            } else {
                System.out.println("El objeto introducido no es un empelado");
            }
        } catch (SQLException sqlEx) {
            System.out.println("Error end findAllProducts" + sqlEx.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return listIngredients;
    }
}
