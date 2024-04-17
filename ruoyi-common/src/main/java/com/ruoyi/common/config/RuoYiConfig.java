package com.ruoyi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoYiConfig
{
    /** 项目名称 */
    private static String name;

    /** 版本 */
    private static String version;

    /** 版权年份 */
    private static String copyrightYear;

    /** 实例演示开关 */
    private static boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;
    /** 获取腾讯secretId */
    private static String secretId;
    /** 获取腾讯secretKey */
    private static String secretKey;
    /** 获取腾讯appId */
    private static String appId;

    public static String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        RuoYiConfig.signAddress = signAddress;
    }

    /** 签到服务地址 */
    private static String signAddress;

    public static int getCcNum() {
        return ccNum;
    }

    public void setCcNum(int ccNum) {
        RuoYiConfig.ccNum = ccNum;
    }

    /** 获取上传分片设置 */
    private static int ccNum;

    public static String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        RuoYiConfig.secretId = secretId;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        RuoYiConfig.secretKey = secretKey;
    }

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        RuoYiConfig.appId = appId;
    }

    public static String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        RuoYiConfig.mediaPath = mediaPath;
    }

    /** 获取mediaPath*/
    private static String mediaPath;

    public static String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        RuoYiConfig.coverPath = coverPath;
    }

    /** 获取coverPath*/
    private static String coverPath;

    public static String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        RuoYiConfig.name = name;
    }

    public static String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        RuoYiConfig.version = version;
    }

    public static String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        RuoYiConfig.copyrightYear = copyrightYear;
    }

    public static boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        RuoYiConfig.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        RuoYiConfig.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        RuoYiConfig.addressEnabled = addressEnabled;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getProfile() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
