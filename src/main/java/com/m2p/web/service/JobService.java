package com.m2p.web.service;

import java.util.Map;

import org.springframework.core.io.FileSystemResource;

import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.util.DataFile;
import com.m2p.web.util.YappayException;

/**
 * Created by sriramk on 27-07-2015.
 */
public interface JobService {

    Map<String, Object> uploadCustomerAndLoad(DataFile datafile, String fileHeader) throws YappayException;

    Map<String,Object> getUploadList(Integer pageNo, Integer pageSize) throws YappayException;

    FileSystemResource getFile(String filePath) throws Exception;
    
    ParentMakerLoadRequest uploadLoad(DataFile datafile, String fileHeader) throws YappayException;
    
}
