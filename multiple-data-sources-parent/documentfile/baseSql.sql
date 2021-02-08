CREATE TABLE `base_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) unsigned NOT NULL COMMENT '供应商Id',
  `auth_name` varchar(30) NOT NULL DEFAULT '' COMMENT '乘客账户',
  `wechat_open_id` char(50) DEFAULT NULL COMMENT '微信OpenID',
  `mobile` char(50) DEFAULT '' COMMENT '用户手机号',
  `password` char(64) DEFAULT '' COMMENT '密码md5(md5(password)+salt)',
  `salt` varchar(50) DEFAULT '' COMMENT '密码盐值',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '用户昵称',
  `age` tinyint(2) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '性别 1男 2女',
  `create_ip` varchar(46) NOT NULL DEFAULT '' COMMENT '创建Ip地址',
  `avatar` varchar(2000) NOT NULL DEFAULT '' COMMENT '头像地址',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '账户状态 1 启用 0 禁用',
  `create_time` bigint(16) unsigned NOT NULL COMMENT '创建时间',
  `update_time` bigint(16) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间',
  `identify_id` varchar(20) DEFAULT NULL COMMENT '用户标识',
  `active_status` tinyint(4) DEFAULT NULL COMMENT '激活状态',
  `old_open_id` char(50) DEFAULT '' COMMENT '存用户的openId,用户注销也要保存',
  PRIMARY KEY (`id`),
  KEY `provider_id` (`provider_id`),
  KEY `mobile` (`mobile`),
  KEY `status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息';


CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'app id',
  `app_secret` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'app secret',
  `private_key` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '私钥(rsa非对称加密)',
  `public_key` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '公钥(rsa非对称加密)',
  `key_status` tinyint(2) DEFAULT '0' COMMENT '秘钥更新状态(rsa非对称加密 1 已刷新)',
  `order_channel_id` tinyint(4) unsigned NOT NULL COMMENT '订单渠道id',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='应用平台信息表';