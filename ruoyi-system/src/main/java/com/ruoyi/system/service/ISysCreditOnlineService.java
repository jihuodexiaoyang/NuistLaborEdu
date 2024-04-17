package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysCredit;
import com.ruoyi.system.domain.SysCreditOnline;

/**
 * 线上教学成绩Service接口
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
public interface ISysCreditOnlineService 
{
    /**
     * 查询线上教学成绩
     * 
     * @param id 线上教学成绩主键
     * @return 线上教学成绩
     */
    public SysCreditOnline selectSysCreditOnlineById(Long id);

    /**
     * 查询线上教学成绩列表
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 线上教学成绩集合
     */
    public List<SysCreditOnline> selectSysCreditOnlineList(SysCreditOnline sysCreditOnline);
    public List<SysCredit> selectSysCreditOnlineDeptList(SysCredit sysCredit);
    /**
     * 新增线上教学成绩
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 结果
     */
    public int insertSysCreditOnline(SysCreditOnline sysCreditOnline);

    /**
     * 修改线上教学成绩
     * 
     * @param sysCreditOnline 线上教学成绩
     * @return 结果
     */
    public int updateSysCreditOnline(SysCreditOnline sysCreditOnline);

    /**
     * 批量删除线上教学成绩
     * 
     * @param ids 需要删除的线上教学成绩主键集合
     * @return 结果
     */
    public int deleteSysCreditOnlineByIds(String ids);

    /**
     * 删除线上教学成绩信息
     * 
     * @param id 线上教学成绩主键
     * @return 结果
     */
    public int deleteSysCreditOnlineById(Long id);
}
