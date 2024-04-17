package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生选课对象 sys_user_class
 * 
 * @author ruoyi
 * @date 2021-12-30
 */
public class SysUserClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生编号 */
    private Long userId;

    /** 学习类型0理论学习1实践学习2在线学习 */
    private String classType;

    /** 课程编号 */
    private Long classId;

    /** 完成标志0未完成1已完成 */
    @Excel(name = "完成标志0未完成1已完成")
    private String completeFlag;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setCompleteFlag(String completeFlag) 
    {
        this.completeFlag = completeFlag;
    }

    public String getCompleteFlag() 
    {
        return completeFlag;
    }
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("classType", getClassType())
            .append("classId", getClassId())
            .append("createTime", getCreateTime())
            .append("completeFlag", getCompleteFlag())
            .append("completeTime", getCompleteTime())
            .toString();
    }
}
