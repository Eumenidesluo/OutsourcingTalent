package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.UGRelateDao;
import entity.RelateUserGroupEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class PersonalInfDaoImplTest{

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpdate() {
		UGRelateDao ugRelateDao = new UGRelateDaoImpl();
		RelateUserGroupEntity relateUserGroupEntity = new RelateUserGroupEntity();
		relateUserGroupEntity.setGroupId(2);
		relateUserGroupEntity.setPlace(0);
		relateUserGroupEntity.setUserId(1);
		ugRelateDao.addRelate(relateUserGroupEntity);
	}

}
