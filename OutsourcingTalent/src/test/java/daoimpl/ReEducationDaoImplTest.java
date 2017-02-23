package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.ReEducationDao;
import entity.ReEducationEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class ReEducationDaoImplTest {

	@Autowired
	ReEducationDao reEducationDao;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAddEducation() {
	}

	@Test
	public void testDeleteEducation() {
	}

	@Test
	public void testFindEducationsByResumeId() {
	}

	@Test
	public void testUpdateEducation() {
		ReEducationEntity educationEntity = reEducationDao.findByEducationId(2);
		educationEntity.setSchool("HDU");
		reEducationDao.updateEducation(educationEntity);
	}

	@Test
	public void testFindByEducationId() {
	}

}
