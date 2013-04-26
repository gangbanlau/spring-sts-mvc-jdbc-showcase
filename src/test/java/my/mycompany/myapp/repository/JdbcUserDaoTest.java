package my.mycompany.myapp.repository;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import my.mycompany.myapp.domain.User;
import my.mycompany.myapp.repository.IUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:web.xml","classpath*:root-context.xml",
		"classpath*:servlet-context.xml",
		"classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class JdbcUserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(JdbcUserDaoTest.class);
	
	@Autowired
	IUserDao userDao;
	
	@Test
	public void testInsert() {
		User newUser = new User();
		newUser.setCreatedDate(new java.util.Date());
		newUser.setPassphrase("passphase");
		newUser.setSalt("salt");
		newUser.setUserId("userid");
		
		userDao.insert(newUser);
	}
}
