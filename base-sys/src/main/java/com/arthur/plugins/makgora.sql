SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS t_crm_user;
DROP TABLE IF EXISTS t_sys_constant;
DROP TABLE IF EXISTS t_sys_enum;
DROP TABLE IF EXISTS t_sys_enum_value;
DROP TABLE IF EXISTS t_sys_log;
DROP TABLE IF EXISTS t_sys_login_log;
DROP TABLE IF EXISTS t_sys_login_user;
DROP TABLE IF EXISTS t_sys_menu;
DROP TABLE IF EXISTS t_sys_power;
DROP TABLE IF EXISTS t_sys_power_resource;
DROP TABLE IF EXISTS t_sys_resource;
DROP TABLE IF EXISTS t_sys_role;
DROP TABLE IF EXISTS t_sys_role_power;
DROP TABLE IF EXISTS t_sys_user_role;




/* Create Tables */

-- persistent_logins
CREATE TABLE persistent_logins
(
	username varchar(64) NOT NULL COMMENT 'username',
	series varchar(64) NOT NULL COMMENT 'series',
	token varchar(64) NOT NULL COMMENT 'token',
	last_used timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT 'last_used',
	PRIMARY KEY (series)
) COMMENT = 'persistent_logins' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_crm_user
CREATE TABLE t_crm_user
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 支付密码 : 支付密码
	PAYMENT_PASSWORD varchar(100) COMMENT '支付密码 : 支付密码',
	-- 状态,1有效，0无效 : 状态,1有效，0无效
	STATUS varchar(50) DEFAULT '0' COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
	-- 其他状态 : 其他状态
	STATE varchar(50) COMMENT '其他状态 : 其他状态',
	-- 真实姓名（用户名称/企业名称） : 真实姓名（用户名称/企业名称）
	NAME varchar(200) COMMENT '真实姓名（用户名称/企业名称） : 真实姓名（用户名称/企业名称）',
	-- 详细地址 : 详细地址
	ADDRESS varchar(400) COMMENT '详细地址 : 详细地址',
	-- 联系电话 : 联系电话
	TELEPHONE varchar(100) COMMENT '联系电话 : 联系电话',
	-- 证件类型(个人用户：身份证；企业用户：统一信用代码) : 证件类型(个人用户：身份证；企业用户：统一信用代码)
	CARD_TYPE varchar(50) COMMENT '证件类型(个人用户：身份证；企业用户：统一信用代码) : 证件类型(个人用户：身份证；企业用户：统一信用代码)',
	-- 证件号 : 证件号
	CARD_NUMBER varchar(50) COMMENT '证件号 : 证件号',
	-- 身份类型(0个人/1企业/) : 身份类型(0个人/1企业/)
	TYPE varchar(50) DEFAULT '0' COMMENT '身份类型(0个人/1企业/) : 身份类型(0个人/1企业/)',
	-- 邮箱 : 邮箱
	EMAIL varchar(100) COMMENT '邮箱 : 邮箱',
	-- 介绍人id : 介绍人id
	USER_ID bigint COMMENT '介绍人id : 介绍人id',
	-- 银行 : 银行
	BANK varchar(200) COMMENT '银行 : 银行',
	-- 开户账号姓名 : 开户账号姓名
	ACCOUNT_NAME varchar(200) COMMENT '开户账号姓名 : 开户账号姓名',
	-- 默认银行账号 : 默认银行账号
	ACCOUNT varchar(200) COMMENT '默认银行账号 : 默认银行账号',
	-- 积分 : 积分 需要加密
	SCORE varchar(50) COMMENT '积分 : 积分 需要加密',
	-- 推荐码 : 推荐码
	RECOMMENDED_CODE varchar(200) COMMENT '推荐码 : 推荐码',
	-- 头像地址 : 头像地址
	URI varchar(200) COMMENT '头像地址 : 头像地址',
	-- 最近登录失败时间 : 最近登录失败时间
	FINAL_FAILURE_DATE datetime COMMENT '最近登录失败时间 : 最近登录失败时间',
	-- 锁定至时间 : 锁定至时间
	LOCK_TO_DATE datetime COMMENT '锁定至时间 : 锁定至时间',
	-- 用户登录ip : 用户登录ip
	IP varchar(255) COMMENT '用户登录ip : 用户登录ip',
	-- 用户登录失败次数 : 用户登录失败次数
	FAIL_TIMES int DEFAULT 0 COMMENT '用户登录失败次数 : 用户登录失败次数',
	-- 会员卡编号 : 会员卡编号
	MEMBER_CODE varchar(50) COMMENT '会员卡编号 : 会员卡编号',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_crm_user' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_constant
