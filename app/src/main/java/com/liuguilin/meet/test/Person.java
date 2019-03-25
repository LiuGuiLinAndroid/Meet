package com.liuguilin.meet.test;

import cn.bmob.v3.BmobObject;

/**
 * FileName: Person
 * Founder: 刘桂林
 * Create Date: 2019/3/24 18:45
 * Profile: 学生
 */
public class Person extends BmobObject {

    private String name;
    private String address;
    private boolean sex;
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", sex=" + sex +
                ", height=" + height +
                '}';
    }
}
