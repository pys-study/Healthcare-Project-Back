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

    // FK
    @OneToMany(mappedBy = "member")
    @JsonIgnore // Member Bean에 운동 리스트, 식단 리스트를 json형태로 가져오려는 것은 아니기 때문에 어노테이션 적용
    private List<ExerciseRecord> exerciseRecordList;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<DietRecord> dietRecordList;

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

    public List<ExerciseRecord> getExerciseRecordList() {
        return exerciseRecordList;
    }

    public List<DietRecord> getDietRecordList() {
        return dietRecordList;
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

    public void setExerciseRecordList(List<ExerciseRecord> exerciseRecordList) {
        this.exerciseRecordList = exerciseRecordList;
    }

    public void setDietRecordList(List<DietRecord> dietRecordList) {
        this.dietRecordList = dietRecordList;
    }
}
