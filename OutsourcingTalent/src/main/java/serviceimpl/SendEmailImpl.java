package serviceimpl;

import service.SendEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Eumenides on 2017/2/18.
 */
public class SendEmailImpl implements SendEmail{

    private final String HOST = "smtp.163.com";
    private final String PROTOCOL = "smtp";
    private final int PORT = 25;
    private final String FROM = "chenqi11545@163.com";//发件人的email
    private final String PWD = "cqlove520";//发件人密码

    public SendEmailImpl() {
    }
    /**
     * 获取Session
     * @return
     */

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);//设置服务器地址
        props.put("mail.store.protocol" , PROTOCOL);//设置协议
        props.put("mail.smtp.port", PORT);//设置端口
        props.put("mail.smtp.auth" , true);

        Authenticator authenticator = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);

        return session;
    }

    public Boolean send(String toEmail , String content) {
        Session session = getSession();
        try {
//            System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
            return true;
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
    }

}
