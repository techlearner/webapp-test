package com.m2p.web.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.repository.ParentRequestRepository;
import com.m2p.web.service.ParentLoadRequestService;
import com.m2p.web.util.RequestStatus;

@Service
public class ParentLoadRequestServiceImpl implements ParentLoadRequestService {
	
	@Autowired
	private ParentRequestRepository parentMakerLoadRequestRepository;
	
	@Override
	@Transactional
	public ParentMakerLoadRequest upsert(Object obj) {
		if (obj != null && obj instanceof ParentMakerLoadRequest) {
			ParentMakerLoadRequest request = (ParentMakerLoadRequest) obj;
			long time = System.currentTimeMillis();
			Timestamp timestamp = new Timestamp(time);
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			if (request.getId() == null) {
				request.setCreated(timestamp);
				request.setCreator(user);
			} else {
				request.setChanged(timestamp);
				request.setChanger(user);
			}
			request =parentMakerLoadRequestRepository.save(request);
			return request;
		}
		return null;
	}
		
	@Override
	public ParentMakerLoadRequest findById(String pkey) {
		return parentMakerLoadRequestRepository.findOne(pkey);
	}

	@Override
	public Page<ParentMakerLoadRequest> findByRequestStatus(RequestStatus status, int pageNo, int pageSize) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<ParentMakerLoadRequest> result = parentMakerLoadRequestRepository.findByRequestStatus(status, pageable);
        //Page<ParentMakerLoadRequest> result = parentMakerLoadRequestRepository.findAll(pageable);
        return result;
	}
	
	
	@Override
	public Page<ParentMakerLoadRequest> findAll(int pageNo, int pageSize) {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<ParentMakerLoadRequest> result = parentMakerLoadRequestRepository.findAll(pageable);
        return result;
	}

	public ParentRequestRepository getParentLoadRequestRepository() {
		return parentMakerLoadRequestRepository;
	}

	public void setParentLoadRequestRepository(ParentRequestRepository parentLoadRequestRepository) {
		this.parentMakerLoadRequestRepository = parentLoadRequestRepository;
	}

}
