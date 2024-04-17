package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 在线学习对象 sys_elearning
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
public class SysElearning extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long classId;

    /** 课程标题 */
    @Excel(name = "课程标题")
    private String classTitle;

    /** 课程主题分类 */
    @Excel(name = "课程主题分类")
    private String classSubject;

    /** 课程简介 */
    @Excel(name = "课程简介")
    private String classOverview;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private Integer classCredit;

    /** 申请人数 */
    @Excel(name = "申请人数")
    private Integer classApply;

    /** 喜欢人数 */
    @Excel(name = "喜欢人数")
    private Integer classLike;

    /** 课程状态（0正常 1停用） */
    @Excel(name = "课程状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public String getSelFlag() {
        return selFlag;
    }

    public void setSelFlag(String selFlag) {
        this.selFlag = selFlag;
    }

    /** 选课标志（0代表未选 1代表已选） */
    private String selFlag;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setClassTitle(String classTitle) 
    {
        this.classTitle = classTitle;
    }

    public String getClassTitle() 
    {
        return classTitle;
    }
    public void setClassSubject(String classSubject) 
    {
        this.classSubject = classSubject;
    }

    public String getClassSubject() 
    {
        return classSubject;
    }
    public void setClassOverview(String classOverview) 
    {
        this.classOverview = classOverview;
    }

    public String getClassOverview() 
    {
        return classOverview;
    }
    public void setOrderNum(Integer orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() 
    {
        return orderNum;
    }
    public void setClassCredit(Integer classCredit) 
    {
        this.classCredit = classCredit;
    }

    public Integer getClassCredit() 
    {
        return classCredit;
    }
    public void setClassApply(Integer classApply) 
    {
        this.classApply = classApply;
    }

    public Integer getClassApply() 
    {
        return classApply;
    }
    public void setClassLike(Integer classLike) 
    {
        this.classLike = classLike;
    }

    public Integer getClassLike() 
    {
        return classLike;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("classTitle", getClassTitle())
            .append("classSubject", getClassSubject())
            .append("classOverview", getClassOverview())
            .append("orderNum", getOrderNum())
            .append("classCredit", getClassCredit())
            .append("classApply", getClassApply())
            .append("classLike", getClassLike())
            .append("status", getStatus())
            .append("selFlag", getSelFlag())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
