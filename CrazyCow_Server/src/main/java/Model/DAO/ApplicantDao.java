package Model.DAO;

import Model.Entities.Applicant;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicantDao implements IDao {

    private final String SQL_FIND_ALL= "SELECT A.*, JO.title FROM APPLICANTS A JOIN JOB_OFFERS_APPLICANTS JOA ON A.applicant_id = JOA.applicant_id JOIN JOB_OFFERS JO ON JOA.job_offer_id = JO.job_offer_id";
    private final String SQL_ADD_APPLICANT = "INSERT INTO APPLICANTS (name, surname, email, phone_number, address, resume) VALUES (?,?,?,?,?,?)";
    private final String SQL_INSERT_JOB_OFFERS_APPLICANTS = "INSERT INTO JOB_OFFERS_APPLICANTS (job_offer_id,applicant_id,application_date) VALUES (?,?,CURRENT_TIMESTAMP);" ;
    private IMotorSql motorSql;


    public ApplicantDao() {
        motorSql = new MotorSql();
    }


    @Override
    public int add(Object bean) {
        int filas = 0;
        if (bean != null && bean instanceof Applicant) {
            Applicant applicant = (Applicant) bean;
            PreparedStatement ps = null;
            String sql = SQL_ADD_APPLICANT;

            try {
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);

                ps.setString(1, applicant.getName());
                ps.setString(2, applicant.getSurname());
                ps.setString(3, applicant.getEmail());
                ps.setString(4, applicant.getPhone_number());
                ps.setString(5, applicant.getAddress());
                ps.setString(6, applicant.getResume());

                filas = motorSql.executeUpdate(ps);



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
