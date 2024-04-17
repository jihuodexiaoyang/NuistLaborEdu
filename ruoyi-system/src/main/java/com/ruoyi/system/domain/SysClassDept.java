package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程部门对应对象 sys_class_dept
 * 
 * @author ruoyi
 * @date 2022-03-06
 */
public class SysClassDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long classId;

    /** 课程类型 */
    private String classType;

    /** 组织编号 */
    private Long classDept;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
    }
    public void setClassDept(Long classDept) 
    {
        this.classDept = classDept;
    }

    public Long getClassDept() 
    {
        return classDept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("classType", getClassType())
            .append("classDept", getClassDept())
            .toString();
    }
}
