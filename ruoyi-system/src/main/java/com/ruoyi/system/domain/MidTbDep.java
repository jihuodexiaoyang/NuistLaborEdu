package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 部门中间对象 mid_tb_dep
 * 
 * @author ruoyi
 * @date 2022-03-31
 */
public class MidTbDep extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门编号 */
    @Excel(name = "部门编号")
    private Long depId;

    /** 部门唯一编码 */
    @Excel(name = "部门唯一编码")
    private String depCode;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String depName;

    /** 上级部门编号 */
    @Excel(name = "上级部门编号")
    private Long parentId;

    /** 上级部门编码 */
    @Excel(name = "上级部门编码")
    private String parentCode;

    /** 有效标志 */
    @Excel(name = "有效标志")
    private String yxbz;

    public void setDepId(Long depId) 
    {
        this.depId = depId;
    }

    public Long getDepId() 
    {
        return depId;
    }
    public void setDepCode(String depCode) 
    {
        this.depCode = depCode;
    }

    public String getDepCode() 
    {
        return depCode;
    }
    public void setDepName(String depName) 
    {
        this.depName = depName;
    }

    public String getDepName() 
    {
        return depName;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setParentCode(String parentCode) 
    {
        this.parentCode = parentCode;
    }

    public String getParentCode() 
    {
        return parentCode;
    }
    public void setYxbz(String yxbz) 
    {
        this.yxbz = yxbz;
    }

    public String getYxbz() 
    {
        return yxbz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("depId", getDepId())
            .append("depCode", getDepCode())
            .append("depName", getDepName())
            .append("parentId", getParentId())
            .append("parentCode", getParentCode())
            .append("yxbz", getYxbz())
            .toString();
    }
}
