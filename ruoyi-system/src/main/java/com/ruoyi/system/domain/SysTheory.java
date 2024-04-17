package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 理论学习对象 sys_theory
 *
 * @author ruoyi
 * @date 2022-03-06
 */
public class SysTheory extends BaseEntity
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

    /** 归属部门 */
    @Excel(name = "归属部门")
    private Long classDept;

    /** 开课单位 */
    @Excel(name = "开课单位")
    private String classOrg;

    /** 讲座内容 */
    @Excel(name = "讲座内容")
    private String classOverview;

    /** 开课地点 */
    @Excel(name = "开课地点")
    private String classAddress;

    /** 负责人编号 */
    @Excel(name = "负责人编号")
    private Long classFzrid;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String classFzrxm;

    /** 联系人 */
    @Excel(name = "联系人")
    private String classOwner;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String classMobile;

    /** 课程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "课程开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date classStarttime;

    /** 课程结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "课程结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date classEndtime;

    /** 排序 */
    @Excel(name = "排序")
    private Integer orderNum;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private Integer classCredit;

    /** 容纳人数 */
    @Excel(name = "容纳人数")
    private Integer classLimit;

    /** 申请人数 */
    @Excel(name = "申请人数")
    private Integer classApply;

    /** 喜欢人数 */
    @Excel(name = "喜欢人数")
    private Integer classLike;

    /** 课程状态（0正常 1停用） */
    @Excel(name = "课程状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 选课标记 (0未选 1已选) */
    @Excel(name = "选课标记 (0未选 1已选)")
    private String selFlag;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
    /** 部门组（数据权限） */
    private Long[] deptIds;

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
    public void setClassDept(Long classDept)
    {
        this.classDept = classDept;
    }

    public Long getClassDept()
    {
        return classDept;
    }
    public void setClassOrg(String classOrg)
    {
        this.classOrg = classOrg;
    }

    public String getClassOrg()
    {
        return classOrg;
    }
    public void setClassOverview(String classOverview)
    {
        this.classOverview = classOverview;
    }

    public String getClassOverview()
    {
        return classOverview;
    }
    public void setClassAddress(String classAddress)
    {
        this.classAddress = classAddress;
    }

    public String getClassAddress()
    {
        return classAddress;
    }
    public void setClassFzrid(Long classFzrid)
    {
        this.classFzrid = classFzrid;
    }

    public Long getClassFzrid()
    {
        return classFzrid;
    }
    public void setClassFzrxm(String classFzrxm)
    {
        this.classFzrxm = classFzrxm;
    }

    public String getClassFzrxm()
    {
        return classFzrxm;
    }
    public void setClassOwner(String classOwner)
    {
        this.classOwner = classOwner;
    }

    public String getClassOwner()
    {
        return classOwner;
    }
    public void setClassMobile(String classMobile)
    {
        this.classMobile = classMobile;
    }

    public String getClassMobile()
    {
        return classMobile;
    }
    public void setClassStarttime(Date classStarttime)
    {
        this.classStarttime = classStarttime;
    }

    public Date getClassStarttime()
    {
        return classStarttime;
    }
    public void setClassEndtime(Date classEndtime)
    {
        this.classEndtime = classEndtime;
    }

    public Date getClassEndtime()
    {
        return classEndtime;
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
    public void setClassLimit(Integer classLimit)
    {
        this.classLimit = classLimit;
    }

    public Integer getClassLimit()
    {
        return classLimit;
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
    public void setSelFlag(String selFlag)
    {
        this.selFlag = selFlag;
    }

    public String getSelFlag()
    {
        return selFlag;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public Long[] getDeptIds()
    {
        return deptIds;
    }

    public void setDeptIds(Long[] deptIds)
    {
        this.deptIds = deptIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("classTitle", getClassTitle())
            .append("classSubject", getClassSubject())
            .append("classDept", getClassDept())
            .append("classOrg", getClassOrg())
            .append("classOverview", getClassOverview())
            .append("classAddress", getClassAddress())
            .append("classFzrid", getClassFzrid())
            .append("classFzrxm", getClassFzrxm())
            .append("classOwner", getClassOwner())
            .append("classMobile", getClassMobile())
            .append("classStarttime", getClassStarttime())
            .append("classEndtime", getClassEndtime())
            .append("orderNum", getOrderNum())
            .append("classCredit", getClassCredit())
            .append("classLimit", getClassLimit())
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
