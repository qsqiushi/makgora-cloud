/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : makgora

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 08/17/2018 11:08:27 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL COMMENT 'username',
  `series` varchar(64) NOT NULL COMMENT 'series',
  `token` varchar(64) NOT NULL COMMENT 'token',
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last_used',
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='persistent_logins';

-- ----------------------------
--  Table structure for `t_crm_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_crm_user`;
CREATE TABLE `t_crm_user` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `PAYMENT_PASSWORD` varchar(100) DEFAULT NULL COMMENT '支付密码 : 支付密码',
  `STATUS` varchar(50) DEFAULT '0' COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
  `STATE` varchar(50) DEFAULT NULL COMMENT '其他状态 : 其他状态',
  `NAME` varchar(200) DEFAULT NULL COMMENT '真实姓名（用户名称/企业名称） : 真实姓名（用户名称/企业名称）',
  `ADDRESS` varchar(400) DEFAULT NULL COMMENT '详细地址 : 详细地址',
  `TELEPHONE` varchar(100) DEFAULT NULL COMMENT '联系电话 : 联系电话',
  `CARD_TYPE` varchar(50) DEFAULT NULL COMMENT '证件类型(个人用户：身份证；企业用户：统一信用代码) : 证件类型(个人用户：身份证；企业用户：统一信用代码)',
  `CARD_NUMBER` varchar(50) DEFAULT NULL COMMENT '证件号 : 证件号',
  `TYPE` varchar(50) DEFAULT '0' COMMENT '身份类型(0个人/1企业/) : 身份类型(0个人/1企业/)',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱 : 邮箱',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '介绍人id : 介绍人id',
  `BANK` varchar(200) DEFAULT NULL COMMENT '银行 : 银行',
  `ACCOUNT_NAME` varchar(200) DEFAULT NULL COMMENT '开户账号姓名 : 开户账号姓名',
  `ACCOUNT` varchar(200) DEFAULT NULL COMMENT '默认银行账号 : 默认银行账号',
  `SCORE` varchar(50) DEFAULT NULL COMMENT '积分 : 积分 需要加密',
  `RECOMMENDED_CODE` varchar(200) DEFAULT NULL COMMENT '推荐码 : 推荐码',
  `URI` varchar(200) DEFAULT NULL COMMENT '头像地址 : 头像地址',
  `FINAL_FAILURE_DATE` datetime DEFAULT NULL COMMENT '最近登录失败时间 : 最近登录失败时间',
  `LOCK_TO_DATE` datetime DEFAULT NULL COMMENT '锁定至时间 : 锁定至时间',
  `IP` varchar(255) DEFAULT NULL COMMENT '用户登录ip : 用户登录ip',
  `FAIL_TIMES` int(11) DEFAULT '0' COMMENT '用户登录失败次数 : 用户登录失败次数',
  `MEMBER_CODE` varchar(50) DEFAULT NULL COMMENT '会员卡编号 : 会员卡编号',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_crm_user';

-- ----------------------------
--  Table structure for `t_sys_constant`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_constant`;
CREATE TABLE `t_sys_constant` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `NAME` varchar(200) DEFAULT NULL COMMENT '常量名称 : 常量名称',
  `CODE` varchar(200) DEFAULT NULL COMMENT '常量代码 : 常量代码',
  `VALUE` varchar(200) DEFAULT NULL COMMENT '常量值 : 常量值',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态 : 状态',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_constant';

-- ----------------------------
--  Table structure for `t_sys_enum`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_enum`;
CREATE TABLE `t_sys_enum` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `CODE` varchar(200) DEFAULT NULL COMMENT '枚举代码 : 枚举代码',
  `NAME` varchar(200) DEFAULT NULL COMMENT '枚举名称 : 枚举名称',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `STATUS` varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_enum';

