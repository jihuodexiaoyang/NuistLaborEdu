package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据透析对象 sys_touxi
 * 
 * @author ruoyi
 * @date 2022-02-14
 */
public class SysTouxi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 登录名称 */
    @Excel(name = "登录名称")
    private String loginName;

    /** 部门编号 */
    @Excel(name = "部门编号")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标志 */
    private String delFlag;

    /** 完成标志 */
    @Excel(name = "完成标志")
    private String completeFlag;

    /** 课程类别 */
    @Excel(name = "课程类别")
    private String classType;

    /** 总学分 */
    @Excel(name = "总学分")
    private Long totalCredit;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCompleteFlag(String completeFlag) 
    {
        this.completeFlag = completeFlag;
    }

    public String getCompleteFlag() 
    {
        return completeFlag;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }
    public void setTotalCredit(Long totalCredit) 
    {
        this.totalCredit = totalCredit;
    }

    public Long getTotalCredit() 
    {
        return totalCredit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("loginName", getLoginName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("email", getEmail())
            .append("sex", getSex())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("completeFlag", getCompleteFlag())
            .append("classType", getClassType())
            .append("totalCredit", getTotalCredit())
            .toString();
    }
}
