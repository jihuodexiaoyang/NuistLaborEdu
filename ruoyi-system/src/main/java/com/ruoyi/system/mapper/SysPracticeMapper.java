package com.ruoyi.system.mapper;

import java.io.Serializable;
import java.util.List;
import com.ruoyi.system.domain.SysPractice;

/**
 * 实践学习Mapper接口
 *
 * @author ruoyi
 * @date 2021-12-25
 */
public interface SysPracticeMapper  {
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
    /**
     * 查询实践学习列表(选课)
     *
     * @param userId 实践学习
     * @return 实践学习集合
     */
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
     * 删除实践学习
     *
     * @param classId 实践学习主键
     * @return 结果
     */
    public int deleteSysPracticeByClassId(Long classId);

    /**
     * 批量删除实践学习
     *
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPracticeByClassIds(String[] classIds);
    /**
     * 更新课程已选人数
     * @param sysPractice
     * @return
     */
    public int updateSelected(SysPractice sysPractice);

    /**
     * 取消选课 更新人数
     * @param sysPractice
     * @return
     */
    public int updateDeSelected(SysPractice sysPractice);
}