-- ----------------------------
--  Table structure for `t_sys_enum_value`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_enum_value`;
CREATE TABLE `t_sys_enum_value` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `ENUM_ID` bigint(20) DEFAULT NULL COMMENT '枚举id : 枚举id',
  `CODE` varchar(50) DEFAULT NULL COMMENT '枚举值编码 : 枚举值编码',
  `NAME` varchar(200) DEFAULT NULL COMMENT '枚举值名称 : 枚举值名称',
  `STATUS` varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `SEQUENCE` int(11) DEFAULT NULL COMMENT '顺序 : 顺序',
  `PID` bigint(20) DEFAULT NULL COMMENT '父id : 父id',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_enum_value';

-- ----------------------------
--  Table structure for `t_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT 'user_id : user_id',
  `TYPE` varchar(50) DEFAULT NULL COMMENT '操作类型 : 操作类型',
  `CONTENT` text COMMENT '操作内容 : 操作内容',
  `URI` text COMMENT '操作地址 : 操作地址',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期 : 创建日期',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_log';

-- ----------------------------
--  Table structure for `t_sys_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login_log`;
CREATE TABLE `t_sys_login_log` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT 'user_id : user_id',
  `USER_IP` bigint(20) DEFAULT NULL COMMENT 'user_ip : user_ip',
  `LOGIN_ON_DATE` datetime DEFAULT NULL COMMENT '登录时间 : 登录时间',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改日期 : 修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_login_log';

-- ----------------------------
--  Table structure for `t_sys_login_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login_user`;
CREATE TABLE `t_sys_login_user` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `LOGIN_NAME` varchar(100) DEFAULT NULL COMMENT '登录名 : 登录名',
  `PASSWORD` varchar(100) DEFAULT NULL COMMENT '密码 : 密码',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT 'user_id : user_id',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `IS_DELETED` varchar(50) DEFAULT NULL COMMENT '删除状态 : 删除状态',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建日期 : 创建日期',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改日期 : 修改日期',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_login_user';

-- ----------------------------
--  Table structure for `t_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `NAME` varchar(200) NOT NULL COMMENT '名称 : 名称',
  `DEPICT` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `LAYER` int(11) NOT NULL DEFAULT '1' COMMENT '层次 : 层次',
  `PID` bigint(20) DEFAULT NULL COMMENT '父菜单ID : 父菜单ID',
  `RESOURCE_ID` bigint(20) DEFAULT NULL COMMENT '资源id : 资源id',
  `SEQUENCE` int(11) NOT NULL DEFAULT '0' COMMENT '排列次序 : 排列次序',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `SYSTEM` varchar(50) NOT NULL DEFAULT '0' COMMENT '菜单所属系统 : 菜单所属系统',
  `PARAMETER` varchar(50) DEFAULT NULL COMMENT '链接此菜单的参数 : 链接此菜单的参数',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_menu';

-- ----------------------------
--  Table structure for `t_sys_power`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_power`;
CREATE TABLE `t_sys_power` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `NAME` varchar(200) NOT NULL COMMENT '权限名称 : 权限名称',
  `DEPICT` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `STATUS` varchar(50) NOT NULL DEFAULT '0' COMMENT '是否有效 : 是否有效',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `PID` bigint(20) DEFAULT NULL COMMENT '父id : 父id',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_power';

-- ----------------------------
--  Table structure for `t_sys_power_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_power_resource`;
CREATE TABLE `t_sys_power_resource` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `RESOURCE_ID` bigint(20) NOT NULL COMMENT '资源id : 资源id',
  `POWER_ID` bigint(20) DEFAULT NULL COMMENT '权限表id : 权限表id',
  `DEPICT` varchar(200) DEFAULT NULL COMMENT '描述 : 描述',
  `STATUS` varchar(50) NOT NULL DEFAULT '0' COMMENT '是否有效 : 是否有效',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_power_resource';

