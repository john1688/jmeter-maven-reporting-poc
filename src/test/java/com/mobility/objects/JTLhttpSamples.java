package com.mobility.objects;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "testResults")
public class JTLhttpSamples {
	
	
	@JacksonXmlElementWrapper(localName = "httpSample", useWrapping = false)
	private HttpSample[] httpSample;
	
	@JacksonXmlProperty(localName="version", isAttribute=true)
	private String version;
	
	@JacksonXmlProperty(localName="sample")
	private HttpSample[] sample;
	
    
    public JTLhttpSamples() {
    }
    
    public JTLhttpSamples(HttpSample[] httpSample) {
    	this.httpSample = httpSample;
    }
    
    public HttpSample[] getHttpSample() {
        return httpSample;
    }

    public void setHttpSample(HttpSample[] httpSample) {
    	
    	this.httpSample = httpSample;
    }
    
	
    public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public HttpSample[] getSample() {
		return sample;
	}


	public void setSample(HttpSample[] sample) {
		this.sample = sample;
	}


	@Override public String toString() {
        return "HttpSample{" + "httpSample=" + Arrays.toString(httpSample) + '}';
    }
}
