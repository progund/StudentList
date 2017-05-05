package se.juneday.examples.studentlist;

public class Student {

    private String givenName;
    private String familyName;

    public Student(String givenName, String familyName) {
        this.givenName = givenName;
        this.familyName = familyName;
    }

    @Override
    public String toString() {
        return givenName + " " + familyName;
    }

}
