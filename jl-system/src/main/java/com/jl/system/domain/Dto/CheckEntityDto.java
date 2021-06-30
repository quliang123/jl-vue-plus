package com.jl.system.domain.Dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel("参数校验对象")
public class CheckEntityDto implements Serializable {


    /**
     * 校验状态
     */
    private String status;

    /**
     * 校验结果
     */
    private String msg;
}
