package daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CoRecruitDao;
import entity.CoRecruitEntity;

public class CoRecruitDaoImpl extends HibernateDaoSupport implements CoRecruitDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addRecruit(CoRecruitEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteRecruit(CoRecruitEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateRecruit(CoRecruitEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public CoRecruitEntity findRecruitByRecruitId(int recruitId) {
		return (CoRecruitEntity)getHibernateTemplate().find("from CoRecruitEntity e where e.recruitId=?", recruitId).get(0);
	}

	public List<?> findRecruitsByCompanyId(int companyId) {
		return getHibernateTemplate().find("from CoRecruitEntity e where e.companyId=?", companyId);
	}
	
	public List<?> findRecruitsLimit(int begin,int max,String tag){
		Session session = getSessionFactory().getCurrentSession();
		if (tag == null) {
			Query query = session.createQuery("from CoRecruitEntity e");
			query.setFirstResult(begin);
			query.setMaxResults(max);
			List<?> list = query.list();
			return list;
		}else{
			try {		
				Query query = session.createQuery("from CoRecruitEntity e where e.label=?");
				query.setString(0, tag);
				query.setFirstResult(begin);
				query.setMaxResults(max);
				List<?> list = query.list();
				return list;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		
	}

	@Override
	public List<?> findByKeyWord(String keyWord) {
		keyWord = "%"+keyWord+"%";
		List<?> list = getHibernateTemplate().find("from CoRecruitEntity as e where e.title like ?", keyWord);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
}
