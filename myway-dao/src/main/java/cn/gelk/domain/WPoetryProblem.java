package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "w_poetry_problem")
public class WPoetryProblem extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "poetry_id")
    private Integer poetryId;

    @Column(name = "title")
    private Integer title;

    @Column(name = "problem")
    private Integer problem;

    @Column(name = "select1")
    private Integer select1;

    @Column(name = "select2")
    private Integer select2;

    @Column(name = "select3")
    private Integer select3;

    @Column(name = "select4")
    private Integer select4;

    @Column(name = "answer")
    private Integer answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(Integer poetryId) {
        this.poetryId = poetryId;
    }

    public Integer getTitle() {
        return title;
    }

    public void setTitle(Integer title) {
        this.title = title;
    }

    public Integer getProblem() {
        return problem;
    }

    public void setProblem(Integer problem) {
        this.problem = problem;
    }

    public Integer getSelect1() {
        return select1;
    }

    public void setSelect1(Integer select1) {
        this.select1 = select1;
    }

    public Integer getSelect2() {
        return select2;
    }

    public void setSelect2(Integer select2) {
        this.select2 = select2;
    }

    public Integer getSelect3() {
        return select3;
    }

    public void setSelect3(Integer select3) {
        this.select3 = select3;
    }

    public Integer getSelect4() {
        return select4;
    }

    public void setSelect4(Integer select4) {
        this.select4 = select4;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}