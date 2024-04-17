package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户中间对象 mid_tb_user
 * 
 * @author ruoyi
 * @date 2022-03-31
 */
public class    MidTbUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 流水号 */
    @Excel(name = "流水号")
    private Long userId;

    /** 学号 */
    @Excel(name = "学号")
    private String certId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别代码 */
    @Excel(name = "性别代码")
    private String xbdm;

    /** 性别名称 */
    @Excel(name = "性别名称")
    private String sex;

    /** 身份证号(可空) */
    @Excel(name = "身份证号(可空)")
    private String idCard;

    /** 手机号码(可空) */
    @Excel(name = "手机号码(可空)")
    private String mobile;

    /** 邮箱(可空) */
    @Excel(name = "邮箱(可空)")
    private String email;

    /** 部门流水号 */
    @Excel(name = "部门流水号")
    private Long depId;

    /** 部门编码 */
    @Excel(name = "部门编码")
    private String depCode;

    /** 身份类型代码 */
    @Excel(name = "身份类型代码")
    private String sflxdm;

    /** 身份类型名称(本科生、预科生、研究生、教职工等) */
    @Excel(name = "身份类型名称(本科生、预科生、研究生、教职工等)")
    private String sflxmc;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String zymc;

    /** 年级名称 */
    @Excel(name = "年级名称")
    private String njmc;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String bjmc;

    /** 班级代码 */
    @Excel(name = "班级代码")
    private String bjdm;

    /** 年级代码 */
    @Excel(name = "年级代码")
    private String njdm;

    /** 专业代码 */
    @Excel(name = "专业代码")
    private String zydm;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCertId(String certId) 
    {
        this.certId = certId;
    }

    public String getCertId() 
    {
        return certId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setXbdm(String xbdm) 
    {
        this.xbdm = xbdm;
    }

    public String getXbdm() 
    {
        return xbdm;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
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
    public void setSflxdm(String sflxdm) 
    {
        this.sflxdm = sflxdm;
    }

    public String getSflxdm() 
    {
        return sflxdm;
    }
    public void setSflxmc(String sflxmc) 
    {
        this.sflxmc = sflxmc;
    }

    public String getSflxmc() 
    {
        return sflxmc;
    }
    public void setZymc(String zymc) 
    {
        this.zymc = zymc;
    }

    public String getZymc() 
    {
        return zymc;
    }
    public void setNjmc(String njmc) 
    {
        this.njmc = njmc;
    }

    public String getNjmc() 
    {
        return njmc;
    }
    public void setBjmc(String bjmc) 
    {
        this.bjmc = bjmc;
    }

    public String getBjmc() 
    {
        return bjmc;
    }
    public void setBjdm(String bjdm) 
    {
        this.bjdm = bjdm;
    }

    public String getBjdm() 
    {
        return bjdm;
    }
    public void setNjdm(String njdm) 
    {
        this.njdm = njdm;
    }

    public String getNjdm() 
    {
        return njdm;
    }
    public void setZydm(String zydm) 
    {
        this.zydm = zydm;
    }

    public String getZydm() 
    {
        return zydm;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("certId", getCertId())
            .append("name", getName())
            .append("xbdm", getXbdm())
            .append("sex", getSex())
            .append("idCard", getIdCard())
            .append("mobile", getMobile())
            .append("email", getEmail())
            .append("depId", getDepId())
            .append("depCode", getDepCode())
            .append("sflxdm", getSflxdm())
            .append("sflxmc", getSflxmc())
            .append("zymc", getZymc())
            .append("njmc", getNjmc())
            .append("bjmc", getBjmc())
            .append("bjdm", getBjdm())
            .append("njdm", getNjdm())
            .append("zydm", getZydm())
            .toString();
    }
}
