package com.wei.wechat.core.bean;

/**
 * 接口访问凭证.
 * @author sophia
 *
 */
public class Token {

	private String accessToken;
	
	//凭证有效时间
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
