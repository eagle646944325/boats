package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "w_user")
public class WUser extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "photo")
    private String  photo;

    @Column(name = "name")
    private String name;

    @Column(name = "read_days")
    private Integer readDays;

    @Column(name = "level")
    private String level;


    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "state")
    private String state;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "integral")
    private Integer integral;

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReadDays() {
        return readDays;
    }

    public void setReadDays(Integer readDays) {
        this.readDays = readDays;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}