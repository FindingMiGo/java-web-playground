package com.findingmigo.talent.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 所属タレントの情報を保持するモデルクラス。
 */
public class Talent implements Serializable {
    private Long id;
    private String name;
    private Date birthday;
    private Date joinDate;
    private String homeTown;
    private String bloodType;
    private int age;
    private String memberColor;

    public Talent() {}

    public Talent(Long id, String name, Date birthday, Date joinDate, String homeTown, String bloodType, int age, String memberColor) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.joinDate = joinDate;
        this.homeTown = homeTown;
        this.bloodType = bloodType;
        this.age = age;
        this.memberColor = memberColor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
    public String getHomeTown() { return homeTown; }
    public void setHomeTown(String homeTown) { this.homeTown = homeTown; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getMemberColor() { return memberColor; }
    public void setMemberColor(String memberColor) { this.memberColor = memberColor; }
}
