package com.example.demo.entity;

public class abobus {
    private Long id;
    private String number;
    private String brand;
    private String year;
    private String number_rout;
    private String seating;
    private String total_capacity;
    private String passengers_now;
private boolean door;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setNumber_rout(String number_rout) {
        this.number_rout = number_rout;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public void setTotal_capacity(String total_capacity) {
        this.total_capacity = total_capacity;
    }

    public void setPassengers_now(String passengers_now) {
        this.passengers_now = passengers_now;
    }

    public void setDoor(boolean door) {
        this.door = door;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getBrand() {
        return brand;
    }

    public String getYear() {
        return year;
    }

    public String getNumber_rout() {
        return number_rout;
    }

    public String getSeating() {
        return seating;
    }

    public String getTotal_capacity() {
        return total_capacity;
    }

    public String getPassengers_now() {
        return passengers_now;
    }

    public boolean getDoor() {
        return door;
    }

    public abobus(Long id, String number, String brand, String year, String number_rout, String seating, String total_capacity, String passengers_now, boolean door) {
        this.id = id;
        this.number = number;
        this.brand = brand;
        this.year = year;
        this.number_rout = number_rout;
        this.seating = seating;
        this.total_capacity = total_capacity;
        this.passengers_now = passengers_now;
        this.door = door;
    }
}
