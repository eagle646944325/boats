package cn.gelk.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "b_leaving_message")
public class BLeavingMessage {
    /**
     * 留言ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 留言人姓名
     */
    @Column(name = "leave_user_name")
    private String leaveUserName;

    /**
     * 留言人用户ID
     */
    @Column(name = "leave_user_id")
    private String leaveUserId;

    /**
     * 留言时间
     */
    @Column(name = "leave_time")
    private Date leaveTime;

    /**
     * 项目ID
     */
    @Column(name = "project_id")
    private String projectId;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目类型
     */
    @Column(name = "project_type")
    private String projectType;

    /**
     * 留言信息
     */
    private String message;

    /**
     * 回复ID
     */
    @Column(name = "reply_id")
    private String replyId;

    /**
     * 获取留言ID
     *
     * @return id - 留言ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置留言ID
     *
     * @param id 留言ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取留言人姓名
     *
     * @return leave_user_name - 留言人姓名
     */
    public String getLeaveUserName() {
        return leaveUserName;
    }

    /**
     * 设置留言人姓名
     *
     * @param leaveUserName 留言人姓名
     */
    public void setLeaveUserName(String leaveUserName) {
        this.leaveUserName = leaveUserName;
    }

    /**
     * 获取留言人用户ID
     *
     * @return leave_user_id - 留言人用户ID
     */
    public String getLeaveUserId() {
        return leaveUserId;
    }

    /**
     * 设置留言人用户ID
     *
     * @param leaveUserId 留言人用户ID
     */
    public void setLeaveUserId(String leaveUserId) {
        this.leaveUserId = leaveUserId;
    }

    /**
     * 获取留言时间
     *
     * @return leave_time - 留言时间
     */
    public Date getLeaveTime() {
        return leaveTime;
    }

    /**
     * 设置留言时间
     *
     * @param leaveTime 留言时间
     */
    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    /**
     * 获取项目ID
     *
     * @return project_id - 项目ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * 设置项目ID
     *
     * @param projectId 项目ID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取项目类型
     *
     * @return project_type - 项目类型
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * 设置项目类型
     *
     * @param projectType 项目类型
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * 获取留言信息
     *
     * @return message - 留言信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置留言信息
     *
     * @param message 留言信息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取回复ID
     *
     * @return reply_id - 回复ID
     */
    public String getReplyId() {
        return replyId;
    }

    /**
     * 设置回复ID
     *
     * @param replyId 回复ID
     */
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }
}