package me.scpark.springdeveloper;

public class Student {
    private String firstName;
    private String lastName;
    public Student (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public  String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return  lastName;
    }
    public void setFirstName(String FirstName) {
        this.firstName = firstName;
    }
    public void setLastName(String LastName) {
        this.lastName = lastName;
}
}

