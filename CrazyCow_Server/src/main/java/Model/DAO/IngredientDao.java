package Model.DAO;

import Model.Entities.Ingredient;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDao implements IDao {


    private final String SQL_FIND_BY_PRODUCT = "SELECT ING.ingredient_id, ING.ingredient_name FROM INGREDIENTS ING inner join INGREDIENTS_PRODUCTS IP ON ING.ingredient_id=IP.ingredient_id inner join PRODUCTS PRO ON PRO.product_id=IP.product_id  where PRO.product_id=? ";
    private IMotorSql motorSql;

    public IngredientDao() {
        motorSql = new MotorSql();
    }

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
