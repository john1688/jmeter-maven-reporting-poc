package com.mobility.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpSample {
	
	@JacksonXmlProperty(localName = "lb", isAttribute = true)
    private String label;

    @JacksonXmlProperty(localName = "lt", isAttribute = true)
    private String latency;
    
    @JacksonXmlProperty(localName = "s", isAttribute = true)
    private String status;
    
    @JacksonXmlProperty(localName = "t", isAttribute = true)
    private String duration;
    
    @JacksonXmlProperty(localName = "rc", isAttribute = true)
    private String responseCode;
    
    @JacksonXmlProperty(localName = "rm", isAttribute = true)
    private String responseMsg;
    

    public HttpSample() {

    }
    
    public HttpSample(String label, String latency, String duration, String status, String responseCode, String responseMsg){
    	this.label = label;
    	this.latency=latency;
    	this.duration= duration;
    	this.status = status;
    	this.responseCode = responseCode;
    	this.responseMsg = responseMsg;
    }
    
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLatency() {
		return latency;
	}

	public void setLatency(String latency) {
		this.latency = latency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
    
    @Override public String toString() {
        return "HttpSample{" +"label='" + label + '\'' +
        		", latency='" + latency + '\'' +
                ", status='" + status + '\'' +
                ", duration='" + duration + '\'' +
                ", responseCode=" + responseCode +
                ", responseMsg='" + responseMsg + '\'' +
                '}';
    }
}
