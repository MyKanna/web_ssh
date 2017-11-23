package ch.shop.user.utils;

import java.util.Map;

import javax.mail.internet.MimeMultipart;

public class Email {
	private String sender;
	private Map<String, String> addressee;
	private String title;
	private MimeMultipart mimeMultipart;

	public Email(String sender, Map<String, String> addressee, String title, MimeMultipart mimeMultipart) {
		this.sender = sender;
		this.addressee = addressee;
		this.title = title;
		this.mimeMultipart = mimeMultipart;
	}

	public Email() {
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Map<String, String> getAddressee() {
		return this.addressee;
	}

	public void setAddressee(Map<String, String> addressee) {
		this.addressee = addressee;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MimeMultipart getMimeMultipart() {
		return this.mimeMultipart;
	}

	public void setMimeMultipart(MimeMultipart mimeMultipart) {
		this.mimeMultipart = mimeMultipart;
	}

	public String toString() {
		return "com.wuyi.javamail.Email{sender='" + this.sender + '\'' + ", addressee=" + this.addressee + ", title='"
				+ this.title + '\'' + ", mimeMultipart=" + this.mimeMultipart + '}';
	}
}