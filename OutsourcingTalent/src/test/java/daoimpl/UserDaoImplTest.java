package daoimpl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import component.MD5Util;
import dao.BaseTestInterface;
import dao.UserDao;
import entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META_INF/applicationContext.xml")
@Repository
public class UserDaoImplTest implements BaseTestInterface{

	@Autowired
	UserDao userDao;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSave() {
		System.out.println(userDao);
		UserEntity entity = new UserEntity();
		entity.setEmail("1481726071@qq.com");
		entity.setPassword("12345");
		entity.setStatus(0);
//		entity.setRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
		entity.setValidateCode(MD5Util.encode2hex("1481726071@qq.com"));
		String json = JSON.toJSONString(entity);
		System.out.println(json);
		entity = JSON.parseObject(json,UserEntity.class);
		System.out.println(entity.getPassword());
//		userDao.save(entity);
	}


	public void testSavePassword() {

	}

	
	public void testFindByEmail() {
	}

	
	public void testQueryByEntity() {
	}

	
	public void testUpdate() {

	}

	
	public void testIsNameUnique() {
	
	}

	
	public void testIsEmailUnique() {
	
	}

}
