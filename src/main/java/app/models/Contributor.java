package app.models;

public class Contributor {
    public String firstname;
    public String lastname;
    public String urlImage;

    public Number ident;

    public Number age;

    public String location;

    public Contributor(Number ident, String firstname, String lastname, String urlImage, Number age, String location) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.urlImage = urlImage;
        this.ident = ident;
        this.age = age;
        this.location = location;
    }

    public String getFullName(){
        return firstname + ' ' + lastname;
    }

    public String getUrlImage(){
        return urlImage;
    }


}
