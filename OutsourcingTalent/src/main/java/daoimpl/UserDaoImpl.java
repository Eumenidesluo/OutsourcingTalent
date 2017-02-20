package daoimpl;

import dao.UserDao;
import entity.UserEntity;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Eumenides on 2017/2/18.
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    public void save(UserEntity user) {
        getHibernateTemplate().save(user);
    }

    public void savePassword() {
        UserEntity user=new UserEntity();
        user.setPassword("123456");
        getHibernateTemplate().saveOrUpdate(user);
    }

    public void delete() {

    }

    public UserEntity findByEmail(String email) {
        List<?> list=getHibernateTemplate().find("from UserEntity as u where u.email=?",email);
        return (UserEntity) list.get(0);
    }

    public List<?> queryByEntity(UserEntity userEntity) {
        return getHibernateTemplate().findByExample(userEntity);
    }

    public void update(UserEntity user) {
        getHibernateTemplate().update(user);
    }

    public Boolean isNameUnique(String username) {
        return null;
    }

    public int isEmailUnique(String email) {
        List<?> li = getHibernateTemplate().find("from UserEntity u where u.email=?",email);
        if (li.size()==1)
            return  0;
        else if (li.size()==0)
            return 1;
        else
            return -1;
    }
}
