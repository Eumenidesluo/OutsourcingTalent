package serviceimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.ProjectStatusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
@Repository
public class ProjectStatusServiceImplTest {

	@Autowired
	ProjectStatusService projectStatusService;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testUpdateProjectStatus() {
	
		
	}

}
