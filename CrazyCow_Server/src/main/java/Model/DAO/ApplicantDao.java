package Model.DAO;

import Model.Entities.Applicant;
import Model.Entities.ApplicationsApplicant;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementación del DAO para gestionar candidatos (Applicant) en el sistema.
 * Proporciona operaciones CRUD para la gestión de candidatos y sus postulaciones a ofertas de trabajo.
 */
public class ApplicantDao implements IDao {

    // Consultas SQL preparadas

    private final String SQL_FIND_ALL= "SELECT A.*, JO.title FROM APPLICANTS A JOIN JOB_OFFERS_APPLICANTS JOA ON A.applicant_id = JOA.applicant_id JOIN JOB_OFFERS JO ON JOA.job_offer_id = JO.job_offer_id";
    private final String SQL_ADD_APPLICANT = "INSERT INTO APPLICANTS (name, surname, email, phone_number, address, resume) VALUES (?,?,?,?,?,?)";

    private IMotorSql motorSql;


    /**
     * Constructor que inicializa el motor de base de datos.
     */
    public ApplicantDao() {
        motorSql = new MotorSql();
    }

    // ==================== MÉTODOS CRUD ====================

    /**
     * Añade un nuevo candidato a la base de datos.
     * @param bean Objeto Applicant a insertar
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
    @Override
    public int add(Object bean) {
        int filas = 0;
        ResultSet generatedKeys= null;
        if (bean != null && bean instanceof Applicant) {
            Applicant applicant = (Applicant) bean;
            PreparedStatement ps = null;
            String sql = SQL_ADD_APPLICANT;

            try {
                motorSql.connect();

                // Preparar statement con capacidad de retornar claves generadas
                ps = motorSql.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, applicant.getName());
                ps.setString(2, applicant.getSurname());
                ps.setString(3, applicant.getEmail());
                ps.setString(4, applicant.getPhone_number());
                ps.setString(5, applicant.getAddress());
                ps.setString(6, applicant.getResume());

                filas = motorSql.executeUpdate(ps);

                // Obtener ID generado
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    applicant.setApplicant_id(generatedKeys.getInt(1));  // Actualizar el ID en el objeto
                }

            } catch (SQLException e) {
                System.out.println("Error en addApplicantDao" + e.getMessage());
            } finally {
                motorSql.disconnect();
            }
        } else {
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }
        return filas;
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
     * Obtiene todos los candidatos con información de las ofertas a las que han aplicado.
     * @param bean Parámetro no utilizado (mantenido por compatibilidad con interfaz)
     * @return ArrayList de objetos Applicant con información de ofertas
     */
    @Override
    public ArrayList findAll(Object bean) {

        ArrayList lisApplicants = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            motorSql.connect();

            ps = motorSql.getConnection().prepareStatement(SQL_FIND_ALL);

            rs = ps.executeQuery();

            while(rs.next()){
                Applicant applicant = new Applicant(
                        rs.getInt("applicant_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("resume"),
                        rs.getString("title")

                );

                lisApplicants.add(applicant);
            }

        }catch (SQLException sqlException){
            System.out.println("ERROR en findAll Applicants" + sqlException.getMessage());
        }
        finally {
            motorSql.disconnect();
        }
        return lisApplicants;
    }
}
