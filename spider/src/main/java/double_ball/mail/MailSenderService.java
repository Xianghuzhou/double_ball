package double_ball.mail;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 类说明
 * @date 2017/9/8 下午6:20
 */
public interface MailSenderService {

    public void sendSimpleMail(String to, String subject, String content);

    public void sendHtmlMail(String to, String subject, String content);

    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
