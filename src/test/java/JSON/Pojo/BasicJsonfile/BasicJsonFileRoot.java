package JSON.Pojo.BasicJsonfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BasicJsonFileRoot {
    @SerializedName("Name of Person")
    @Expose
    private String nameOfPerson;
    @SerializedName("Age")
    @Expose
    private Integer age;
    @SerializedName("Married")
    @Expose
    private Boolean married;
    @SerializedName("Total Education")
    @Expose
    private TotalEducation totalEducation;
    @SerializedName("Job across career")
    @Expose
    private List<JobAcrossCareer> jobAcrossCareer = new ArrayList<JobAcrossCareer>();

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public void setNameOfPerson(String nameOfPerson) {
        this.nameOfPerson = nameOfPerson;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public TotalEducation getTotalEducation() {
        return totalEducation;
    }

    public void setTotalEducation(TotalEducation totalEducation) {
        this.totalEducation = totalEducation;
    }

    public List<JobAcrossCareer> getJobAcrossCareer() {
        return jobAcrossCareer;
    }

    public void setJobAcrossCareer(List<JobAcrossCareer> jobAcrossCareer) {
        this.jobAcrossCareer = jobAcrossCareer;
    }
}