-- ----------------------------
--  Table structure for `t_sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `NAME` varchar(200) DEFAULT NULL COMMENT '资源名称 : 资源名称',
  `URI` varchar(400) DEFAULT NULL COMMENT '资源路径 : 资源路径',
  `PID` bigint(20) DEFAULT NULL COMMENT '父资源id : 父资源id',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态,1有效，0无效 : 状态,1有效，0无效',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_resource';

-- ----------------------------
--  Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `NAME` varchar(200) NOT NULL COMMENT '角色名 : 角色名',
  `CODE` varchar(50) DEFAULT NULL COMMENT '角色代码 : 角色代码',
  `STATUS` varchar(50) NOT NULL DEFAULT '0' COMMENT '角色状态 : 角色状态',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `SEQUENCE` int(11) DEFAULT NULL COMMENT '顺序号 : 顺序号',
  `PID` bigint(20) DEFAULT NULL COMMENT '父id : 父id',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_role';

-- ----------------------------
--  Table structure for `t_sys_role_power`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_power`;
CREATE TABLE `t_sys_role_power` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `POWER_ID` bigint(20) DEFAULT NULL COMMENT '权限表id : 权限表id',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色id : 角色id',
  `STATUS` varchar(50) NOT NULL DEFAULT '0' COMMENT '状态 : 状态',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注 : 备注',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_role_power';

