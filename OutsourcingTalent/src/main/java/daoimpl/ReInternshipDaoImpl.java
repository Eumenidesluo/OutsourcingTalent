package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReInternshipDao;
import entity.ReInternshipEntity;

public class ReInternshipDaoImpl extends HibernateDaoSupport implements ReInternshipDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	public Integer addInternship(ReInternshipEntity entity) {
		return (Integer)getHibernateTemplate().save(entity);
	}

	public Boolean deleteInternship(ReInternshipEntity entity) {
		try {
			getHibernateTemplate().delete(entity);
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	public List<?> findInternshipsByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReInternshipEntity e where e.resumeId=?", resumeId);
	}

	public ReInternshipEntity findInternshipByinternshioId(int internshipId) {
		return (ReInternshipEntity)getHibernateTemplate().find("from ReInternshipEntity e where e.internshipId=?", internshipId).get(0);
	}

	public void deleteInternshipByInternshipId(int internshipId) {
		ReInternshipEntity entity = findInternshipByinternshioId(internshipId);
		deleteInternship(entity);
	}

	public void deleteInternshipsByResumeId(int resumeId) {
		List<?> internships = findInternshipsByResumeId(resumeId);
		for (Object object:internships){
			if (object!=null) {
				deleteInternship((ReInternshipEntity)object);
			}
		}
	}

	public Boolean updateInternship(ReInternshipEntity entity) {
		try {
			getHibernateTemplate().update(entity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
