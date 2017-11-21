package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_member")
public class TMember extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "regist_time")
    private Date registTime;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "grade")
    private Short grade;

    @Column(name = "sex")
    private Short sex;

    @Column(name = "status")
    private Short status;

    @Column(name = "price")
    private String price;

    @Column(name = "remark")
    private String remark;

    @Column(name = "password")
    private String password;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getMemberName() {
        return memberName;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Short getGrade() {
        return grade;
    }

    public Short getSex() {
        return sex;
    }

    public Short getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public String getPassword() {
        return password;
    }



}