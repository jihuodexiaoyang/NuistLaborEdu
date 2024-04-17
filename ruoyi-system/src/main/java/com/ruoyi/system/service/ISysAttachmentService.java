package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysAttachment;

/**
 * 成绩附件Service接口
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
public interface ISysAttachmentService 
{
    /**
     * 查询成绩附件
     * 
     * @param id 成绩附件主键
     * @return 成绩附件
     */
    public SysAttachment selectSysAttachmentById(Long id);

    /**
     * 查询成绩附件列表
     * 
     * @param sysAttachment 成绩附件
     * @return 成绩附件集合
     */
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment);

    /**
     * 新增成绩附件
     * 
     * @param sysAttachment 成绩附件
     * @return 结果
     */
    public int insertSysAttachment(SysAttachment sysAttachment);

    /**
     * 修改成绩附件
     * 
     * @param sysAttachment 成绩附件
     * @return 结果
     */
    public int updateSysAttachment(SysAttachment sysAttachment);

    /**
     * 批量删除成绩附件
     * 
     * @param ids 需要删除的成绩附件主键集合
     * @return 结果
     */
    public int deleteSysAttachmentByIds(String ids);

    /**
     * 删除成绩附件信息
     * 
     * @param id 成绩附件主键
     * @return 结果
     */
    public int deleteSysAttachmentById(Long id);
}
