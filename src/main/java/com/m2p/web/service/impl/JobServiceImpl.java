package com.m2p.web.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.web.model.MakerLoadRequest;
import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.service.JobService;
import com.m2p.web.service.ParentLoadRequestService;
import com.m2p.web.util.DataFile;
import com.m2p.web.util.JsonRequestUtility;
import com.m2p.web.util.MultipartUtility;
import com.m2p.web.util.RequestStatus;
import com.m2p.web.util.YappayException;
import com.m2p.web.util.YappayExceptionConstant;
import com.m2p.web.util.YappayResponse;

/**
 * Created by sriramk on 27-07-2015.
 */
@Service
public class JobServiceImpl implements JobService {

	@Value("${jobs.url}")
	private String serviceUrl;

	private String charset = "UTF-8";

	private static Logger jobServiceLogger = LoggerFactory.getLogger(JobServiceImpl.class);

	private List<String> columnList = new ArrayList<String>(
			Arrays.asList("toEntityId", "amount", "isDebit", "description"));
	
	@Autowired private ParentLoadRequestService parentLoadRequestService;
	
	private String filePath = "/home/senthil/fileimport/";

	@Override
	public Map<String, Object> uploadCustomerAndLoad(DataFile datafile, String fileHeader) throws YappayException {
		String requestURL = serviceUrl + "/web/customerImport";
		MultipartUtility multipart = null;
		try {
			multipart = new MultipartUtility(requestURL, charset);
			multipart.addHeaderField("User-Agent", "CodeJava");
			multipart.addHeaderField("Test-Header", "Header-Value");

			multipart.addFormField("fileHeader",
					"terminalId,terminalYapcode,entityId,loadAmount,title,firstName,lastName,specialDate,gender,contactNo,businessId,emailAddress,address,address2,city,state,country,pincode,idType,idNumber,countryofIssue,businessType,entityType,dependent,kitNo");
			multipart.addFilePart("fileData", multipart.convert(datafile.getFileData()));

			List<String> response = multipart.finish();
			ObjectMapper mapper = new ObjectMapper();
			YappayResponse yappayResponse = mapper.readValue(response.get(0), YappayResponse.class);
			if (yappayResponse.getResult().equals(false)) {
				throw new YappayException("File Uploaded with Errors", "File Uploaded with Errors", null, null, null);
			}
		} catch (IOException e) {
			throw new YappayException("Could not Upload the File", e.getMessage(), null, null, null);
		}
		return null;
	}

	@Override
	public Map<String, Object> getUploadList(Integer pageNo, Integer pageSize) throws YappayException {
		String requestURL = serviceUrl + "/web/listImports?pageNumber=" + pageNo + "&pageSize=" + pageSize;
		JsonRequestUtility jsonRequest = null;

		try {
			jsonRequest = new JsonRequestUtility(requestURL, charset, HttpMethod.GET);
			jsonRequest.addHeader("CSRF", ((Long) System.currentTimeMillis()).toString());
			List<String> response = jsonRequest.finish();
			String result = response.get(0);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(result, HashMap.class);
		} catch (IOException e) {
			throw new YappayException("Could not retrieve the data", e.getMessage(), null, null, null);
		}
	}

	@Override
	public FileSystemResource getFile(String filePath) throws Exception {
		String[] fileNameWE = filePath.split("/");
		String fileName = fileNameWE[fileNameWE.length - 1];
		return new FileSystemResource(downloadFile(serviceUrl + "/web/getFile?filePath=" + filePath,
				fileName.substring(0, fileName.indexOf(".")), "xlsx"));
	}

	private String downloadFile(String url, String filenamePrefix, String fileExtension) throws Exception {
		// request setup...
		URLConnection request = null;
		request = new URL(url).openConnection();
		InputStream in = request.getInputStream();
		File downloadedFile = File.createTempFile(filenamePrefix, fileExtension);
		FileOutputStream out = new FileOutputStream(downloadedFile);
		byte[] buffer = new byte[1024];
		int len = in.read(buffer);
		while (len != -1) {
			out.write(buffer, 0, len);
			len = in.read(buffer);
			if (Thread.interrupted()) {
				throw new InterruptedException();
			}
		}
		in.close();
		out.close();
		return downloadedFile.getAbsolutePath();
	}

