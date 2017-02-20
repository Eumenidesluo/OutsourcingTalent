package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.PersonalInfDao;
import entity.PersonalInfEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class PersonalInfDaoImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpdate() {
//		PersonalInfDao personalInfDao = (PersonalInfDao)SpringContextHolder.getBean("personalInfDao");
		PersonalInfDao personalInfDao = new PersonalInfDaoImpl();
		PersonalInfEntity entity = new PersonalInfEntity();
		entity.setEmail("1481726071@qq.com");
		entity.setName("aaaa");
		personalInfDao.update(entity);
	}

}
