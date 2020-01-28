package com.app.entity;

import java.io.Serializable;

public class SealServiceOrderEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//报单类型
	private String typeId;
	
	//报单时间
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