	@Override
	public ParentMakerLoadRequest uploadLoad(DataFile datafile, String fileHeader) throws YappayException {

		MultipartFile uploadedFile = datafile.getFileData();
		List<MakerLoadRequest> reqList = new ArrayList<>();
		ParentMakerLoadRequest parentRequest = new ParentMakerLoadRequest();
		String fileName = filePath+"import-load-"+ new Date().getTime()+".csv";
		parentRequest.setImportFileName(fileName);
		try {
			Workbook loadWorkBook = null;
			loadWorkBook = WorkbookFactory.create(uploadedFile.getInputStream());
			parentRequest = createUpdateCustomerFromFile(fileHeader, loadWorkBook, fileName, parentRequest);
		} catch (YappayException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new YappayException(YappayExceptionConstant.ENTITY_IMPORT_EXCEPTION.toString(), e.getMessage(),
					YappayExceptionConstant.ENTITY_IMPORT_EXCEPTION, null);
		}
		parentRequest.setRequestStatus(RequestStatus.PENDING);
		parentRequest = parentLoadRequestService.upsert(parentRequest);
		return parentRequest;
	}

	@Transactional
	private ParentMakerLoadRequest createUpdateCustomerFromFile(String fileHeader,
			Workbook loadWorkBook, String fileName, ParentMakerLoadRequest parentRequest)
					throws YappayException, IOException {

		Sheet loadSheet = null;
		loadSheet = loadWorkBook.getSheetAt(0);
		Integer totalNumberOfRows = loadWorkBook.getSheetAt(0).getPhysicalNumberOfRows() - 1;
		Row rowData = null;
		List<MakerLoadRequest> loadReqList = new ArrayList<>();
		FileWriter writer = null;
		writer = new FileWriter(fileName);
		writer.append("Entity Id");
		writer.append(',');
		writer.append("Load Amount");
		writer.append('\n');
		Double totalLoadAmount = new Double("0");
		for (int rowNum = 1; rowNum <= totalNumberOfRows; rowNum++) {
			rowData = loadSheet.getRow(rowNum);
			Map<String, Object> reqData = new HashMap<>();
			JSONObject jsonObject = new JSONObject();
			for (Integer colNum = 0; colNum < 2; colNum++) {
				Cell cellData = rowData.getCell(colNum);
				String cellValue = cellData != null ? cellData.toString() : null;
				reqData.put(columnList.get(colNum), cellValue);
				writer.append(cellValue);
				writer.append(",");
				if (colNum == 1) {
					try {
						totalLoadAmount += Double.valueOf(cellValue);
					} catch(NumberFormatException e) {
						
					}
				}
			}
			writer.append('\n');
			MakerLoadRequest loadRequest = new MakerLoadRequest();
			jsonObject.putAll(reqData);
			loadRequest.setRequestData(jsonObject.toJSONString());
			loadReqList.add(loadRequest);
		}
		writer.flush();
		writer.close();
		parentRequest.setTotalLoadAmount(totalLoadAmount);
		parentRequest.setListRequest(loadReqList);
		return parentRequest;
	}

	private Map<Integer, String> buildFieldNameColumnIndexMap(String fileHeader) {

		Map<Integer, String> columnFieldNameMap = null;
		String employeeImportHeader = fileHeader;
		String[] headerArray = employeeImportHeader.split(",");
		if (headerArray.length > 0) {
			columnFieldNameMap = new HashMap<>();
			for (int a = 0; a < headerArray.length; a++) {
				columnFieldNameMap.put(a, headerArray[a]);
			}

		}
		return columnFieldNameMap;
	}

}
