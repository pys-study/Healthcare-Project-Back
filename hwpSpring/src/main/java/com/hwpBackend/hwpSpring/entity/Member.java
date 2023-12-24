package com.hwpBackend.hwpSpring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {
    public Member() {
    }
    @Id
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


    public String getPassword() {
        return Password;
    }


    public String getEmail() {
        return Email;
    }


    public String getName() {
        return Name;
    }


    public int getAge() {
        return Age;
    }


    public String getGender() {
        return Gender;
    }



}
