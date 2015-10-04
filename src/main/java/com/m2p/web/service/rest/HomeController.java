package com.m2p.web.service.rest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.web.model.MakerLoadRequest;
import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.service.JobService;
import com.m2p.web.service.M2PService;
import com.m2p.web.service.ParentLoadRequestService;
import com.m2p.web.util.DataFile;
import com.m2p.web.util.FileType;
import com.m2p.web.util.Paginate;
import com.m2p.web.util.RequestStatus;
import com.m2p.web.util.YappayException;
import com.m2p.web.util.YappayResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/services")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private JobService jobService;

    @Autowired
    private M2PService m2pService;
    
    @Autowired private ParentLoadRequestService parentLoadRequestService;
    
	@Value("${business.entityId}")
	private String businessEntityId;
	
	@Value("${business.type}")
	private String businessType;
	
	@Value("${business.terminalId}")
	private String businessTerminalId;
	
	@Value("${business.yapcode}")
	private String businessYapCode;
	
	@Value("${business.productId}")
	private String businessProductId;
	
	@Value("${business.txnType}")
	private String businessTxnType;
	
	@Value("${business.txnOrigin}")
	private String businessTxnOrigin;

    /**
     * Simply selects the home view to render by returning its name.
     */

    @RequestMapping(value="/import", method= RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<YappayResponse> customerImport(@RequestParam("file") MultipartFile file){
        try{
            DataFile fileData = new DataFile();
            fileData.setFileData(file);
            String fileHeader = "merchantId,entityId,loadAmount,title,firstName,lastName,specialDate,gender,contactNo,businessId,emailAddress,address,address2,city,state,country,pincode,idType,idNumber,countryofIssue,businessType,entityType,dependent,kitNo";
            Map result = jobService.uploadCustomerAndLoad(fileData,fileHeader);
            return new ResponseEntity(new YappayResponse(result, null, null), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @RequestMapping(value="/import/load", method= RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<YappayResponse> loadImport(@RequestParam("file") MultipartFile file){
        try{
            DataFile fileData = new DataFile();
            fileData.setFileData(file);
            String fileHeader = "toEntityId,amount";
            ParentMakerLoadRequest result = jobService.uploadLoad(fileData,fileHeader);
            return new ResponseEntity(new YappayResponse(result, null, null), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/uploadList", method= RequestMethod.GET)
    public ResponseEntity<YappayResponse> getUploadList(@RequestParam("pageNo") Integer pageNo,
                                                        @RequestParam("pageSize") Integer pageSize){
        try{
            return new ResponseEntity(jobService.getUploadList(pageNo, pageSize), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody public FileSystemResource getFile(@RequestParam("filePath") String filePath) {
        try {
            return jobService.getFile(filePath);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value="/customer/register", method= RequestMethod.POST)
    public ResponseEntity<YappayResponse> registerCustomer(@RequestBody Map<String,Object> customer){
        try{
            return new ResponseEntity(m2pService.registerCustomer(customer), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/customer/amountload", method= RequestMethod.POST)
     public ResponseEntity<YappayResponse> amountLoad(@RequestBody Map<String,Object> amountLoad){
        try{
            return new ResponseEntity(m2pService.amountLoad(amountLoad), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/customer/amountRefund", method= RequestMethod.POST)
    public ResponseEntity<YappayResponse> amountRefund(@RequestBody Map<String,Object> amountRefund){
        try{
            return new ResponseEntity(m2pService.amountRefund(amountRefund), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/customer/balance", method= RequestMethod.GET)
    public ResponseEntity<YappayResponse> getBalance(@RequestParam("entityId") String entityId){
        try{
            return new ResponseEntity(m2pService.getBalance(entityId), HttpStatus.OK);
        }catch(YappayException e){
            e.printStackTrace();
            return new ResponseEntity(new YappayResponse(null, e, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@RequestMapping(value="/checker/create", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<YappayResponse> saveRequest(@RequestBody String request, @RequestParam(required=true, value="reqType") String reqType) {
    	
    	MakerLoadRequest checkerLoadRequest = new MakerLoadRequest();
    	checkerLoadRequest.setComment("");
    	checkerLoadRequest.setRequestData(request);
    	checkerLoadRequest.setRequestType(RequestType.valueOf(reqType));
    	checkerLoadRequest.setRequestStatus(RequestStatus.PENDING);
    	checkerLoadRequest = parentLoadReqRepository.upsert(checkerLoadRequest);
    	return new ResponseEntity(checkerLoadRequest, HttpStatus.OK);
    }*/
    
    @RequestMapping(value="/checker", method = RequestMethod.GET)
    public ResponseEntity<YappayResponse> saveRequest(@RequestParam(required=false, value="reqStatus") String reqStatus,
    		@RequestParam(required=false, value="pageNo", defaultValue="0")Integer pageNo, @RequestParam(required=false,
    		value="pageSize", defaultValue="5")Integer pageSize) {
    	Page<ParentMakerLoadRequest> response = null;
    	Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort); 
    	if (!StringUtils.isEmpty(reqStatus)) {
    		RequestStatus status = RequestStatus.valueOf(reqStatus);
    		response = parentLoadRequestService.findByRequestStatus(status, pageNo, pageSize);
    	} else {
    		response = parentLoadRequestService.findAll(pageNo, pageSize);
    	}
    	Paginate paginate = new Paginate();
		paginate.setIsList(true);
		paginate.setPageNo(pageNo);
		paginate.setPageSize(response != null?response.getSize():0);
		paginate.setTotalElements(new Integer(response.getTotalElements()+""));
		paginate.setTotalPages(response.getTotalPages());
		return new ResponseEntity(new YappayResponse(response.getContent(), null, paginate), HttpStatus.OK);
    }
    
    @RequestMapping(value="/checker/{id}/{action}", method = RequestMethod.POST)
    public ResponseEntity<YappayResponse> checkerTakeAction(@PathVariable("id")String id,
    		@PathVariable("action") String action ) throws IOException {
    	RequestStatus checkerAction = RequestStatus.valueOf(action);
    	ParentMakerLoadRequest request = parentLoadRequestService.findById(id);
		ObjectMapper mapper = new ObjectMapper();

    	if (RequestStatus.REJECT.equals(checkerAction)) {
    		request.setRequestStatus(checkerAction);
    		parentLoadRequestService.upsert(request);
    	} else if (RequestStatus.APPROVE.equals(checkerAction)) {
    		List<MakerLoadRequest> makerLoadReqList = request.getListRequest();
    		String errorFileName = request.getImportFileName() != null?
					request.getImportFileName().replace("import-load", "error-load") : "error-load.csv";
			String successFileName = request.getImportFileName() != null?
					request.getImportFileName().replace("import-load", "success-load") : "success-load.csv";
			FileWriter succesWriter  = new FileWriter(successFileName);
			FileWriter failWriter = new FileWriter(errorFileName);
			Map<String, String> defaultValues = new HashMap<String, String>();
			defaultValues.put("fromEntityId", businessEntityId);
			defaultValues.put("yapcode", businessYapCode);
			defaultValues.put("productId", businessProductId);
			defaultValues.put("transactionType", businessTxnType);
			defaultValues.put("transactionOrigin", businessTxnOrigin);
			defaultValues.put("businessType", businessType);
			defaultValues.put("businessEntityId", businessType);
			
    		for (MakerLoadRequest makerLoadRequest : makerLoadReqList) {
    			Map<String,Object> map = new HashMap<String,Object>();
    				try {
    					map = mapper.readValue(makerLoadRequest.getRequestData(), 
  						      new TypeReference<HashMap<String, Object>>(){});
    					map.putAll(defaultValues);
						YappayResponse response = m2pService.amountLoad(map);
						if (response != null) {
							succesWriter.write(""+response.getResult());
							succesWriter.write(",");
							succesWriter.write(makerLoadRequest.getRequestData());
							succesWriter.write(",");
						}
					} catch (YappayException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						failWriter.write(e.getDetailMessage());
						failWriter.write(",");
						failWriter.write(makerLoadRequest.getRequestData());
						failWriter.write(",");
					}  catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						failWriter.write(e1.getMessage());
						failWriter.write(",");
						failWriter.write(makerLoadRequest.getRequestData());
						failWriter.write(",");
					}
    					 
    			//} 
    				failWriter.write("\n");
    				succesWriter.write("\n");
    			
    		}
    		 failWriter.flush();
    		 succesWriter.flush();
    		 failWriter.close();
    		 succesWriter.close();
    		 request.setErrorFileName(errorFileName);
    		 request.setSuccessFileName(successFileName);
    		 request.setRequestStatus(RequestStatus.APPROVE);
    		 parentLoadRequestService.upsert(request);
    	}
    	return new ResponseEntity(request, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getFile/{id}/{fileType}", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody public FileSystemResource getLoadFile(@PathVariable("id") String id,
    		@PathVariable("fileType")FileType fileType) {
    	String fileName = "";
    	if (fileType != null && id != null) {
    		ParentMakerLoadRequest request = parentLoadRequestService.findById(id);
    		if (FileType.IMPORT.equals(fileType)) {
    			fileName = request.getImportFileName();
    		} else if (FileType.SUCCESS.equals(fileType)) {
    			fileName = request.getSuccessFileName();
    		} else if(FileType.ERROR.equals(fileType)) {
    			fileName = request.getErrorFileName();
    		}
    	}
    	return new FileSystemResource(new File(fileName));
    }
    	
    @RequestMapping(value="/loggedinUser", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<YappayResponse> user() {
    //	.getClass().SecurityContextHolderAwareRequestWrapper.
       ;
      return  new ResponseEntity(new YappayResponse(SecurityContextHolder.getContext().
    		  getAuthentication().getName(), null, null), HttpStatus.OK);
    }
    
}
