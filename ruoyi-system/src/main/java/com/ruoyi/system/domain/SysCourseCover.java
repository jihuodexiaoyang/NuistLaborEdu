package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 封面图片对象 sys_course_cover
 * 
 * @author ruoyi
 * @date 2021-12-26
 */
public class SysCourseCover extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** UUID */
    private String fileUuid;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件后缀名 */
    @Excel(name = "文件后缀名")
    private String fileExt;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private BigDecimal fileSize;

    public void setFileUuid(String fileUuid) 
    {
        this.fileUuid = fileUuid;
    }

    public String getFileUuid() 
    {
        return fileUuid;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileExt(String fileExt) 
    {
        this.fileExt = fileExt;
    }

    public String getFileExt() 
    {
        return fileExt;
    }
    public void setFileSize(BigDecimal fileSize) 
    {
        this.fileSize = fileSize;
    }

    public BigDecimal getFileSize() 
    {
        return fileSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileUuid", getFileUuid())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileExt", getFileExt())
            .append("fileSize", getFileSize())
            .toString();
    }
}
