package com.m2p.web.util;

import org.springframework.web.multipart.MultipartFile;

public class DataFile {
    private MultipartFile fileData;

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
}
