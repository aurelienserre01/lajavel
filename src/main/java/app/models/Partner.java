package app.models;

public class Partner {
    public String companyName;
    public String urlLogo;

    public String location;

    public Partner(String companyName, String urlLogo, String location) {
        this.companyName = companyName;
        this.urlLogo = urlLogo;
        this.location = location;
    }

    public String getCompanyName(){
        return companyName;
    }

    public String getUrlLogo(){
        return urlLogo;
    }
}
