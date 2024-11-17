package com.github.mpalambonisi.lab06.q4;


import java.io.Serializable;
import java.util.Date;
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private double salary;
    private Gender gender;
    private String division;
    private String workPosition;

    public enum Gender{
        MALE, FEMALE, OTHER
    }

    public User(String firstName, String lastName, Date birthDate, double salary, Gender gender, String division, String workPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
        this.gender = gender;
        this.division = division;
        this.workPosition = workPosition;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    @Override
    public String toString() {
        return "User {" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", gender=" + gender +
                ", division='" + division + '\'' +
                ", workPosition='" + workPosition + '\'' +
                '}';
    }
}