CREATE TABLE t_sys_constant
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 常量名称 : 常量名称
	NAME varchar(200) COMMENT '常量名称 : 常量名称',
	-- 常量代码 : 常量代码
	CODE varchar(200) COMMENT '常量代码 : 常量代码',
	-- 常量值 : 常量值
	VALUE varchar(200) COMMENT '常量值 : 常量值',
	-- 状态 : 状态
	STATUS varchar(50) COMMENT '状态 : 状态',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_constant' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_enum
CREATE TABLE t_sys_enum
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 枚举代码 : 枚举代码
	CODE varchar(200) COMMENT '枚举代码 : 枚举代码',
	-- 枚举名称 : 枚举名称
	NAME varchar(200) COMMENT '枚举名称 : 枚举名称',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 状态 : 状态
	STATUS varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_enum' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_enum_value
CREATE TABLE t_sys_enum_value
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 枚举id : 枚举id
	ENUM_ID bigint COMMENT '枚举id : 枚举id',
	-- 枚举值编码 : 枚举值编码
	CODE varchar(50) COMMENT '枚举值编码 : 枚举值编码',
	-- 枚举值名称 : 枚举值名称
	NAME varchar(200) COMMENT '枚举值名称 : 枚举值名称',
	-- 状态 : 状态
	STATUS varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 顺序 : 顺序
	SEQUENCE int COMMENT '顺序 : 顺序',
	-- 父id : 父id
	PID bigint COMMENT '父id : 父id',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_enum_value' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_log
CREATE TABLE t_sys_log
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- user_id : user_id
	USER_ID bigint COMMENT 'user_id : user_id',
	-- 操作类型 : 操作类型
	TYPE varchar(50) COMMENT '操作类型 : 操作类型',
	-- 操作内容 : 操作内容
	CONTENT text COMMENT '操作内容 : 操作内容',
	-- 操作地址 : 操作地址
	URI text COMMENT '操作地址 : 操作地址',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建日期 : 创建日期
	CREATED_DATE datetime COMMENT '创建日期 : 创建日期',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_log' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_login_log
CREATE TABLE t_sys_login_log
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- user_id : user_id
	USER_ID bigint COMMENT 'user_id : user_id',
	-- user_ip : user_ip
	USER_IP bigint COMMENT 'user_ip : user_ip',
	-- 登录时间 : 登录时间
	LOGIN_ON_DATE datetime COMMENT '登录时间 : 登录时间',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改日期 : 修改日期
	MODIFIED_DATE datetime COMMENT '修改日期 : 修改日期',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_login_log' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_login_user
CREATE TABLE t_sys_login_user
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 登录名 : 登录名
	LOGIN_NAME varchar(100) COMMENT '登录名 : 登录名',
	-- 密码 : 密码
	PASSWORD varchar(100) COMMENT '密码 : 密码',
	-- user_id : user_id
	USER_ID bigint COMMENT 'user_id : user_id',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) COMMENT '删除状态 : 删除状态',
	-- 状态,1有效，0无效 : 状态,1有效，0无效
	STATUS varchar(50) COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建日期 : 创建日期
	CREATED_DATE datetime COMMENT '创建日期 : 创建日期',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改日期 : 修改日期
	MODIFIED_DATE datetime COMMENT '修改日期 : 修改日期',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_login_user' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_menu
CREATE TABLE t_sys_menu
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 名称 : 名称
	NAME varchar(200) NOT NULL COMMENT '名称 : 名称',
	-- 描述 : 描述
	DEPICT varchar(200) COMMENT '描述 : 描述',
	-- 层次 : 层次
	LAYER int DEFAULT 1 NOT NULL COMMENT '层次 : 层次',
	-- 父菜单ID : 父菜单ID
	PID bigint COMMENT '父菜单ID : 父菜单ID',
	-- 资源id : 资源id
	RESOURCE_ID bigint COMMENT '资源id : 资源id',
	-- 排列次序 : 排列次序
	SEQUENCE int DEFAULT 0 NOT NULL COMMENT '排列次序 : 排列次序',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 菜单所属系统 : 菜单所属系统
	SYSTEM varchar(50) DEFAULT '0' NOT NULL COMMENT '菜单所属系统 : 菜单所属系统',
	-- 链接此菜单的参数 : 链接此菜单的参数
	PARAMETER varchar(50) COMMENT '链接此菜单的参数 : 链接此菜单的参数',
	-- 状态,1有效，0无效 : 状态,1有效，0无效
	STATUS varchar(50) COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_menu' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_power
