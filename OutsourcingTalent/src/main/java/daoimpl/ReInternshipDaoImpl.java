package daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.ReInternshipDao;
import entity.ReInternshipEntity;

public class ReInternshipDaoImpl extends HibernateDaoSupport implements ReInternshipDao {

	public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
	@Override
	public void addInternship(ReInternshipEntity entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void deleteInternship(ReInternshipEntity entity) {
		getHibernateTemplate().delete(entity);
	}

	@Override
	public List<?> findInternshipsByResumeId(int resumeId) {
		return getHibernateTemplate().find("from ReInternshipEntity e where e.resumeId=?", resumeId);
	}

	@Override
	public ReInternshipEntity findInternshipByinternshioId(int internshipId) {
		return (ReInternshipEntity)getHibernateTemplate().find("from ReInternshipEntity e where e.internshipId=?", internshipId).get(0);
	}

	@Override
	public void deleteInternshipByInternshipId(int internshipId) {
		ReInternshipEntity entity = findInternshipByinternshioId(internshipId);
		deleteInternship(entity);
	}

	@Override
	public void deleteInternshipsByResumeId(int resumeId) {
		List<?> internships = findInternshipsByResumeId(resumeId);
		for (Object object:internships){
			if (object!=null) {
				deleteInternship((ReInternshipEntity)object);
			}
		}
	}

	@Override
	public void updateInternship(ReInternshipEntity entity) {
		getHibernateTemplate().update(entity);
	}

}
