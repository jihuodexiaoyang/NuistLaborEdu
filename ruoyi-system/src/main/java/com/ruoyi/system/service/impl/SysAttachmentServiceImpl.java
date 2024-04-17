package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysAttachmentMapper;
import com.ruoyi.system.domain.SysAttachment;
import com.ruoyi.system.service.ISysAttachmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成绩附件Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Service
public class SysAttachmentServiceImpl implements ISysAttachmentService 
{
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    /**
     * 查询成绩附件
     * 
     * @param id 成绩附件主键
     * @return 成绩附件
     */
    @Override
    public SysAttachment selectSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.selectSysAttachmentById(id);
    }

    /**
     * 查询成绩附件列表
     * 
     * @param sysAttachment 成绩附件
     * @return 成绩附件
     */
    @Override
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.selectSysAttachmentList(sysAttachment);
    }

    /**
     * 新增成绩附件
     * 
     * @param sysAttachment 成绩附件
     * @return 结果
     */
    @Override
    public int insertSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setUploadTime(DateUtils.getNowDate());
        return sysAttachmentMapper.insertSysAttachment(sysAttachment);
    }

    /**
     * 修改成绩附件
     * 
     * @param sysAttachment 成绩附件
     * @return 结果
     */
    @Override
    public int updateSysAttachment(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.updateSysAttachment(sysAttachment);
    }

    /**
     * 批量删除成绩附件
     * 
     * @param ids 需要删除的成绩附件主键
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentByIds(String ids)
    {
        return sysAttachmentMapper.deleteSysAttachmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成绩附件信息
     * 
     * @param id 成绩附件主键
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.deleteSysAttachmentById(id);
    }
}
