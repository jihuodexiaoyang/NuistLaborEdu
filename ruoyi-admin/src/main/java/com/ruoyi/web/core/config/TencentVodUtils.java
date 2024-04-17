package com.ruoyi.web.core.config;

import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.ruoyi.common.config.VodResponse;
import com.ruoyi.common.config.RuoYiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 腾讯VOD工具类
 */
public class TencentVodUtils {
    private static Logger logger = LoggerFactory.getLogger(TencentVodUtils.class);

    /**
     * 上传文件
     * @param mediaPath
     * @param coverPath
     */
    public static VodResponse uploadMedia(String mediaPath, String coverPath){
        VodUploadClient client = new VodUploadClient(RuoYiConfig.getSecretId(), RuoYiConfig.getSecretKey());
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(mediaPath);
        if(!"".equals(coverPath)&&coverPath!=null){
            request.setCoverFilePath(coverPath);
        }
        request.setConcurrentUploadNumber(RuoYiConfig.getCcNum());
        try {
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            logger.info("上传成功,文件ID = {},文件播放URL = {},封面URL = {}", response.getFileId(),response.getMediaUrl(),response.getCoverUrl());
            return new VodResponse(response.getFileId(),response.getMediaUrl(),response.getCoverUrl(),response.getRequestId());
        } catch (Exception e) {
            // 业务方进行异常处理
            logger.error("上传失败", e);
            return null;
        }
    }
}
