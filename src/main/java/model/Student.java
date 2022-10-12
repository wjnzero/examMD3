package model;

import java.time.LocalDate;

public class Student {
    private int id;
    private LocalDate dateBirth;
    private String name, phone, email;
    private int classroom;

    public Student() {
    }

    public Student(LocalDate dateBirth, String name, String phone, String email, int classroom) {
        this.dateBirth = dateBirth;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.classroom = classroom;
    }

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", dateBirth=" + dateBirth +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", classroom=" + classroom +
                '}';
    }
}
