package daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.NoticeDao;
import entity.NoticeEntity;

public class NoticeDaoImpl extends HibernateDaoSupport implements NoticeDao{

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public List<?> queryByUserId(String userId) {
		List<?> list = getHibernateTemplate().find("from NoticeEntity e where e.userId=?", userId);
		return list;
	}
	@Override
	public List<NoticeEntity> queryByUserId(String userId, int number) {
		List<?> list = queryByUserId(userId);
		List<NoticeEntity> values = new ArrayList<>();
		
		for(int i = 0;i < number;i++){
			values.add((NoticeEntity)list.get(i));
		}
		return values;
	}
	

}
