/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhnh.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author kubin
 */
public class StaffDTO implements Serializable {

    private String staffID;
    private String password;
    private String firstName;
    private String lastName;
    private Timestamp DOB;
    private String gender;
    private String address;
    private int salary;
    private String role;
    private boolean available;

    public StaffDTO(String staffID, String password, String firstName, String lastName, Timestamp DOB, String gender, String address, int salary, String role, boolean available) {
        this.staffID = staffID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.gender = gender;
        this.address = address;
        this.salary = salary;
        this.role = role;
        this.available = available;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDOB() {
        return DOB;
    }

    public void setDOB(Timestamp DOB) {
        this.DOB = DOB;
    }

    public StaffDTO() {
    }

    public StaffDTO(String staffID, String firstName, String lastName, String gender, String address, int salary, String role, boolean available) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.salary = salary;
        this.role = role;
        this.available = available;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StaffDTO(String firstName, String lastName, String role, boolean available) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.available = available;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
