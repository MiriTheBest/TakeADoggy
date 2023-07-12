package com.example.takeadoggy.Models;

public class Dog {

    private String name = "";
    private String breed = "";
    private String image = "";
    private String size = "";
    private String sex = "";
    private boolean isFavourite = false;
    private double age = 0.0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String attribute1 = "";
    private String attribute2 = "";
    private String restriction1 = "";
    private String restriction2 = "";

    public String getAttribute1() {
        return attribute1;
    }

    public Dog setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
        return this;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public Dog setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
        return this;
    }

    public String getRestriction1() {
        return restriction1;
    }

    public Dog setRestriction1(String restriction1) {
        this.restriction1 = restriction1;
        return this;
    }

    public String getRestriction2() {
        return restriction2;
    }

    public Dog setRestriction2(String restriction2) {
        this.restriction2 = restriction2;
        return this;
    }

    public Dog(){}

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Dog setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Dog setImage(String image) {
        this.image = image;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Dog setSize(String size) {
        this.size = size;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Dog setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", image='" + image + '\'' +
                ", size='" + size + '\'' +
                ", sex=" + sex +
                ", isFavourite=" + isFavourite +
                ", age=" + age +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", attribute1='" + attribute1 + '\'' +
                ", attribute2='" + attribute2 + '\'' +
                ", restriction1='" + restriction1 + '\'' +
                ", restriction2='" + restriction2 + '\'' +
                '}';
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Dog setFavourite(boolean favourite) {
        isFavourite = favourite;
        return this;
    }

    public double getAge() {
        return age;
    }

    public Dog setAge(double age) {
        this.age = age;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Dog setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Dog setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }




}
