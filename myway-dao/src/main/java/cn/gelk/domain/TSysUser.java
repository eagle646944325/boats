package cn.gelk.domain;

import cn.gelk.domain.base.BaseDomain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_sys_user")
public class TSysUser extends BaseDomain {

    private static final long serialVersionUID = 7269941797989709097L;


    /**
     *用户归属的组织
     */
    @Transient
    private TSysOrg tSysOrg;

    /**
     *用户具有的权限
     */
    @Transient
    private TSysRole tSysRole;

    /**
     * 用户唯一编号
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统登录名
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 真实名称
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 登录密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "MSISDN")
    private String msisdn;

    /**
     * 微信号
     */
    @Column(name = "WX")
    private String wx;

    /**
     * qq号
     */
    @Column(name = "QQ")
    private String qq;

    /**
     * 性别 0：未公开  1：男  2：女生
     */
    @Column(name = "SEX")
    private Short sex;

    /**
     * 归属组织
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 具有的角色
     */
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 状态 1：正常 0：已注销  2：暂停使用
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 获取用户唯一编号
     *
     * @return ID - 用户唯一编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户唯一编号
     *
     * @param id 用户唯一编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取系统登录名
     *
     * @return USER_NAME - 系统登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置系统登录名
     *
     * @param userName 系统登录名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取真实名称
     *
     * @return REAL_NAME - 真实名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实名称
     *
     * @param realName 真实名称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取登录密码
     *
     * @return PASSWORD - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return MSISDN - 手机号
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * 设置手机号
     *
     * @param msisdn 手机号
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * 获取微信号
     *
     * @return WX - 微信号
     */
    public String getWx() {
        return wx;
    }

    /**
     * 设置微信号
     *
     * @param wx 微信号
     */
    public void setWx(String wx) {
        this.wx = wx;
    }

    /**
     * 获取qq号
     *
     * @return QQ - qq号
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置qq号
     *
     * @param qq qq号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取性别 0：未公开  1：男  2：女生
     *
     * @return SEX - 性别 0：未公开  1：男  2：女生
     */
    public Short getSex() {
        return sex;
    }

    /**
     * 设置性别 0：未公开  1：男  2：女生
     *
     * @param sex 性别 0：未公开  1：男  2：女生
     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * 获取归属组织
     *
     * @return ORG_ID - 归属组织
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置归属组织
     *
     * @param orgId 归属组织
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取具有的角色
     *
     * @return ROLE_ID - 具有的角色
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置具有的角色
     *
     * @param roleId 具有的角色
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public TSysOrg gettSysOrg() {
        return tSysOrg;
    }

    public void settSysOrg(TSysOrg tSysOrg) {
        this.tSysOrg = tSysOrg;
    }

    public TSysRole gettSysRole() {
        return tSysRole;
    }

    public void settSysRole(TSysRole tSysRole) {
        this.tSysRole = tSysRole;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}