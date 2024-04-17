package com.ruoyi.common.config;

public class VodResponse {
    private String fileId;
    private String mediaUrl;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    private String coverUrl;
    private String requestId;
    public VodResponse(String fileId,String mediaUrl,String coverUrl,String requestId){
        this.coverUrl=coverUrl;
        this.fileId=fileId;
        this.mediaUrl=mediaUrl;
        this.requestId=requestId;
    }
}
