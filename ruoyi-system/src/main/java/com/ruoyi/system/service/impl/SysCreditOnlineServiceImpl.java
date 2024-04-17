package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.SysCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCreditOnlineMapper;
import com.ruoyi.system.domain.SysCreditOnline;
import com.ruoyi.system.service.ISysCreditOnlineService;
import com.ruoyi.common.core.text.Convert;

/**
 * 线上教学成绩Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Service
public class SysCreditOnlineServiceImpl implements ISysCreditOnlineService 
{
    @Autowired
    private SysCreditOnlineMapper sysCreditOnlineMapper;

    /**
     * 查询线上教学成绩
     * 
     * @param id 线上教学成绩主键
     * @return 线上教学成绩
     */
    @Override
    public SysCreditOnline selectSysCreditOnlineById(Long id)
    {
        return sysCreditOnlineMapper.selectSysCreditOnlineById(id);
    }

    /**
     * 查询线上教学成绩列表
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 线上教学成绩
     */
    @Override
    public List<SysCreditOnline> selectSysCreditOnlineList(SysCreditOnline sysCreditOnline)
    {
        return sysCreditOnlineMapper.selectSysCreditOnlineList(sysCreditOnline);
    }

    @Override
    public List<SysCredit> selectSysCreditOnlineDeptList(SysCredit sysCredit) {
        return sysCreditOnlineMapper.selectSysCreditOnlineDeptList(sysCredit);
    }

    /**
     * 新增线上教学成绩
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 结果
     */
    @Override
    public int insertSysCreditOnline(SysCreditOnline sysCreditOnline)
    {
        return sysCreditOnlineMapper.insertSysCreditOnline(sysCreditOnline);
    }

    /**
     * 修改线上教学成绩
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 结果
     */
    @Override
    public int updateSysCreditOnline(SysCreditOnline sysCreditOnline)
    {
        return sysCreditOnlineMapper.updateSysCreditOnline(sysCreditOnline);
    }

    /**
     * 批量删除线上教学成绩
     * 
     * @param ids 需要删除的线上教学成绩主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditOnlineByIds(String ids)
    {
        return sysCreditOnlineMapper.deleteSysCreditOnlineByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线上教学成绩信息
     * 
     * @param id 线上教学成绩主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditOnlineById(Long id)
    {
        return sysCreditOnlineMapper.deleteSysCreditOnlineById(id);
    }
}
