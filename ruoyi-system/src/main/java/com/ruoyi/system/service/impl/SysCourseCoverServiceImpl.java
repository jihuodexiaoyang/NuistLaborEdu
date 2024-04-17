package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysCourseCoverMapper;
import com.ruoyi.system.domain.SysCourseCover;
import com.ruoyi.system.service.ISysCourseCoverService;
import com.ruoyi.common.core.text.Convert;

/**
 * 封面图片Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-12-26
 */
@Service
public class SysCourseCoverServiceImpl implements ISysCourseCoverService 
{
    @Autowired
    private SysCourseCoverMapper sysCourseCoverMapper;

    /**
     * 查询封面图片
     * 
     * @param fileUuid 封面图片主键
     * @return 封面图片
     */
    @Override
    public SysCourseCover selectSysCourseCoverByFileUuid(String fileUuid)
    {
        return sysCourseCoverMapper.selectSysCourseCoverByFileUuid(fileUuid);
    }

    /**
     * 查询封面图片列表
     * 
     * @param sysCourseCover 封面图片
     * @return 封面图片
     */
    @Override
    public List<SysCourseCover> selectSysCourseCoverList(SysCourseCover sysCourseCover)
    {
        return sysCourseCoverMapper.selectSysCourseCoverList(sysCourseCover);
    }

    /**
     * 新增封面图片
     * 
     * @param sysCourseCover 封面图片
     * @return 结果
     */
    @Override
    public int insertSysCourseCover(SysCourseCover sysCourseCover)
    {
        return sysCourseCoverMapper.insertSysCourseCover(sysCourseCover);
    }

    /**
     * 修改封面图片
     * 
     * @param sysCourseCover 封面图片
     * @return 结果
     */
    @Override
    public int updateSysCourseCover(SysCourseCover sysCourseCover)
    {
        return sysCourseCoverMapper.updateSysCourseCover(sysCourseCover);
    }

    /**
     * 批量删除封面图片
     * 
     * @param fileUuids 需要删除的封面图片主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseCoverByFileUuids(String fileUuids)
    {
        return sysCourseCoverMapper.deleteSysCourseCoverByFileUuids(Convert.toStrArray(fileUuids));
    }

    @Override
    public int insertBatch(List<SysCourseCover> list) {
        return sysCourseCoverMapper.insertBatch(list);
    }

    @Override
    public int truncateTable() {
        return sysCourseCoverMapper.truncateTable();
    }

    /**
     * 删除封面图片信息
     * 
     * @param fileUuid 封面图片主键
     * @return 结果
     */
    @Override
    public int deleteSysCourseCoverByFileUuid(String fileUuid)
    {
        return sysCourseCoverMapper.deleteSysCourseCoverByFileUuid(fileUuid);
    }
}
