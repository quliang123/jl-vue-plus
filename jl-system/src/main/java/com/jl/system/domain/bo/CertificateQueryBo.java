package com.jl.system.domain.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 证书管理表
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */


@Data
@ApiModel("证书管理分页查询对象")
public class CertificateQueryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 证书id */
    @ApiModelProperty("证书id")
    @TableId(value = "certificate_id", type = IdType.AUTO)
    private Long certificateId;

    /** 证书编号 */
    @ApiModelProperty("证书编号")
    private String certificateNumber;

    /** 微信用户id */
    @ApiModelProperty("微信用户id")
    private Integer userId;

    /** 基金会id */
    @ApiModelProperty("基金会id")
    private Integer foundationId;

    /** 募捐记录id */
    @ApiModelProperty("募捐记录id")
    private Integer arId;

    /** 校友id  */
    @ApiModelProperty("校友id")
    private Integer alumnaId;

    /** 查询参数 */
    @ApiModelProperty("查询参数")
    Map<String, Object> params = new HashMap<>();

}
