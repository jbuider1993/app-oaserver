package com.app.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SealServiceOrderEntity {

	@ApiModelProperty(name = "typeId", value = "服务类型", dataType = "String", required = true)
	private String typeId;
	
	@ApiModelProperty(name = "declarationTime", value = "报单时间", dataType = "String", required = true)
	private String declarationTime;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getDeclarationTime() {
		return declarationTime;
	}

	public void setDeclarationTime(String declarationTime) {
		this.declarationTime = declarationTime;
	}
	
}
