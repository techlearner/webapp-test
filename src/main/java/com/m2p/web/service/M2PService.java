package com.m2p.web.service;

import com.m2p.web.util.YappayException;
import com.m2p.web.util.YappayResponse;

import java.util.Map;

/**
 * Created by sriramk on 31-07-2015.
 */
public interface M2PService {

    YappayResponse registerCustomer(Map<String,Object> customer) throws YappayException;

    YappayResponse amountLoad(Map<String, Object> amountLoad) throws YappayException;

    YappayResponse amountRefund(Map<String, Object> amountRefund) throws YappayException;

    YappayResponse getBalance(String entityId) throws YappayException;
}
