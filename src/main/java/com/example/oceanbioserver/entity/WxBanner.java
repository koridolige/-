package com.example.oceanbioserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName wx_banner
 */
@TableName(value ="wx_banner")
@Data
public class WxBanner {
    private Integer bannerId;

    private String imgurl;
}