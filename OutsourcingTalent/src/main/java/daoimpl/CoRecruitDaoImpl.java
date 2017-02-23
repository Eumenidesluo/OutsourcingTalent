package daoimpl;

import java.util.List;

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

}
