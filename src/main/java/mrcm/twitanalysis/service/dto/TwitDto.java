package mrcm.twitanalysis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TwitDto {
	
	private long id;
	
	private String link;
	
	private String text;
	
	private int analysis;
	
	@JsonIgnore
	private ApiUserDto apiUser;

	
	public TwitDto() {}
	
	public TwitDto(long id, String link, String text, int analysis) {
		super();
		this.id = id;
		this.link = link;
		this.text = text;
		this.analysis = analysis;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getAnalysis() {
		return analysis;
	}

	public void setAnalysis(int analysis) {
		this.analysis = analysis;
	}

	public ApiUserDto getApiUser() {
		return apiUser;
	}

	public void setApiUser(ApiUserDto apiUser) {
		this.apiUser = apiUser;
	}
	
}

