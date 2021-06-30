package com.jl.common.core.domain.model;

import lombok.Data;

/**
 * @createdTime 2021-06-09
 */
@Data
public class WxLoginForm {
    //@NotBlank(message = "code必传")
    private String code;

    private String appId;

    private String sessionKey;

    private String signature;

    private String rawData;

    private String encryptedData;

    private String iv;





}
