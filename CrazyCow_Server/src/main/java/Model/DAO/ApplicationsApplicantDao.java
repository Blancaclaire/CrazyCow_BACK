package Model.DAO;

import Model.Entities.ApplicationsApplicant;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationsApplicantDao implements IDao{

    private String SQL_INSERT_APPLICATIONSAPPLICANT = "INSERT INTO JOB_OFFERS_APPLICANTS (job_offer_id,applicant_id,application_date) VALUES (?,?,CURRENT_TIMESTAMP);" ;
    private IMotorSql motorSql;

    public ApplicationsApplicantDao(){
        motorSql = new MotorSql();
    }

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
