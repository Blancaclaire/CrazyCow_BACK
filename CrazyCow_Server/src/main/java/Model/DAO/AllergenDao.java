package Model.DAO;

import Model.Entities.Allergen;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación del DAO para manejar operaciones con alérgenos en la base de datos.
 * Proporciona métodos para buscar alérgenos asociados a productos.
 */
public class AllergenDao implements IDao {


    // Consulta SQL para encontrar alérgenos por producto
    private final String SQL_FIND_BY_PRODUCT = "SELECT * FROM ALLERGENS AL inner join ALLERGEN_INGREDIENTS ALING on ALING.allergen_id=AL.allergen_id inner join INGREDIENTS ING on ING.ingredient_id=ALING.ingredient_id inner join INGREDIENTS_PRODUCTS IP ON ING.ingredient_id=IP.ingredient_id inner join PRODUCTS PRO ON PRO.product_id=IP.product_id where PRO.product_id= ? ";

    private IMotorSql motorSql;


    /**
     * Constructor que inicializa el motor de base de datos.
     */
    public AllergenDao() {
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
     * Busca todos los alérgenos asociados a un producto específico.
     * @param bean Producto del que se quieren obtener los alérgenos
     * @return ArrayList de objetos Allergen asociados al producto
     */
    @Override
    public ArrayList findAll(Object bean) {

        ArrayList listAllergen = new ArrayList<>();

        String sql = SQL_FIND_BY_PRODUCT;
        Product product = (Product) bean;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (bean != null) {

            try {
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);
                ps.setInt(1, product.getProduct_id());

                rs = ps.executeQuery();

                while (rs.next()) {
                    Allergen allergen = new Allergen(
                            rs.getInt("allergen_id"),
                            rs.getString("allergen_name")
                    );

                    listAllergen.add(allergen);
                }


            } catch (SQLException sqlEx) {
                System.out.println("ERROR en findAllAllergen" + sqlEx.getMessage());
            } finally {
                motorSql.disconnect();
            }
        }
        return listAllergen;
    }


}



