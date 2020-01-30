package com.app.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class SealServiceOrderEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//报单类型
	private String typeId;
	
	//报单时间
	private String declarationTime;

}
