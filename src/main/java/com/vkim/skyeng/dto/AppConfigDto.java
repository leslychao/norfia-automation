package com.vkim.skyeng.dto;

public class AppConfigDto extends BaseDto {

    private byte[] fileData;
    private String fileName;
    private long fileSize;
    private String skyengCookie;
    private String sheetName;
    private int skipRowNum;
    private int headerRowNum;
    private String lastUrl;
    private String packId;
    private long sectionToScroll;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSkyengCookie() {
        return skyengCookie;
    }

    public void setSkyengCookie(String skyengCookie) {
        this.skyengCookie = skyengCookie;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getSkipRowNum() {
        return skipRowNum;
    }

    public void setSkipRowNum(int skipRowNum) {
        this.skipRowNum = skipRowNum;
    }

    public int getHeaderRowNum() {
        return headerRowNum;
    }

    public void setHeaderRowNum(int headerRowNum) {
        this.headerRowNum = headerRowNum;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public long getSectionToScroll() {
        return sectionToScroll;
    }

    public void setSectionToScroll(long sectionToScroll) {
        this.sectionToScroll = sectionToScroll;
    }
}
