package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "w_poetry")
public class WPoetry extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "context")
    private String context;
    @Column(name = "introduce")
    private String introduce;
    @Column(name = "wexplain")
    private String wexplain;
    @Column(name = "translation")
    private String translation;
    @Column(name = "appreciation")
    private String appreciation;
    @Column(name = "review")
    private String review;

    @Column(name = "poetry_order")
    private String poetryOrder;

    @Column(name = "out_poetry_id")
    private String outPoetryId;

    @Column(name = "out_author_id")
    private String outAuthorId;

    @Column(name = "primary1")
    private String primary;

    @Column(name = "intermediate")
    private String intermediate;

    @Column(name = "senior")
    private String senior;


    @Column(name = "author")
    private String author;
    @Column(name = "background_image_id")
    private String backgroundImageId;
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "state")
    private String state;

    @Column(name = "tryout")
    private String tryout;




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }



    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public void setReview(String review) {
        this.review = review;
    }



    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBackgroundImageId(String backgroundImageId) {
        this.backgroundImageId = backgroundImageId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getWexplain() {
        return wexplain;
    }

    public void setWexplain(String wexplain) {
        this.wexplain = wexplain;
    }

    public String getTranslation() {
        return translation;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public String getReview() {
        return review;
    }


    public String getAuthor() {
        return author;
    }

    public String getBackgroundImageId() {
        return backgroundImageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getPoetryOrder() {
        return poetryOrder;
    }

    public void setPoetryOrder(String poetryOrder) {
        this.poetryOrder = poetryOrder;
    }

    public String getOutPoetryId() {
        return outPoetryId;
    }

    public void setOutPoetryId(String outPoetryId) {
        this.outPoetryId = outPoetryId;
    }

    public String getOutAuthorId() {
        return outAuthorId;
    }

    public void setOutAuthorId(String outAuthorId) {
        this.outAuthorId = outAuthorId;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getIntermediate() {
        return intermediate;
    }

    public void setIntermediate(String intermediate) {
        this.intermediate = intermediate;
    }

    public String getSenior() {
        return senior;
    }

    public void setSenior(String senior) {
        this.senior = senior;
    }

    public String getTryout() {
        return tryout;
    }

    public void setTryout(String tryout) {
        this.tryout = tryout;
    }
}