package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "w_theme")
public class WTheme extends BaseDomain {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "theme_name")
    private String themeName;

    @Column(name = "image_id")
    private String imageId;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "type")
    private String type;

//    public List<WTheme> subWTheme;
//
//    public List<WTheme> getSubWTheme() {
//        return subWTheme;
//    }
//
//    public void setSubWTheme() {
//        this.subWTheme = subWTheme;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}