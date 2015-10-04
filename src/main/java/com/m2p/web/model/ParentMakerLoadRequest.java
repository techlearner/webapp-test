package com.m2p.web.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.m2p.web.util.RequestStatus;
import com.m2p.web.util.RequestType;

@Document
public class ParentMakerLoadRequest {

	@Id
	private String id;

	private List<MakerLoadRequest> listRequest;
	
	private String importFileName;
	
	private String errorFileName;
	
	private String successFileName;
	
	private RequestStatus requestStatus;
	
	private RequestType type;
	
	private Double totalLoadAmount;
	
	private Boolean deleted;
	
    private String creator;
    
    private Date created;
    
    private String changer;
    
    private Date changed;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MakerLoadRequest> getListRequest() {
		return listRequest;
	}

	public void setListRequest(List<MakerLoadRequest> listRequest) {
		this.listRequest = listRequest;
	}

	public String getImportFileName() {
		return importFileName;
	}

	public void setImportFileName(String importFileName) {
		this.importFileName = importFileName;
	}

	public String getErrorFileName() {
		return errorFileName;
	}

	public void setErrorFileName(String errorFileName) {
		this.errorFileName = errorFileName;
	}

	public String getSuccessFileName() {
		return successFileName;
	}

	public void setSuccessFileName(String successFileName) {
		this.successFileName = successFileName;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus status) {
		this.requestStatus = status;
	}

	public RequestType getType() {
		return type;
	}

	public void setType(RequestType type) {
		this.type = type;
	}

	public Double getTotalLoadAmount() {
		return totalLoadAmount;
	}

	public void setTotalLoadAmount(Double totalLoadAmount) {
		this.totalLoadAmount = totalLoadAmount;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getChanger() {
		return changer;
	}

	public void setChanger(String changer) {
		this.changer = changer;
	}

	public Date getChanged() {
		return changed;
	}

	public void setChanged(Date changed) {
		this.changed = changed;
	}
}
