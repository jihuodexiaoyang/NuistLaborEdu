package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.SysCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCreditPracMapper;
import com.ruoyi.system.domain.SysCreditPrac;
import com.ruoyi.system.service.ISysCreditPracService;
import com.ruoyi.common.core.text.Convert;

/**
 * 专业实践成绩Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Service
public class SysCreditPracServiceImpl implements ISysCreditPracService 
{
    @Autowired
    private SysCreditPracMapper sysCreditPracMapper;

    /**
     * 查询专业实践成绩
     * 
     * @param id 专业实践成绩主键
     * @return 专业实践成绩
     */
    @Override
    public SysCreditPrac selectSysCreditPracById(Long id)
    {
        return sysCreditPracMapper.selectSysCreditPracById(id);
    }

    /**
     * 查询专业实践成绩列表
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 专业实践成绩
     */
    @Override
    public List<SysCreditPrac> selectSysCreditPracList(SysCreditPrac sysCreditPrac)
    {
        return sysCreditPracMapper.selectSysCreditPracList(sysCreditPrac);
    }
    /**
     * 查询专业实践成绩列表
     *
     * @param sysCredit 专业实践成绩
     * @return 专业实践成绩
     */
    @Override
    public List<SysCredit> selectSysCreditPracDeptList(SysCredit sysCredit)
    {
        return sysCreditPracMapper.selectSysCreditPracDeptList(sysCredit);
    }
    /**
     * 新增专业实践成绩
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 结果
     */
    @Override
    public int insertSysCreditPrac(SysCreditPrac sysCreditPrac)
    {
        return sysCreditPracMapper.insertSysCreditPrac(sysCreditPrac);
    }

    /**
     * 修改专业实践成绩
     * 
     * @param sysCreditPrac 专业实践成绩
     * @return 结果
     */
    @Override
    public int updateSysCreditPrac(SysCreditPrac sysCreditPrac)
    {
        return sysCreditPracMapper.updateSysCreditPrac(sysCreditPrac);
    }

    /**
     * 批量删除专业实践成绩
     * 
     * @param ids 需要删除的专业实践成绩主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditPracByIds(String ids)
    {
        return sysCreditPracMapper.deleteSysCreditPracByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专业实践成绩信息
     * 
     * @param id 专业实践成绩主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditPracById(Long id)
    {
        return sysCreditPracMapper.deleteSysCreditPracById(id);
    }
}
