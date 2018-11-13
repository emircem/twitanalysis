package mrcm.twitanalysis.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="twit")
public class Twit {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String link;
	
	private String text;
	
	private int analysis;
	
	@ManyToOne
	@JoinColumn(name="id_apiuser")
	private ApiUser apiUser;

	
	public Twit() {}
	
	public Twit(long id, String link, String text, int analysis, ApiUser apiUser) {
		super();
		this.id = id;
		this.link = link;
		this.text = text;
		this.analysis = analysis;
		this.apiUser = apiUser;
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

	public ApiUser getApiUser() {
		return apiUser;
	}

	public void setApiUser(ApiUser apiUser) {
		this.apiUser = apiUser;
	}
	
}
