package serviceimpl;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import dao.ReEducationDao;
import service.ResumeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
@Repository
public class ResumeServiceImplTest {

	@Autowired
	ResumeService resumeservice;
	@Autowired
	ReEducationDao reEducationDao;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddResumeInformations() {
//		ResumeEntity entity = new ResumeEntity();
//		entity.setUserId(1);
//		entity.setPhone("15988425431");
////		entity.setUserId(9);
//		String json = JSON.toJSONString(entity);
//		String string = resumeservice.addResumeInformations("resume", json);
//		System.out.println(string);
		List<?> list = reEducationDao.findEducationsByResumeId(2);
		String json = JSON.toJSONString(list);
		System.out.println(json);
		Integer string = resumeservice.addResumeInformations("education", json,2);
		System.out.println(string);
	}

}
