package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.ResumeDao;
import entity.ResumeEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class ResumeDaoImplTest {

	@Autowired
	ResumeDao resumeDao;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSaveResume() {
		
		
	}

	@Test
	public void testDeleteResume() {
	}

	@Test
	public void testUpdateResume() {
		ResumeEntity entity = resumeDao.findResume(1);
		entity.setPhone("15988425431");
		resumeDao.updateResume(entity);
	}

}
