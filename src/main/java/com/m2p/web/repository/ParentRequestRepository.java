package com.m2p.web.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.m2p.web.model.ParentMakerLoadRequest;
import com.m2p.web.util.RequestStatus;

public interface ParentRequestRepository extends MongoRepository<ParentMakerLoadRequest, String> {

	Page<ParentMakerLoadRequest> findByRequestStatus(RequestStatus type, Pageable page);
	
}
