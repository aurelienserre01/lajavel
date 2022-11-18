package org.lajavel;

public class Personne {
    public String firstname;
    public String lastname;

    public Personne(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getname(){
        return firstname + ' ' + lastname;
    }


}
