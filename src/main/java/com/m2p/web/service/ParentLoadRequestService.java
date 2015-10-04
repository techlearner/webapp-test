package com.m2p.web.service;

import org.springframework.data.domain.Page;

import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.util.RequestStatus;

public interface ParentLoadRequestService {

	ParentMakerLoadRequest upsert(Object reqObj);
	
	ParentMakerLoadRequest findById(String pkey);
	
	Page<ParentMakerLoadRequest> findByRequestStatus(RequestStatus status,
			int pageNo, int pageSize);
	
	Page<ParentMakerLoadRequest> findAll(int pageNo, int pageSize);
	
}
