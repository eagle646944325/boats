package cn.gelk.bo;

import cn.gelk.domain.WTheme;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class WThemeBo {
    private Integer id;

    private String themeName;

    private String imageId;

    private Integer parentId;


    private String remark;

    private String type;

   public List<WTheme> subWTheme;

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

    public List<WTheme> getSubWTheme() {
        return subWTheme;
    }

    public void setSubWTheme(List<WTheme> subWTheme) {
        this.subWTheme = subWTheme;
    }
}