-- ----------------------------
--  Table structure for `t_sys_tickets64`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_tickets64`;
CREATE TABLE `t_sys_tickets64` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `stub` char(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stub` (`stub`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_sys_tickets64`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_tickets64` VALUES ('7', 'a');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_tickets64_even`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_tickets64_even`;
CREATE TABLE `t_sys_tickets64_even` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `stub` char(1) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stub` (`stub`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_sys_tickets64_even`
-- ----------------------------
BEGIN;
INSERT INTO `t_sys_tickets64_even` VALUES ('10', 'a');
COMMIT;

-- ----------------------------
--  Table structure for `t_sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `ID` bigint(20) NOT NULL COMMENT 'id : id',
  `USER_ID` bigint(20) DEFAULT NULL COMMENT '用户表id : 用户表id',
  `ROLE_ID` bigint(20) DEFAULT NULL COMMENT '角色id : 角色id',
  `RELATE_TYPE` varchar(50) DEFAULT '1' COMMENT '角色持有类型 : 角色持有类型',
  `START_DATE` datetime DEFAULT NULL COMMENT '角色有效时间起点 : 角色有效时间起点',
  `END_DATE` datetime DEFAULT NULL COMMENT '角色有效时间终点 : 角色有效时间终点',
  `STATUS` varchar(50) DEFAULT '0' COMMENT '状态 : 状态',
  `IS_DELETED` varchar(50) DEFAULT '0' COMMENT '删除状态 : 删除状态',
  `CREATED_BY` bigint(20) DEFAULT NULL COMMENT '创建人 : 创建人',
  `CREATED_DATE` datetime DEFAULT NULL COMMENT '创建时间 : 创建时间',
  `MODIFIED_BY` bigint(20) DEFAULT NULL COMMENT '修改人 : 修改人',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '修改时间 : 修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='t_sys_user_role';

-- ----------------------------
--  Procedure structure for `import_into_cms_article`
-- ----------------------------
DROP PROCEDURE IF EXISTS `import_into_cms_article`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `import_into_cms_article`()
BEGIN
  DECLARE old_Id int(11);
  DECLARE old_title char(50);
  DECLARE old_content text;
  DECLARE old_Writer_id int(11);
  DECLARE old_status int(5);
  DECLARE old_Verifier_id int(11);
  DECLARE old_modifier_id int(11);
  DECLARE old_Createtime date;
  DECLARE old_Edittime date;
  DECLARE old_View_num int(11);
  DECLARE old_Comment_num int(11);
  DECLARE old_stick int(2);
  DECLARE old_Category_ID int(11);
  DECLARE old_keywords char(50);
  DECLARE old_back varchar(255);
  DECLARE done int DEFAULT FALSE;
  DECLARE cur CURSOR FOR
    SELECT
      `Id`,
      `title`,
      `content`,
      `Writer_id`,
      `status`,
      `Verifier_id`,
      `modifier_id`,
      `Createtime`,
      `Edittime`,
      `View_num`,
      `Comment_num`,
      `stick`,
      `Category_ID`,
      `keywords`,
      `back`
    FROM
      `article`;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET done = TRUE;
  DELETE FROM `cms_article`;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO old_Id, old_title, old_content, old_Writer_id, old_status, old_Verifier_id, old_modifier_id, old_Createtime, old_Edittime, old_View_num, old_Comment_num, old_stick, old_Category_ID, old_keywords, old_back;
    IF `done` THEN
      LEAVE read_loop;
    END IF;
    INSERT
      INTO `cms_article`
      (`ID`, `TITLE`, `CONTENT`, `STATUS`, `VIEW_NUM`, `STICK`, `CATEGORY_ID`, `KEYWORDS`, `IS_DELETED`, `REMARK`, `VERIFIED_BY`, `CREATED_BY`, `MODIFIED_BY`, `CREATED_DATE`, `MODIFIED_DATE`)
    VALUE
      (`old_Id`, `old_title`, `old_content`, `old_status`, `old_View_num`, `old_stick`, `old_Category_ID`, `old_keywords`, '0', `old_back`, `old_Verifier_id`, `old_Writer_id`, `old_modifier_id`, `old_Createtime`, `old_Edittime`);
  END LOOP;
  CLOSE cur;
END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `import_into_cms_article_category`
-- ----------------------------
DROP PROCEDURE IF EXISTS `import_into_cms_article_category`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `import_into_cms_article_category`()
BEGIN
  DECLARE old_Id int(11);
  DECLARE old_name varchar(20);
  DECLARE old_Descr varchar(50);
  DECLARE old_Parent_id int(11);
  DECLARE old_priority int(5);
  DECLARE old_createtime date;
  DECLARE old_status int(3);
  DECLARE old_modifytime date;
  DECLARE done int DEFAULT FALSE;
  DECLARE cur CURSOR FOR
    SELECT
      `id`,
      `name`,
      `descr`,
      `parent_id`,
      `priority`,
      `createtime`,
      `status`,
      `modifytime`
    FROM
      `articlecategory`;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET done = TRUE;
  DELETE FROM `cms_article_category`;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO old_Id, old_name, old_Descr, old_Parent_id, old_priority, old_createtime, old_status, old_modifytime;
    IF `done` THEN
      LEAVE read_loop;
    END IF;
    INSERT
      INTO `cms_article_category`
      (`id`, `name`, `descr`, `pid`, `is_deleted`, `created_date`, `modified_date`)
    VALUE
      (`old_Id`, `old_name`, `old_Descr`, `old_Parent_id`, `old_status`, `old_createtime`, `old_modifytime`);
  END LOOP;
  CLOSE cur;
  UPDATE `cms_article_category` SET `pid` = 0 WHERE `pid` IS NULL;
END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `import_into_crm_delivery_address`
-- ----------------------------
DROP PROCEDURE IF EXISTS `import_into_crm_delivery_address`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `import_into_crm_delivery_address`()
BEGIN
  DECLARE old_Id int(11);
  DECLARE old_address varchar(255);
  DECLARE old_telephone char(20);
  DECLARE old_contacts varchar(50);
  DECLARE old_user_id int(11);
  DECLARE old_zipcode char(6);
  DECLARE old_is_default int(11);
  DECLARE old_shengshi varchar(200);
  DECLARE province varchar(20);
  DECLARE city varchar(20);
  DECLARE district varchar(20);
  DECLARE done int DEFAULT FALSE;
  DECLARE cur CURSOR FOR
    SELECT
      `Id`,
      `address`,
      `telephone`,
      `contacts`,
      `user_id`,
      `zipcode`,
      `is_default`,
      `shengshi`
    FROM
      `delivery_address`;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET done = TRUE;
  DELETE FROM `crm_delivery_address`;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO old_Id, old_address, old_telephone, old_contacts, old_user_id, old_zipcode, old_is_default, old_shengshi;
    IF `done` THEN
      LEAVE read_loop;
    END IF;
    SET province = split(`old_shengshi`, ' ', 0);
    SET city = split(`old_shengshi`, ' ', 1);
    SET district = split(`old_shengshi`, ' ', 2);
    INSERT
      INTO `crm_delivery_address`
      (`ID`, `USER_ID`, `ADDRESS`, `TELEPHONE`, `CONTACTS`, `ZIPCODE`, `IS_DEFAULT`, `PROVINCE`, `CITY`, `DISTRICT`, `IS_DELETED`, `REMARK`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`)
    VALUE
      (`old_Id`, `old_user_id`, `old_address`, `old_telephone`, `old_contacts`, `old_zipcode`, `old_is_default`, `province`, `city`, `district`, '0', NULL, `old_user_id`, NULL, `old_user_id`, NULL);
  END LOOP;
  CLOSE cur;
