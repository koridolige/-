package com.example.oceanbioserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName wx_goods
 */
@TableName(value ="wx_goods")
@Data
public class WxGoods {
    private Integer goodsId;

    private String goodsName;

    private BigDecimal price;

    private String category;

    private String brand;

    private String specifications;

    private String description;

    private String image;

    private Integer stock;

    private Integer sales;

    private Date createTime;

    private Date updateTime;
}