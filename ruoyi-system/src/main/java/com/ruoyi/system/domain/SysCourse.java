package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 在线学习课程对象 sys_course
 * 
 * @author ruoyi
 * @date 2021-12-25
 */
public class SysCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课时编号 */
    private Long courseId;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long classId;

    /** 课时序号 */
    @Excel(name = "课时序号")
    private Integer courseSeq;

    /** 课时名称 */
    @Excel(name = "课时名称")
    private String courseName;

    /** 课时简介 */
    @Excel(name = "课时简介")
    private String courseOverview;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String coursePath;

    /** 封面路径 */
    @Excel(name = "封面路径")
    private String courseCoverpath;

    /** 在线播放URL */
    @Excel(name = "在线播放URL")
    private String courseUrl;

    /** 封面URL */
    @Excel(name = "封面URL")
    private String courseCoverurl;

    /** 上传文件ID */
    @Excel(name = "上传文件ID")
    private String courseFileid;

    /** 课时状态（0正常 1停用） */
    @Excel(name = "课时状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setCourseSeq(Integer courseSeq) 
    {
        this.courseSeq = courseSeq;
    }

    public Integer getCourseSeq() 
    {
        return courseSeq;
    }
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }
    public void setCourseOverview(String courseOverview) 
    {
        this.courseOverview = courseOverview;
    }

    public String getCourseOverview() 
    {
        return courseOverview;
    }
    public void setCoursePath(String coursePath) 
    {
        this.coursePath = coursePath;
    }

    public String getCoursePath() 
    {
        return coursePath;
    }
    public void setCourseCoverpath(String courseCoverpath) 
    {
        this.courseCoverpath = courseCoverpath;
    }

    public String getCourseCoverpath() 
    {
        return courseCoverpath;
    }
    public void setCourseUrl(String courseUrl) 
    {
        this.courseUrl = courseUrl;
    }

    public String getCourseUrl() 
    {
        return courseUrl;
    }
    public void setCourseCoverurl(String courseCoverurl) 
    {
        this.courseCoverurl = courseCoverurl;
    }

    public String getCourseCoverurl() 
    {
        return courseCoverurl;
    }
    public void setCourseFileid(String courseFileid) 
    {
        this.courseFileid = courseFileid;
    }

    public String getCourseFileid() 
    {
        return courseFileid;
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
            .append("courseId", getCourseId())
            .append("classId", getClassId())
            .append("courseSeq", getCourseSeq())
            .append("courseName", getCourseName())
            .append("courseOverview", getCourseOverview())
            .append("coursePath", getCoursePath())
            .append("courseCoverpath", getCourseCoverpath())
            .append("courseUrl", getCourseUrl())
            .append("courseCoverurl", getCourseCoverurl())
            .append("courseFileid", getCourseFileid())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