END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `import_into_dms_img`
-- ----------------------------
DROP PROCEDURE IF EXISTS `import_into_dms_img`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `import_into_dms_img`()
BEGIN
  DECLARE old_Id int(11);
  DECLARE old_img_url varchar(255);
  DECLARE old_img_type char(32);
  DECLARE old_img_otherId int(11);
  DECLARE old_img_createTime char(24);
  DECLARE done int DEFAULT FALSE;
  DECLARE cur CURSOR FOR
    SELECT
      `Id`,
      `img_url`,
      `img_type`,
      `img_otherId`,
      `img_createTime`
    FROM
      `img`;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET done = TRUE;
  DELETE FROM `dms_img`;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO old_Id, old_img_url, old_img_type, old_img_otherId, old_img_createTime;
    IF `done` THEN
      LEAVE read_loop;
    END IF;
    INSERT
      INTO `dms_img`
      (`Id`, `NAME`, `URI`, `LINKS`, `TYPE`, `OTHER_ID`, `REMARK`, `IS_DELETED`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`)
    VALUE
      (`old_Id`, NULL, `old_img_url`, NULL, `old_img_type`, `old_img_otherId`, NULL, '0', NULL, `old_img_createTime`, NULL, NULL);
  END LOOP;
  CLOSE cur;
END
 ;;
delimiter ;

-- ----------------------------
--  Procedure structure for `import_into_sys_login_log`
-- ----------------------------
DROP PROCEDURE IF EXISTS `import_into_sys_login_log`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `import_into_sys_login_log`()
BEGIN
  DECLARE old_ID int(11);
  DECLARE old_USER_ID int(11);
  DECLARE old_USER_IP varchar(255);
  DECLARE old_LOGIN_ON_DATE datetime;
  DECLARE old_LOGIN_OFF_TIME datetime;
  DECLARE done int DEFAULT FALSE;
  DECLARE cur CURSOR FOR
    SELECT
      `ID`,
      `USER_ID`,
      `USER_IP`,
      `LOGIN_ON_DATE`,
      `LOGIN_OFF_TIME`
    FROM
      `t_sys_login_log`;
  DECLARE CONTINUE HANDLER FOR NOT FOUND
    SET done = TRUE;
  DELETE FROM `sys_login_log`;
  OPEN cur;
  read_loop: LOOP
    FETCH cur INTO old_ID, old_USER_ID, old_USER_IP, old_LOGIN_ON_DATE, old_LOGIN_OFF_TIME;
    IF `done` THEN
      LEAVE read_loop;
    END IF;
    INSERT
      INTO `sys_login_log`
      (`ID`, `USER_ID`, `USER_IP`, `LOGIN_ON_DATE`, `IS_DELETED`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`)
    VALUE
      (`old_Id`, `old_USER_ID`, `old_USER_IP`, `old_LOGIN_ON_DATE`, `0`, NULL, `old_LOGIN_ON_DATE`, NULL, `old_LOGIN_ON_DATE`);
  END LOOP;
  CLOSE cur;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getEvenPrimaryKey`
-- ----------------------------
DROP FUNCTION IF EXISTS `getEvenPrimaryKey`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getEvenPrimaryKey`() RETURNS bigint(20)
BEGIN

DECLARE sTemp BIGINT;


