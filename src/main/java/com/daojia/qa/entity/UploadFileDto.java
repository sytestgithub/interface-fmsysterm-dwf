package com.daojia.qa.entity;

/**
 * Created by daojia on 2017-11-21.
 */
public class UploadFileDto {
    private String fileName;
    private String fileAddress;
    private String isPic;
    private String fileSize="0";
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getIsPic() {
        return isPic;
    }

    public void setIsPic(String isPic) {
        this.isPic = isPic;
    }

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
