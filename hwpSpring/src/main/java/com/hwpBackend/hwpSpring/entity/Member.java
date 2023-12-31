package com.hwpBackend.hwpSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Member {
    public Member() {
    }
    @Id
    @Size(min=2, message = "ID는 2글자 이상이어야 합니다.")
    private String ID;
    private String Password;
    private String Email;
    private String Name;
    private Integer Age;
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

    public Member(String ID, String password, String email, String name, Integer age, String gender) {
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


    public Integer getAge() {
        return Age;
    }


    public String getGender() {
        return Gender;
    }


    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

}