SET auto_increment_increment = 2;
SET auto_increment_offset = 2;
REPLACE INTO t_sys_tickets64_even (stub)
VALUES  ('a');

SELECT
	LAST_INSERT_ID() INTO sTemp;

RETURN sTemp;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getLowerList`
-- ----------------------------
DROP FUNCTION IF EXISTS `getLowerList`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getLowerList`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
   DECLARE sTemp VARCHAR(1000);
   DECLARE sTempChd VARCHAR(1000);
 
   SET sTemp = 0;
   SET sTempChd =cast(rootId as CHAR);
    WHILE sTempChd is not null DO
    SET sTemp = concat(sTemp,',',sTempChd);
     SELECT group_concat(id) INTO sTempChd FROM sys_user where IS_DELETED = '0' and  STATUS = '1' and Type = '1' and  FIND_IN_SET(ENTERPRISE_ID,sTempChd)>0;
   END WHILE;
   RETURN sTemp;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getPrimaryKey`
-- ----------------------------
DROP FUNCTION IF EXISTS `getPrimaryKey`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getPrimaryKey`() RETURNS bigint(20)
BEGIN

DECLARE sTemp BIGINT;


SET auto_increment_increment = 2;
SET auto_increment_offset = 1;

REPLACE INTO t_sys_tickets64 (stub)
VALUES  ('a');

SELECT
	LAST_INSERT_ID() INTO sTemp;

RETURN sTemp;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getRootId`
-- ----------------------------
DROP FUNCTION IF EXISTS `getRootId`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getRootId`(`childrenId` integer) RETURNS int(11)
BEGIN
	DECLARE rootId INTEGER(11);
  DECLARE parentId INTEGER(11);
SET rootId=0;
SET parentId=0;

   WHILE parentId is not null DO
		 SET rootId =childrenId;
		 SET parentId=null; 
     SELECT ENTERPRISE_ID INTO parentId FROM sys_user where  Type = '1' and  ID=childrenId;
     SET childrenId = parentId;
   END WHILE;

	RETURN rootId;


END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getUserIdById`
-- ----------------------------
DROP FUNCTION IF EXISTS `getUserIdById`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getUserIdById`(param_id int ) RETURNS int(11)
BEGIN
	DECLARE ret_id INTEGER(11);
	SELECT user_id INTO ret_id FROM sys_user WHERE id=param_id;
	RETURN ret_id;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `split`
-- ----------------------------
DROP FUNCTION IF EXISTS `split`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `split`(str varchar(2000), separatorStr varchar(2000), indexOrder int(11)) RETURNS varchar(2000) CHARSET utf8
BEGIN
  DECLARE tmpstr varchar(2000);
  DECLARE countIndex int(11) DEFAULT 0;
  DECLARE startPosition int(11) DEFAULT 1;
  DECLARE endPosition int(11);
  DECLARE result varchar(2000) DEFAULT '';
  DECLARE separatorLen int(11);
  SET tmpstr = `str`;
  SET separatorLen = CHAR_LENGTH(`separatorStr`);
  IF `indexOrder` > -1 THEN
    lp: LOOP
      SET endPosition = POSITION(`separatorStr` IN `tmpstr`);
      IF `endPosition` = 0 THEN
        LEAVE lp;
      END IF;
      IF `countIndex` = `indexOrder` THEN
        SET result = SUBSTRING(`tmpstr`,`startPosition`, (`endPosition` - 1));
        LEAVE lp;
      ELSE
        SET endPosition = `endPosition` + `separatorLen`;
        IF CHAR_LENGTH(`tmpstr`) < `endPosition` THEN
          LEAVE lp;
        END IF;
        SET tmpstr = SUBSTRING(`tmpstr`,`endPosition`);
        IF POSITION(`separatorStr` IN `tmpstr`) = 0 THEN
          SET result = `tmpstr`;
          LEAVE lp;
        END IF;
      END IF;
      SET countIndex = `countIndex` + 1;
    END LOOP;
  END IF;
  RETURN `result`;
END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
