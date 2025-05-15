package Model.DAO;

import Model.Entities.JobOffer;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobOfferDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM JOB_OFFERS WHERE 1=1 ";


    private IMotorSql motorSql;

    public JobOfferDao() {
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
        ArrayList listOffers = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = SQL_FIND_ALL;

        try {
            if (bean != null) {
                JobOffer jobOffer = (JobOffer) bean;

                motorSql.connect();


                StringBuilder sqlBuilder = new StringBuilder(sql);

                if (jobOffer.getJob_offer_id() > 0) {
                    sqlBuilder.append(" AND job_offer_id = ?");
                }

                ps = motorSql.getConnection().prepareStatement(sqlBuilder.toString());

                if (bean != null && jobOffer.getJob_offer_id() > 0) {
                    ps.setInt(1, jobOffer.getJob_offer_id());
                }

                rs = ps.executeQuery();

                while (rs.next()) {
                    jobOffer = new JobOffer(
                        rs.getInt("job_offer_id"),
                        rs.getInt("job_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("location")
                    );

                    listOffers.add(jobOffer);

                }
            }

        } catch (SQLException e) {
            System.out.println("Error en findAll JobOffer: " + e.getMessage());
        } finally {

            motorSql.disconnect();
        }

        return listOffers;


    }
}
