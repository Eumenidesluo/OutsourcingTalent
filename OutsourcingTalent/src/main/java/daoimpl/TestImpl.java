package daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by Eumenides on 2017/2/19.
 */
public class TestImpl extends HibernateDaoSupport {
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
