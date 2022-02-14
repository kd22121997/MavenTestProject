package JSON.Pojo.BasicJsonfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobAcrossCareer {

    @SerializedName("NameOfCompany")
    @Expose
    private String nameOfCompany;

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

}