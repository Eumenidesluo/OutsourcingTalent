package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CompanyDao;
import entity.CompanyEntity;

public class CompanyDaoImpl extends HibernateDaoSupport implements CompanyDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	
	public void addCompany(CompanyEntity entity) {
		getHibernateTemplate().save(entity);
	}

	public void deleteCompany(CompanyEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	public void updateCompany(CompanyEntity entity) {
		getHibernateTemplate().update(entity);
	}

	public CompanyEntity findCompanyById(int Id) {
		List<?> list = getHibernateTemplate().find("from CompanyEntity e where e.companyId=?", Id);
		if (list.size() == 0) {
			return null;
		}
		return (CompanyEntity)list.get(0);
	}

	public List<?> findComaniesByName(String name) {
		List<?> list = getHibernateTemplate().find("from CompanyEntity e where e.name=?", name);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

}
