package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysCredit;
import com.ruoyi.system.domain.SysCreditPrac;

/**
 * 专业实践成绩Service接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public interface ISysCreditPracService 
{
    /**
     * 查询专业实践成绩
     * 
     * @param id 专业实践成绩主键
     * @return 专业实践成绩
     */
    public SysCreditPrac selectSysCreditPracById(Long id);

    /**
     * 查询专业实践成绩列表
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 专业实践成绩集合
     */
    public List<SysCreditPrac> selectSysCreditPracList(SysCreditPrac sysCreditPrac);
    public List<SysCredit> selectSysCreditPracDeptList(SysCredit sysCredit);

    /**
     * 新增专业实践成绩
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 结果
     */
    public int insertSysCreditPrac(SysCreditPrac sysCreditPrac);

    /**
     * 修改专业实践成绩
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 结果
     */
    public int updateSysCreditPrac(SysCreditPrac sysCreditPrac);

    /**
     * 批量删除专业实践成绩
     * 
     * @param ids 需要删除的专业实践成绩主键集合
     * @return 结果
     */
    public int deleteSysCreditPracByIds(String ids);

    /**
     * 删除专业实践成绩信息
     * 
     * @param id 专业实践成绩主键
     * @return 结果
     */
    public int deleteSysCreditPracById(Long id);
}
