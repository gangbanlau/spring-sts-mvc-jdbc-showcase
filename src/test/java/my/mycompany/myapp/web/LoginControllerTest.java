package my.mycompany.myapp.web;

import java.util.logging.Filter;

import my.mycompany.myapp.domain.User;
import my.mycompany.myapp.service.IUsersService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:root-context.xml",
		"classpath*:servlet-context.xml", "classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class LoginControllerTest {

	@Autowired
	WebApplicationContext wac;	// cached
	
	@Autowired 
	MockServletContext servletContext; // cached
	
	@Autowired
	IUsersService userService;
	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
		filterProxy.setServletContext(this.servletContext);
		filterProxy.setTargetBeanName("shiroFilter");
		//filterProxy.setBeanName("shiroFilter");
		filterProxy.setTargetFilterLifecycle(true);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(filterProxy).build();
	}

	@After
	public void teardown() {
	}
	
	@Test
	public void testLogin() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
	}
	
	@Test
	@Ignore
	public void verifyPassphase() throws Exception
	{
		this.testLogin();
		
		User newUser = new User();
		newUser.setUserId("testuser");
		newUser.setCreatedDate(new java.util.Date());
		
		User newUserAdded = userService.insertUser(newUser, "testpassword");
		
		this.mockMvc.perform(post("/login").param("user", "testuser").param("password", "testpassword"))
			.andExpect(status().isOk())
			.andExpect(redirectedUrl("/inventory"));
	}	
}
