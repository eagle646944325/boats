package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "w_poetry_record")
public class WPoetryRecord extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "poetry_id")
    private Integer poetryId;

    @Column(name = "score")
    private String score;


    @Column(name = "state")
    private String state;

    @Column(name = "read_date")
    private Date readDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Integer poetryId) {
        this.poetryId = poetryId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}