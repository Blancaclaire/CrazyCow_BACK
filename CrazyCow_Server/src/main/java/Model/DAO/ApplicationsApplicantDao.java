package Model.DAO;

import Model.Entities.ApplicationsApplicant;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implementación del DAO para gestionar las postulaciones de candidatos a ofertas de trabajo.
 * Maneja la relación entre candidatos (Applicant) y ofertas de trabajo (JobOffer).
 */
public class ApplicationsApplicantDao implements IDao{

    // Consulta SQL para insertar una nueva postulación
    private String SQL_INSERT_APPLICATIONSAPPLICANT = "INSERT INTO JOB_OFFERS_APPLICANTS (job_offer_id,applicant_id,application_date) VALUES (?,?,CURRENT_TIMESTAMP);" ;
    private IMotorSql motorSql;

    /**
     * Constructor que inicializa el motor de base de datos.
     */
    public ApplicationsApplicantDao(){
        motorSql = new MotorSql();
    }


    /**
     * Registra una nueva postulación de un candidato a una oferta de trabajo.
     * @param bean Objeto ApplicationsApplicant con los IDs de oferta y candidato
     * @return Número de filas afectadas (1 si éxito, 0 si fallo)
     */
    @Override
    public int add(Object bean) {

        int filas =0;

        if (bean!=null && bean instanceof ApplicationsApplicant){
            ApplicationsApplicant applicationsApplicant = (ApplicationsApplicant) bean;
            PreparedStatement ps=null;
            String sql = SQL_INSERT_APPLICATIONSAPPLICANT;

            try{
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);
                ps.setInt(1, applicationsApplicant.getJob_offer_id());
                ps.setInt(2,applicationsApplicant.getApplicant_id());

                filas = motorSql.executeUpdate(ps);
            }
            catch (SQLException sqlException){
                System.out.println("ERROR en add ApplicationsApplicantDAo " + sqlException.getMessage() );
            }
            finally {
                motorSql.disconnect();
            }
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

    @Override
    public ArrayList findAll(Object bean) {
        return null;
    }
}
