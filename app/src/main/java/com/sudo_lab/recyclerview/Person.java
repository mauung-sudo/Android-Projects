package com.sudo_lab.recyclerview;

public class Person {
    private int image =R.drawable.logo;
    private String name,age,job;

    Person(String name,String age,String job){
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
