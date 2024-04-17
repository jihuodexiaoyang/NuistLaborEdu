package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysPractice;

/**
 * 实践学习Service接口
 * 
 * @author ruoyi
 * @date 2021-12-19
 */
public interface ISysPracticeService 
{
    /**
     * 查询实践学习
     * 
     * @param classId 实践学习主键
     * @return 实践学习
     */
    public SysPractice selectSysPracticeByClassId(Long classId);

    /**
     * 查询实践学习列表
     * 
     * @param sysPractice 实践学习
     * @return 实践学习集合
     */
    public List<SysPractice> selectSysPracticeList(SysPractice sysPractice);
    public List<SysPractice> selectSysPracticeListSelect(SysPractice sysPractice);
    public List<SysPractice> selectSysPracticeListSelected(SysPractice sysPractice);

    /**
     * 新增实践学习
     * 
     * @param sysPractice 实践学习
     * @return 结果
     */
    public int insertSysPractice(SysPractice sysPractice);

    /**
     * 修改实践学习
     * 
     * @param sysPractice 实践学习
     * @return 结果
     */
    public int updateSysPractice(SysPractice sysPractice);

    /**
     * 批量删除实践学习
     * 
     * @param classIds 需要删除的实践学习主键集合
     * @return 结果
     */
    public int deleteSysPracticeByClassIds(String classIds);

    /**
     * 删除实践学习信息
     * 
     * @param classId 实践学习主键
     * @return 结果
     */
    public int deleteSysPracticeByClassId(Long classId);
    public int changeStatus(SysPractice sysPractice);
    public int updateSelected(SysPractice sysPractice);
    public int updateDeSelected(SysPractice sysPractice);
}
