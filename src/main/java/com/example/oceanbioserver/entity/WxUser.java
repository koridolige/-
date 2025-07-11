package com.example.oceanbioserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName wx_user
 */
@TableName(value ="wx_user")
@Data
public class WxUser {
    private Integer userId;

    private String username;

    private String password;

    private String userImg;

    private String flag;
}