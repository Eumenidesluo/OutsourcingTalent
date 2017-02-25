package serviceimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.GroupService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
@Repository
public class GroupServiceImplTest {

	@Autowired
	GroupService groupService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
//		GroupEntity groupEntity = new GroupEntity();
//		groupEntity.setMemberId1(2);
//		groupEntity.setLeaderId(1);
//		groupEntity.setMemberId2(null);
//		groupService.deleteExcute(groupEntity, 2);
	}

}
