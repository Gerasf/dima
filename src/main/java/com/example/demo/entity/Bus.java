package com.example.demo.entity;

public class Bus {
    private Long id;
    private String number;
    private String brand;
    private String year;
    private String numberRout;
    private String seating;
    private String totalCapacity;
    private String passengersNow;
    private boolean door;

    public Bus() {
    }

    public Bus(Long id, String number, String brand, String year, String numberRout, String seating, String totalCapacity, String passengersNow, boolean door) {
        this.id = id;
        this.number = number;
        this.brand = brand;
        this.year = year;
        this.numberRout = numberRout;
        this.seating = seating;
        this.totalCapacity = totalCapacity;
        this.passengersNow = passengersNow;
        this.door = door;
    }


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

    public void setNumberRout(String numberRout) {
        this.numberRout = numberRout;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public void setTotalCapacity(String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setPassengersNow(String passengersNow) {
        this.passengersNow = passengersNow;
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

    public String getNumberRout() {
        return numberRout;
    }

    public String getSeating() {
        return seating;
    }

    public String getTotalCapacity() {
        return totalCapacity;
    }

    public String getPassengersNow() {
        return passengersNow;
    }

    public boolean getDoor() {
        return door;
    }
}
