package com.bank.manage.application.bean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/4/26 14:08
 */
public class UploadFileBean implements Serializable {
    /**
     * originalName : 1587881256169bala_crop.jpg
     * bucketName : goldchain
     * fileName : 19b410fe6ed64012aa17ead7a3d87a20.jpg
     */

    private String originalName;
    private String bucketName;
    private String fileName;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
