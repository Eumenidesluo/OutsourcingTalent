package serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import component.MD5Util;
import component.ServiceException;
import dao.UserDao;
import entity.UserEntity;
import service.RegisterValidate;

/**
 * Created by Eumenides on 2017/2/18.
 */
@Service("registerValidate")
public class RegisterValidateImpl implements RegisterValidate{
    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * ����ע��
     */

    public void processregister(String password,String email){
        UserEntity user=new UserEntity();
        user.setPassword(password);
        user.setEmail(email);
        user.setRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setStatus(0);
        ///������ڰ�ȫ�����Խ������봦��ĸ����ӵ㣬�����������򵥴���
        //user.setValidateCode(MD5Tool.MD5Encrypt(email));
        user.setValidateCode(MD5Util.encode2hex(email));

        userDao.save(user);//����ע����Ϣ

        ///�ʼ�������
        StringBuffer sb=new StringBuffer("����������Ӽ����˺ţ�48Сʱ��Ч����������ע���˺ţ�����ֻ��ʹ��һ�Σ��뾡�켤�</br>");
        sb.append("<a href=\"http://localhost:8082/register?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("\">http://localhost:8082/register?action=activate&email=");
        sb.append(email);
        sb.append("&validateCode=");
        sb.append(user.getValidateCode());
        sb.append("</a>");

        //�����ʼ�
        SendEmailImpl sendEmail=(SendEmailImpl) SpringContextHolder.getBean("sendEmail");
        sendEmail.send(email,sb.toString());
        System.out.println("�����ʼ�");

    }

    /**
     * ������
     * @throws ParseException
     */
    ///���ݼ������email����
    public void processActivate(String email , String validateCode)throws ServiceException, ParseException {
        //���ݷ��ʲ㣬ͨ��email��ȡ�û���Ϣ
        UserEntity user=userDao.findByEmail(email);
        //��֤�û��Ƿ����
        if(user!=null) {
            //��֤�û�����״̬
            if(user.getStatus()==0) {
                ///û����
                Date currentTime = new Date();//��ȡ��ǰʱ��
                //��֤�����Ƿ����
                currentTime.before(user.getRegisterTime());
                if(currentTime.before(user.getLastActivateTime())) {
                    //��֤�������Ƿ���ȷ
                    if(validateCode.equals(user.getValidateCode())) {
                        //����ɹ��� //�������û��ļ���״̬��Ϊ�Ѽ���
                        System.out.println("==sq==="+user.getStatus());
                        user.setStatus(1);//��״̬��Ϊ����
                        System.out.println("==sh==="+user.getStatus());
                        userDao.update(user);
                    } else {
                        throw new ServiceException("�����벻��ȷ");
                    }
                } else { throw new ServiceException("�������ѹ��ڣ�");
                }
            } else {
                throw new ServiceException("�����Ѽ�����¼��");
            }
        } else {
            throw new ServiceException("������δע�ᣨ�����ַ�����ڣ���");
        }

    }
}
