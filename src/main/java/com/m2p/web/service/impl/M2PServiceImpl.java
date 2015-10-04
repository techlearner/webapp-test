package com.m2p.web.service.impl;

import com.m2p.web.service.M2PService;
import com.m2p.web.util.JsonRequestUtility;
import com.m2p.web.util.YappayException;
import com.m2p.web.util.YappayResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by sriramk on 31-07-2015.
 */
@Service
public class M2PServiceImpl implements M2PService {

    @Value("${m2p.url}")
    private String serviceUrl;

    private String charset = "UTF-8";

    @Override
    public YappayResponse registerCustomer(Map<String, Object> customer) throws YappayException {
        String url = serviceUrl + "/registration-manager/register";
        try {
            JsonRequestUtility requestUtility = new JsonRequestUtility(url,charset, HttpMethod.POST);
            requestUtility.addHeader("Authorization","Basic YWRtaW46YWRtaW4=");
            requestUtility.addRequestBody(customer);

            YappayResponse response =  requestUtility.getYappayResponse(requestUtility.finish());
            if(response.getResult()!=null)
                return response;
            else
                throw response.getException();
        } catch (IOException e) {
            throw new YappayException("Could not Register Customer", e.getMessage(), null, null ,null);
        }
    }

    @Override
    public YappayResponse amountLoad(Map<String, Object> amountLoad) throws YappayException {
        String url = serviceUrl + "/txn-manager/create";
        try {
            JsonRequestUtility requestUtility = new JsonRequestUtility(url,charset, HttpMethod.POST);
            requestUtility.addHeader("Authorization","Basic YWRtaW46YWRtaW4=");
            requestUtility.addHeader("TENANT","M2P");
            requestUtility.addRequestBody(amountLoad);
            YappayResponse response =  requestUtility.getYappayResponse(requestUtility.finish());
            if(response.getResult()!=null)
                return response;
            else
                throw response.getException();
        } catch (IOException e) {
            throw new YappayException("Could not Load Customer", e.getMessage(), null, null ,null);
        }
    }

    @Override
    public YappayResponse amountRefund(Map<String, Object> amountRefund) throws YappayException {
        String url = serviceUrl + "/business-entity-manager/amountRefund";
        try {
            JsonRequestUtility requestUtility = new JsonRequestUtility(url,charset, HttpMethod.POST);
            requestUtility.addHeader("Authorization","Basic YWRtaW46YWRtaW4=");
            requestUtility.addRequestBody(amountRefund);
            YappayResponse response =  requestUtility.getYappayResponse(requestUtility.finish());
            if(response.getResult()!=null)
                return response;
            else
                throw response.getException();
        } catch (IOException e) {
            throw new YappayException("Could not Load Customer", e.getMessage(), null, null ,null);
        }
    }

    @Override
    public YappayResponse getBalance(String entityId) throws YappayException {
        String url = serviceUrl + "/business-entity-manager/fetchbalance/"+entityId;
        try {
            JsonRequestUtility requestUtility = new JsonRequestUtility(url,charset, HttpMethod.GET);
            requestUtility.addHeader("Authorization","Basic YWRtaW46YWRtaW4=");
            return requestUtility.getYappayResponse(requestUtility.finish());
        } catch (IOException e) {
            throw new YappayException("Could not Load Customer", e.getMessage(), null, null ,null);
        }
    }
}
