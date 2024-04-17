package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线上教学成绩对象 sys_credit_online
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public class SysCreditOnline extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private String userXuehao;

    /** 成绩 */
    @Excel(name = "成绩")
    private BigDecimal userCredit;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserXuehao(String userXuehao) 
    {
        this.userXuehao = userXuehao;
    }

    public String getUserXuehao() 
    {
        return userXuehao;
    }
    public void setUserCredit(BigDecimal userCredit) 
    {
        this.userCredit = userCredit;
    }

    public BigDecimal getUserCredit() 
    {
        return userCredit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userXuehao", getUserXuehao())
            .append("userCredit", getUserCredit())
            .toString();
    }
}
