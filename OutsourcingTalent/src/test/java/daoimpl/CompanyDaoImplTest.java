package daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.CompanyDao;
import entity.CompanyEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
public class CompanyDaoImplTest {

	@Autowired
	CompanyDao companyDao;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddCompany() {
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setName("alibaba");
		companyDao.addCompany(companyEntity);
	}

	@Test
	public void testDeleteCompany() {
	}

	@Test
	public void testUpdateCompany() {
	}

	@Test
	public void testFindCompanyById() {
	}

	@Test
	public void testFindComaniesByName() {
	}

}
