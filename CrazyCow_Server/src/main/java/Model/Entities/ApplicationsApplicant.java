package Model.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;

public class ApplicationsApplicant {

    private int job_offer_id;
    private int  applicant_id;
    private Date application_date;

    public int getJob_offer_id() {
        return job_offer_id;
    }

    public void setJob_offer_id(int job_offer_id) {
        this.job_offer_id = job_offer_id;
    }

    public int getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(int applicant_id) {
        this.applicant_id = applicant_id;
    }

    public Date getApplication_date() {
        return application_date;
    }

    public void setApplication_date(Date application_date) {
        this.application_date = application_date;
    }


    //Constructores

    public ApplicationsApplicant(){

    }

    public ApplicationsApplicant(int job_offer_id, int applicant_id, Date application_date) {
        this.job_offer_id = job_offer_id;
        this.applicant_id = applicant_id;
        this.application_date = application_date;
    }

    @Override
    public String toString() {
        return "ApplicationsApplicant{" +
                "job_offer_id=" + job_offer_id +
                ", applicant_id=" + applicant_id +
                ", application_date=" + application_date +
                '}';
    }
    public static String toArrayJson(ArrayList<ApplicationsApplicant> listApplicationsApplicant) {

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String resp = gson.toJson(listApplicationsApplicant);
        return resp;
    }


}
