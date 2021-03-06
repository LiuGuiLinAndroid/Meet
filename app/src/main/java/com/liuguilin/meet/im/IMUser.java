package com.liuguilin.meet.im;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * FileName: IMUser
 * Founder: 刘桂林
 * Create Date: 2019/3/25 22:56
 * Profile:
 */
public class IMUser extends BmobUser implements Serializable {

    //昵称
    private String nickname;
    //年龄
    private int age = 0;
    //性别
    private boolean sex;
    //头像
    private BmobFile avatar;
    //生日
    private String birthday = "";
    //电话
    private String phone;
    //地区
    private String city = "";
    //签名
    private String desc;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public BmobFile getAvatar() {
        return avatar;
    }

    public void setAvatar(BmobFile avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "IMUser{" +
                "nickname='" + nickname + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", avatar=" + avatar +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
