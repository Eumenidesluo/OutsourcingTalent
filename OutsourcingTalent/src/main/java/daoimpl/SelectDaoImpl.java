package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.SelectDao;
import entity.SelectUserRecruitEntity;

public class SelectDaoImpl extends HibernateDaoSupport implements SelectDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public Integer addSelect(SelectUserRecruitEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public void deleteSelect(SelectUserRecruitEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateSelect(SelectUserRecruitEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public List<?> findSelectsByUserId(int userId) {
		return getHibernateTemplate().find("from SelectUserRecruitEntity e where e.userId=?", userId);
	}

	public List<?> findSelectsByRecruitId(int recruitId) {
		return getHibernateTemplate().find("from SelectUserRecruitEntity e where e.recruitId=?", recruitId);
	}

	public SelectUserRecruitEntity findSelectByUserIdAndRecruitId(int recruitId,int userId){
		List<?> list = getHibernateTemplate().find("from SelectUserRecruitEntity e where e.recruitId=? and e.userId = ?", recruitId,userId);
		if (list.size() == 0) {
			return null;
		}
		return (SelectUserRecruitEntity)list.get(0);
	}
}
