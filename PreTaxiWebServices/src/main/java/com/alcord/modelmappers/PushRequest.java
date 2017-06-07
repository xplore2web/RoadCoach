package com.alcord.modelmappers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushRequest {
	
	@JsonProperty("default")
	private String def;
	@JsonProperty("GCM")
	private GCM gcm;
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public GCM getGcm() {
		return gcm;
	}
	public void setGcm(GCM gcm) {
		this.gcm = gcm;
	}
}