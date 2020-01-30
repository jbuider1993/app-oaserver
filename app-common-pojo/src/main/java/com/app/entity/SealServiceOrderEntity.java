package com.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "售后订单")
public class SealServiceOrderEntity {
	
	@ApiModelProperty(name = "id", value = "工单id", dataType = "String", required = false)
	private String id;
	
	@ApiModelProperty(name = "typeId", value = "服务类型", dataType = "String", required = true)
	private String typeId;
	
	@ApiModelProperty(name = "declarationTime", value = "报单时间", dataType = "String", required = true)
	private String declarationTime;
	
	@ApiModelProperty(name = "customerId", value = "客户id", dataType = "String", required = false)
	private String customerId;
	
	@ApiModelProperty(name = "customerName", value = "客户名称", dataType = "String", required = true)
	private String customerName;
	
	@ApiModelProperty(name = "contacts", value = "联系人", dataType = "String", required = true)
	private String contacts;
	
	@ApiModelProperty(name = "phone", value = "联系电话", dataType = "String", required = true)
	private String phone;
	
	@ApiModelProperty(name = "email", value = "邮箱", dataType = "String", required = false)
	private String email;
	
	@ApiModelProperty(name = "qq", value = "QQ号", dataType = "String", required = false)
	private String qq;
	
	@ApiModelProperty(name = "provinceId", value = "省，不可为空", dataType = "String", required = true)
	private String provinceId;
	
	@ApiModelProperty(name = "cityId", value = "市，不可为空", dataType = "String", required = true)
	private String cityId;
	
	@ApiModelProperty(name = "areaId", value = "区县，可为空", dataType = "String", required = false)
	private String areaId;
	
	@ApiModelProperty(name = "townshipId", value = "乡镇，可为空", dataType = "String", required = false)
	private String townshipId;
	
	@ApiModelProperty(name = "addressDetailed", value = "详细地址", dataType = "String", required = true)
	private String addressDetailed;
	
	@ApiModelProperty(name = "productId", value = "产品id", dataType = "String", required = false)
	private String productId;
	
	@ApiModelProperty(name = "productName", value = "产品名称", dataType = "String", required = false)
	private String productName;
	
	@ApiModelProperty(name = "productNorms", value = "规格型号", dataType = "String", required = false)
	private String productNorms;
	
	@ApiModelProperty(name = "productSerialNum", value = "序列号", dataType = "String", required = false)
	private String productSerialNum;
	
	@ApiModelProperty(name = "productWarranty", value = "质保类型", dataType = "String", required = false)
	private String productWarranty;
	
	@ApiModelProperty(name = "urgencyId", value = "紧急程度", dataType = "String", required = true)
	private String urgencyId;
	
	@ApiModelProperty(name = "modeId", value = "处理方式", dataType = "String", required = true)
	private String modeId;
	
	@ApiModelProperty(name = "content", value = "服务内容", dataType = "String", required = true)
	private String content;
	
	@ApiModelProperty(name = "serviceUserId", value = "工单接收人", dataType = "String", required = false)
	private String serviceUserId;
	
	@ApiModelProperty(name = "cooperationUserId", value = "工单协助人", dataType = "String", required = false)
	private String cooperationUserId;
	
	@ApiModelProperty(name = "sheetPicture", value = "相关照片", dataType = "String", required = false)
	private String sheetPicture;
	
	@ApiModelProperty(name = "enclosureInfo", value = "附件", dataType = "String", required = false)
	private String enclosureInfo;
	
}
