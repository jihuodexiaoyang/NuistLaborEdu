package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成绩统计对象 sys_chengji
 * 
 * @author ruoyi
 * @date 2022-03-17
 */
public class SysChengji extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String loginName;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userName;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 用户性别（0男 1女 2未知） */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String userStatus;

    /** 劳动文化讲座签到 */
    @Excel(name = "劳动文化讲座签到")
    private Long sign1;

    /** 劳动文化讲座学分 */
    @Excel(name = "劳动文化讲座学分")
    private Long credit1;

    /** 劳动文化讲座合格状态 */
    @Excel(name = "劳动文化讲座合格状态")
    private String hgzt1;

    /** 校园劳动签到 */
    @Excel(name = "校园劳动签到")
    private Long sign2;

    /** 校园劳动学分 */
    @Excel(name = "校园劳动学分")
    private Long credit2;

    /** 校园劳动合格状态 */
    @Excel(name = "校园劳动合格状态")
    private String hgzt2;

    /** 社会服务签到 */
    @Excel(name = "社会服务签到")
    private Long sign3;

    /** 社会服务学分 */
    @Excel(name = "社会服务学分")
    private Long credit3;

    /** 社会服务合格状态 */
    @Excel(name = "社会服务合格状态")
    private String hgzt3;

    /** 线上理论成绩 */
    @Excel(name = "线上理论成绩")
    private BigDecimal credit4;

    /** 专业实践成绩 */
    @Excel(name = "专业实践成绩")
    private BigDecimal credit5;

    /** 两门总成绩 */
    @Excel(name = "两门总成绩")
    private BigDecimal credit6;

    /** 最终成绩 */
    @Excel(name = "最终成绩")
    private String totalCredit;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
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
    public void setUserStatus(String userStatus) 
    {
        this.userStatus = userStatus;
    }

    public String getUserStatus() 
    {
        return userStatus;
    }
    public void setSign1(Long sign1) 
    {
        this.sign1 = sign1;
    }

    public Long getSign1() 
    {
        return sign1;
    }
    public void setCredit1(Long credit1) 
    {
        this.credit1 = credit1;
    }

    public Long getCredit1() 
    {
        return credit1;
    }
    public void setHgzt1(String hgzt1) 
    {
        this.hgzt1 = hgzt1;
    }

    public String getHgzt1() 
    {
        return hgzt1;
    }
    public void setSign2(Long sign2) 
    {
        this.sign2 = sign2;
    }

    public Long getSign2() 
    {
        return sign2;
    }
    public void setCredit2(Long credit2) 
    {
        this.credit2 = credit2;
    }

    public Long getCredit2() 
    {
        return credit2;
    }
    public void setHgzt2(String hgzt2) 
    {
        this.hgzt2 = hgzt2;
    }

    public String getHgzt2() 
    {
        return hgzt2;
    }
    public void setSign3(Long sign3) 
    {
        this.sign3 = sign3;
    }

    public Long getSign3() 
    {
        return sign3;
    }
    public void setCredit3(Long credit3) 
    {
        this.credit3 = credit3;
    }

    public Long getCredit3() 
    {
        return credit3;
    }
    public void setHgzt3(String hgzt3) 
    {
        this.hgzt3 = hgzt3;
    }

    public String getHgzt3() 
    {
        return hgzt3;
    }
    public void setCredit4(BigDecimal credit4) 
    {
        this.credit4 = credit4;
    }

    public BigDecimal getCredit4() 
    {
        return credit4;
    }
    public void setCredit5(BigDecimal credit5) 
    {
        this.credit5 = credit5;
    }

    public BigDecimal getCredit5() 
    {
        return credit5;
    }
    public void setCredit6(BigDecimal credit6) 
    {
        this.credit6 = credit6;
    }

    public BigDecimal getCredit6() 
    {
        return credit6;
    }
    public void setTotalCredit(String totalCredit) 
    {
        this.totalCredit = totalCredit;
    }

    public String getTotalCredit() 
    {
        return totalCredit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("loginName", getLoginName())
            .append("userName", getUserName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("email", getEmail())
            .append("sex", getSex())
            .append("userStatus", getUserStatus())
            .append("sign1", getSign1())
            .append("credit1", getCredit1())
            .append("hgzt1", getHgzt1())
            .append("sign2", getSign2())
            .append("credit2", getCredit2())
            .append("hgzt2", getHgzt2())
            .append("sign3", getSign3())
            .append("credit3", getCredit3())
            .append("hgzt3", getHgzt3())
            .append("credit4", getCredit4())
            .append("credit5", getCredit5())
            .append("credit6", getCredit6())
            .append("totalCredit", getTotalCredit())
            .toString();
    }
}
