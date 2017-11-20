package cn.gelk.domain.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import javax.persistence.Transient;

/**
 * 所有对应到表中的domain都继承该类
 */
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 3496678493493863676L;
    /**
     * 页码
     */
    @Transient
    @JSONField(serialize=false)
    private Integer pageNum;

    /**
     * 每页条目
     */
    @Transient
    @JSONField(serialize=false)
    private Integer pageSize;

    /**
     * 关键字搜索
     */
    @Transient
    @JSONField(serialize=false)
    private String searchText;

    /**
     * 搜索开始时间
     */
    @Transient
    @JSONField(serialize=false)
    private String searchStartTime;

    /**
     * 搜索结束时间
     */
    @Transient
    @JSONField(serialize=false)
    private String searchEndTime;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    @Override
    public String toString() {
        return super.toString()+" "+ JSONObject.toJSONString(this);
    }
}
