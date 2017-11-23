package ch.shop.user.utils;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.mail.util.MailSSLSocketFactory;

public class JavaMailUtils {
	private static Document document;
	private String user;
	private String password;
	private Map<String, String> properties;
	public static final String TO = "TO";
	public static final String CC = "CC";
	public static final String BCC = "BCC";

	public JavaMailUtils(String name) {
		Element element = (Element) document.selectNodes("//named-config[@name=" + name + "]");
		Element userEle = element.element("user");
		Element passwordEle = element.element("password");
		List propertieEles = element.elements("properties");

		this.user = userEle.getText();
		this.password = passwordEle.getText();
		this.properties = new HashMap();

		for (int i = 0; i < propertieEles.size(); i++) {
			Element ele = (Element) propertieEles.get(i);
			Attribute key = ele.attribute("key");
			String value = key.getValue();
			String text = ele.getText();
			this.properties.put(value, text);
		}
	}

	public JavaMailUtils() {
		List list = document.selectNodes("//default-config");
		Element element = (Element) list.get(0);
		Element userEle = element.element("user");
		Element passwordEle = element.element("password");
		List propertieEles = element.elements("properties");

		this.user = userEle.getText();
		this.password = passwordEle.getText();
		this.properties = new HashMap();

		for (int i = 0; i < propertieEles.size(); i++) {
			Element ele = (Element) propertieEles.get(i);
			Attribute key = ele.attribute("key");
			String value = key.getValue();
			String text = ele.getText();
			this.properties.put(value, text);
		}
	}

	public boolean sendEmail(Email email, boolean flag) throws JavaMailUtilsException, MessagingException {
		if (email != null) {
			Properties props = new Properties();
			if (flag) {
				MailSSLSocketFactory sf = null;
				try {
					sf = new MailSSLSocketFactory();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
				sf.setTrustAllHosts(true);
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.ssl.socketFactory", sf);
			}

			if ((this.properties != null) && (!this.properties.isEmpty())) {
				Set keys = this.properties.keySet();
				Iterator iterator = keys.iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					String values = (String) this.properties.get(key);
					props.setProperty(key, values);
				}

			}

			if ((this.user != null) && (this.password != null)) {
				Authenticator auth = new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(JavaMailUtils.this.user, JavaMailUtils.this.password);
					}
				};
				Session session = Session.getDefaultInstance(props, auth);

				session.setDebug(true);

				MimeMessage msg = new MimeMessage(session);

				msg.setFrom(new InternetAddress(email.getSender()));

				Map addressee = email.getAddressee();

				if ((addressee != null) && (!addressee.isEmpty())) {
					Set keySet = addressee.keySet();
					Iterator iterator = keySet.iterator();
					while (iterator.hasNext()) {
						String strKey = (String) iterator.next();
						if ("TO".equals(strKey))
							msg.setRecipients(MimeMessage.RecipientType.TO, (String) addressee.get(strKey));
						else if ("CC".equals(strKey))
							msg.setRecipients(MimeMessage.RecipientType.CC, (String) addressee.get(strKey));
						else if ("BCC".equals(strKey))
							msg.setRecipients(MimeMessage.RecipientType.BCC, (String) addressee.get(strKey));
						else
							msg.setRecipients(MimeMessage.RecipientType.TO, (String) addressee.get(strKey));
					}
				} else {
					throw new JavaMailUtilsException("没有设置收件人信息");
				}

				msg.setSubject(email.getTitle());
				msg.setContent(email.getMimeMultipart());
				Transport.send(msg);
				return true;
			}
			throw new JavaMailUtilsException("没有账户跟密码");
		}

		return false;
	}

	public boolean sendEmail(Email email) throws JavaMailUtilsException, MessagingException {
		return sendEmail(email, false);
	}

	static {
		try {
			document = new SAXReader().read(JavaMailUtils.class.getResourceAsStream("/javamail_config.xml"));
		} catch (DocumentException e) {
			throw new RuntimeException("配置文件加载错误!请查看是否有配置文件!");
		}
	}

}