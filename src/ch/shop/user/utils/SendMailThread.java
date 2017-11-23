package ch.shop.user.utils;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import ch.shop.user.vo.User;



public class SendMailThread extends Thread {
	private User user = null;
	private JavaMailUtils javaMailUtils = new JavaMailUtils();

	public SendMailThread(User user) {
		this.user = user;

	}

	public void run() {
		// 发送邮件
		String path = "http://localhost：8080/web_ssh/user_active.action?code=" + user.getCode();
		Email email = new Email();
		// 创建MimeMultipart对象
		MimeMultipart mimeMultipart = new MimeMultipart();
		// 创建MimeBodyPart对象，至于功能不解释，不会的请温习javaMail
		MimeBodyPart bodyPart = new MimeBodyPart();
		try {
			bodyPart.setContent("这是一封激活邮件！<br/>" + "请点击以下连接<a href=" + path + ">激活你的账户</a><br>"
					+ "或者拷贝以上连接到你的浏览器的地址栏中<br/>" + "本邮件由系统自动发送，请误回复", "text/html;charset=utf-8");
			mimeMultipart.addBodyPart(bodyPart);
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}

		// // 添加文件
		// MimeBodyPart bodyPart1 = new MimeBodyPart();
		// bodyPart1.attachFile(new File("E://Users/Pictures/Saved
		// Pictures/5.jpg"));
		// mimeMultipart.addBodyPart(bodyPart1);

		// 把需要的资料分别添加到email对象中
		email.setMimeMultipart(mimeMultipart);
		email.setSender("1065670082@qq.com");
		email.setTitle("商城激活邮件");

		// 添加联系人，与模式
		Map<String, String> addressee = new HashMap();
		addressee.put(JavaMailUtils.TO, user.getEmail());
		addressee.put(JavaMailUtils.CC, user.getEmail());
		addressee.put(JavaMailUtils.BCC, user.getEmail());
		email.setAddressee(addressee);

		// 调用方法，如果是true，是ssl加密，如QQ邮箱！如果是false，base64加密，如163邮箱
		try {
			javaMailUtils.sendEmail(email, true);
		} catch (JavaMailUtilsException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
