package com.notes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notes")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nId;
	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name="uId")
	@JsonBackReference
	private User user;

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Note(int nId, String title, String content, User user) {
		super();
		this.nId = nId;
		this.title = title;
		this.content = content;
		this.user = user;
	}

	public int getnId() {
		return nId;
	}

	public void setnId(int nId) {
		this.nId = nId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Note [nId=" + nId + ", title=" + title + ", content=" + content + ", user=" + user.getuId() + "]";
	}
}
