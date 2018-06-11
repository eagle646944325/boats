package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "w_poetry_problem")
public class WPoetryProblem extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "poetry_id")
    private String poetryId;

    @Column(name = "title")
    private String title;

    @Column(name = "problem")
    private String problem;

    @Column(name = "select1")
    private String select1;

    @Column(name = "select2")
    private String select2;

    @Column(name = "select3")
    private String select3;

    @Column(name = "select4")
    private String select4;

    @Column(name = "answer")
    private String answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(String poetryId) {
        this.poetryId = poetryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSelect1() {
        return select1;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public String getSelect2() {
        return select2;
    }

    public void setSelect2(String select2) {
        this.select2 = select2;
    }

    public String getSelect3() {
        return select3;
    }

    public void setSelect3(String select3) {
        this.select3 = select3;
    }

    public String getSelect4() {
        return select4;
    }

    public void setSelect4(String select4) {
        this.select4 = select4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}