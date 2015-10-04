package com.m2p.web.util;

/**
 * Created by sriramk on 10/25/2014.
 */
public class YappayExceptionConstant {

    public static final String OTP_EXPIRED = "otp.expired";
    public static final String OTP_ATTEMPT_EXHAUSTED = "otp.attempt.exhausted";
    public static final String OTP_INVALID = "otp.invalid";
    public static final String VALIDATION_EXCEPTION = "validation.exception";

    public static final String MANDATORY_FIELD_MISSING = "mandatory.field.missing";
    public static final String FIELD_ALREADY_EXISTS = "field.exists";
    public static final String VALIDATOR_NOT_FOUND = "validator.not.found";
    public static final String FIELD_CANNOT_BE_EDITED = "field.cannot.edit";
    public static final String CUSTOMER_ID_INVALID = "customer.id.invalid";
    public static final String YCS_VALIDATION_FAILED = "ycs.validation.failed";
    public static final String MERCHANT_NOT_FOUND = "yappay.merchant.notfound";
    public static final String YAPCODE_VERIFICATION_FAILED = "yapcode.invalid";
    public static final String TRANSACTION_FAILED = "transaction.failed";
    public static final String INVALID_DATA = "data.invalid";
    public static final String GCM_PUSH_FAILED = "gcm.push.failed";
    public static final String TERMINAL_NOT_FOUND = "yappay.terminal.notfound";

    public static final String INVALID_CARDNO = "yappay.invalid.cardno";
    public static final String KIT_NO_MISMATCH = "yappay.kitno.mismatch";
    public static final String CARD_ALREADY_ALLOCATED = "yappay.card.allocated";
    public static final String BUS_KIT_MAPPING_NOT_FOUND = "yappay.bus.kit.mapping.not.found";

    public static final String INVALID_WALLET_ID = "yappay.wallet.id.invalid";
    public static final String WALLET_ID_MISSING = "yappay.wallet.id.missing";
    public static final String NEGATIVE_BALANCE = "yappay.balance.negative";
    public static final String DUPLICATE_CARD_NO = "yappay.duplicate.card.no";
    public static final String DUPLICATE_KIT_NO = "yappay.duplicate.kit.no";
    public static final String PRODUCT_NOT_FOUND = "yappay.product.not.found";
    public static final String INVALID_MOBILE_NO = "yappay.mobile.invalid";
    public static final String YSE_SOAP_REQUEST_FAILED = "yappay.yse.soap.failed";
    public static final String ZIP_CASH_REQUEST_FAILED = "yappay.zipcash.request.failed";
    public static final String NULL_POINTER = "yappay.null.pointer";
    public static final String BUS_ID_NULL = "yappay.bus.id.null";
    public static final String BUS_TYPE_NULL = "yappay.bus.type.null";
    public static final String WALLET_BID_MISSING = "yappay.wallet.bid.missing";
    public static final String WALLET_NOT_FOUND = "yappay.wallet.not.found";
    public static final String TRANSACTION_ID_INVALID = "yappay.invalid.txid";
    public static final String ENTITY_ID_CARD_BLOCKED = "yappay.card.blocked";
    public static final String BLOCK_CARD_NOT = "yappay.not.block.card";
    public static final String ENTITY_ID_INVALID = "yappay.entity.id.invalid";
    public static final String KIT_NOT_ASSIGNED = "yappay.kit.not.assigned";
    public static final String NOT_ENOUGH_BALANCE = "yappay.not.enough.balance";
    public static final String MISSING_HEADER = "yappay.security.exception";
    public static final String MERCHANT_EXISTS = "yappay.merchant.exits";
    public static final String CUSTOMER_EXISTS = "yappay.customer.exits";
    public static final String BUSINESS_EXISTS = "yappay.business.exits";
    public static final String KIT_ASSIGNED = "yappay.kit.assigned";
    public static final String INVALID_PROFILE_ID = "yappay.invalid.profile.id";
    public static final String INVALID_ENTITY_TYPE = "yappay.invalid.entity.type";
    public static final String BUSINESS_ID_EXISTS = "yappay.bus.id.exists";
    public static final String BUSINESS_ID_NULL = "yappay.business.id.null";
    public static final String ENTITY_DECRYPTION_FAILED = "yappay.entity.decrypt.fail";
    public static final String BODY_DECRYPTION_FAILED = "yappay.body.decrypt.fail";
    public static final String TOKEN_VERIFICATION_FAILED="yappay.token.verification.failed";
    public static final String RESPONSE_ENCRYPTION_FAILED="yappay.response.encryption.failed";
    public static final String FLOW_NOT_FOUND="yappay.flow.not.found";
    
    public static final String UNSUPPORTED_FILE_FORMAT = "unsupported.file.format";
    public static final String UPLOAD_PATH_EXCEPTION = "upload.path.error";
    public static final String INVALID_DATE = "invalid.date";
    public static final String ENTITY_IMPORT_EXCEPTION = "entity.import.exception";
}
