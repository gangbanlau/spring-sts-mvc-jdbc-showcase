package my.mycompany.myapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
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
		"classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class ScheduledJobTest {
	
	private final static Logger LOGGER=LoggerFactory.getLogger(ScheduledJobTest.class);
	@Autowired
	private IScheduledJob scheduledJob;
	
	@Test
	public void TestRunPreferentialPrice() {
               LOGGER.info("execute TestRunPreferentialPrice");
               this.scheduledJob.RunPreferentialPrice();
	}
	@Test
	public void TestRestorePrice() {
               LOGGER.info("execute TestRestorePrice");
               this.scheduledJob.RestorePrice();
	}
}
