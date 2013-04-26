package my.mycompany.myapp.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import my.mycompany.myapp.domain.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Ignore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:web.xml","classpath*:root-context.xml",
		"classpath*:servlet-context.xml",
		"classpath*:datasource.xml"})
@TransactionConfiguration
@Transactional
public class UsersServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImplTest.class);
	
	@Autowired
	IUsersService userService;
	
	@Autowired
	DefaultWebSecurityManager securityManager;
	
	@Test
	public void testAddUser()
	{
		User newUser = new User();
		newUser.setUserId("testuser");
		newUser.setCreatedDate(new java.util.Date());
		
		User newUserAdded = userService.insertUser(newUser, "testpassword");
	}
	
	@Test
	@Ignore
	public void verifyPassphase() throws Exception
	{	
		User newUser = new User();
		newUser.setUserId("testuser");
		newUser.setCreatedDate(new java.util.Date());
		
		User newUserAdded = userService.insertUser(newUser, "testpassword");
		
		UsernamePasswordToken token = new UsernamePasswordToken("testuser", "testpassword");
		Subject currentUser = SecurityUtils.getSubject();

		currentUser.login(token);	        		
	}	
}
