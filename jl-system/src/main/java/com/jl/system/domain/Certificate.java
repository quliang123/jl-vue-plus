package com.jl.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 证书管理表
 * </p>
 *
 * @author quliang
 * @since 2021-06-10
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("Certificate")
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 证书id
     */
    @TableId(value = "certificate_id", type = IdType.AUTO)
    private Long certificateId;

    /**
     * 证书编号
     */
    private String certificateNumber;

    /**
     * 微信用户id
     */
    private Long userId;

    /**
     * 基金会id
     */
    private Long foundationId;

    /**
     * 募捐记录id
     */
    private Long arId;

    /** 校友id  */
    private Long alumnaId;


    private Activity activity;

    private ActivityRecord activityRecord;

    private Foundation foundation;

}
