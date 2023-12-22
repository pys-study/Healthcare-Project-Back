package com.hwpBackend.hwpSpring.entity;

public class Member {
    private String ID;
    private String Password;
    private String Email;
    private String Name;
    private int Age;
    private String Gender;

    @Override
    public String toString() {
        return "Member{" +
                "ID='" + ID + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                ", Gender='" + Gender + '\'' +
                '}';
    }

    public Member(String ID, String password, String email, String name, int age, String gender) {
        this.ID = ID;
        Password = password;
        Email = email;
        Name = name;
        Age = age;
        Gender = gender;
    }

    // getter setter
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


}
