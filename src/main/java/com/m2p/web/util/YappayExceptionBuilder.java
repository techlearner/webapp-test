package com.m2p.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by sriramk on 10/25/2014.
 */
@Component
public class YappayExceptionBuilder {

    private ResourceBundleMessageSource messageSource;

    public YappayException build(String code, String[] args, Locale languageCode, List<String> fieldErrors) {
        String message = this.exceptionMessage(code, args, languageCode);
        String errorCode = this.exceptionMessage(code,args,null);
        return new YappayException(message, message, languageCode.getLanguage(), errorCode,fieldErrors);

    }

    public ResponseEntity<YappayResponse> getException(Exception e) {
        e.printStackTrace();
        YappayResponse response = new YappayResponse();
        YappayException exception = new YappayException();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return new ResponseEntity<YappayResponse>(mapper.readValue(((HttpServerErrorException)e)
                    .getResponseBodyAsString(),YappayResponse.class), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public String exceptionMessage(String code, String[] args, Locale languageCode) {
        return messageSource.getMessage(code, args, languageCode);
    }

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