CREATE TABLE t_sys_power
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 权限名称 : 权限名称
	NAME varchar(200) NOT NULL COMMENT '权限名称 : 权限名称',
	-- 描述 : 描述
	DEPICT varchar(200) COMMENT '描述 : 描述',
	-- 是否有效 : 是否有效
	STATUS varchar(50) DEFAULT '0' NOT NULL COMMENT '是否有效 : 是否有效',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 父id : 父id
	PID bigint COMMENT '父id : 父id',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_power' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_power_resource
CREATE TABLE t_sys_power_resource
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 资源id : 资源id
	RESOURCE_ID bigint NOT NULL COMMENT '资源id : 资源id',
	-- 权限表id : 权限表id
	POWER_ID bigint COMMENT '权限表id : 权限表id',
	-- 描述 : 描述
	DEPICT varchar(200) COMMENT '描述 : 描述',
	-- 是否有效 : 是否有效
	STATUS varchar(50) DEFAULT '0' NOT NULL COMMENT '是否有效 : 是否有效',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_power_resource' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_resource
CREATE TABLE t_sys_resource
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 资源名称 : 资源名称
	NAME varchar(200) COMMENT '资源名称 : 资源名称',
	-- 资源路径 : 资源路径
	URI varchar(400) COMMENT '资源路径 : 资源路径',
	-- 父资源id : 父资源id
	PID bigint COMMENT '父资源id : 父资源id',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 状态,1有效，0无效 : 状态,1有效，0无效
	STATUS varchar(50) COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_resource' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_role
CREATE TABLE t_sys_role
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 角色名 : 角色名
	NAME varchar(200) NOT NULL COMMENT '角色名 : 角色名',
	-- 角色代码 : 角色代码
	CODE varchar(50) COMMENT '角色代码 : 角色代码',
	-- 角色状态 : 角色状态
	STATUS varchar(50) DEFAULT '0' NOT NULL COMMENT '角色状态 : 角色状态',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 顺序号 : 顺序号
	SEQUENCE int COMMENT '顺序号 : 顺序号',
	-- 父id : 父id
	PID bigint COMMENT '父id : 父id',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID),
	UNIQUE (CODE)
) COMMENT = 't_sys_role' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_role_power
CREATE TABLE t_sys_role_power
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 权限表id : 权限表id
	POWER_ID bigint COMMENT '权限表id : 权限表id',
	-- 角色id : 角色id
	ROLE_ID bigint COMMENT '角色id : 角色id',
	-- 状态 : 状态
	STATUS varchar(50) DEFAULT '0' NOT NULL COMMENT '状态 : 状态',
	-- 备注 : 备注
	REMARK varchar(200) COMMENT '备注 : 备注',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_role_power' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- t_sys_user_role
CREATE TABLE t_sys_user_role
(
	-- id : id
	ID bigint NOT NULL COMMENT 'id : id',
	-- 用户表id : 用户表id
	USER_ID bigint COMMENT '用户表id : 用户表id',
	-- 角色id : 角色id
	ROLE_ID bigint COMMENT '角色id : 角色id',
	-- 角色持有类型 : 角色持有类型
	RELATE_TYPE varchar(50) DEFAULT '1' COMMENT '角色持有类型 : 角色持有类型',
	-- 角色有效时间起点 : 角色有效时间起点
	START_DATE datetime COMMENT '角色有效时间起点 : 角色有效时间起点',
	-- 角色有效时间终点 : 角色有效时间终点
	END_DATE datetime COMMENT '角色有效时间终点 : 角色有效时间终点',
	-- 状态 : 状态
	STATUS varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
	-- 删除状态 : 删除状态
	IS_DELETED varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
	-- 创建人 : 创建人
	CREATED_BY bigint COMMENT '创建人 : 创建人',
	-- 创建时间 : 创建时间
	CREATED_DATE datetime COMMENT '创建时间 : 创建时间',
	-- 修改人 : 修改人
	MODIFIED_BY bigint COMMENT '修改人 : 修改人',
	-- 修改时间 : 修改时间
	MODIFIED_DATE datetime COMMENT '修改时间 : 修改时间',
	PRIMARY KEY (ID)
) COMMENT = 't_sys_user_role' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



