package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.CoRecruitDao;
import entity.CoRecruitEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class CoRecruitDaoImplTest {

	@Autowired
	CoRecruitDao coRecruitDao;
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void addRecruitTest(){
		CoRecruitEntity entity = new CoRecruitEntity();
		entity.setCompanyId(1);
		entity.setTitle("产品开发");
		coRecruitDao.addRecruit(entity);
	}


}
