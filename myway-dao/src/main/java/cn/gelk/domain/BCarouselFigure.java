package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;
import java.util.Date;
import javax.persistence.*;

@Table(name = "b_carousel_figure")
public class BCarouselFigure extends BaseDomain {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 轮播图标题
     */
    @Column(name = "figure_title")
    private String figureTitle;

    /**
     * 轮播图图标地址
     */
    @Column(name = "image_id")
    private String imageId;

    /**
     * 类型
     */
    @Column(name = "figure_type")
    private String figureType;

    /**
     * 创建时间
     */
    @Column(name = "crate_time")
    private Date crateTime;

    /**
     * 录播图URL
     */
    @Column(name = "figure_url")
    private String figureUrl;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取轮播图标题
     *
     * @return figure_title - 轮播图标题
     */
    public String getFigureTitle() {
        return figureTitle;
    }

    /**
     * 设置轮播图标题
     *
     * @param figureTitle 轮播图标题
     */
    public void setFigureTitle(String figureTitle) {
        this.figureTitle = figureTitle;
    }

    /**
     * 获取轮播图图标地址
     *
     * @return image_id - 轮播图图标地址
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * 设置轮播图图标地址
     *
     * @param imageId 轮播图图标地址
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取类型
     *
     * @return figure_type - 类型
     */
    public String getFigureType() {
        return figureType;
    }

    /**
     * 设置类型
     *
     * @param figureType 类型
     */
    public void setFigureType(String figureType) {
        this.figureType = figureType;
    }

    /**
     * 获取创建时间
     *
     * @return crate_time - 创建时间
     */
    public Date getCrateTime() {
        return crateTime;
    }

    /**
     * 设置创建时间
     *
     * @param crateTime 创建时间
     */
    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    /**
     * 获取录播图URL
     *
     * @return figure_url - 录播图URL
     */
    public String getFigureUrl() {
        return figureUrl;
    }

    /**
     * 设置录播图URL
     *
     * @param figureUrl 录播图URL
     */
    public void setFigureUrl(String figureUrl) {
        this.figureUrl = figureUrl;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}