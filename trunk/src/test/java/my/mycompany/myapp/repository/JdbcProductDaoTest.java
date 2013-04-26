package my.mycompany.myapp.repository;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.repository.IProductDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:web.xml","classpath*:root-context.xml",
		"classpath*:servlet-context.xml",
		"classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class JdbcProductDaoTest {

	private static final Logger logger = LoggerFactory.getLogger(JdbcProductDaoTest.class);
	
	@Autowired
	IProductDao productDao;

    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	logger.debug("@BeforeClass - oneTimeSetUp");
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	logger.debug("@AfterClass - oneTimeTearDown");
    }
    

    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        // logic to verify the initial state before a transaction is started
    	logger.debug("@BeforeTransaction verifyInitialDatabaseState");
    }


    @AfterTransaction
    public void verifyFinalDatabaseState() {
        // logic to verify the final state after transaction has rolled back
    	logger.debug("@AfterTransaction verifyFinalDatabaseState");
    }
    
	@Before
	public void setUp() {
		logger.debug("@Before setUp");
	}

	@After
	public void tearDown() {
		logger.debug("@After tearDown");
	}

	@Test
	public void testDelete() {
		productDao.delete(1L);
		productDao.delete(2L);
		productDao.delete(3L);
	}
	
	@Test
	public void testCount() {
		//Assert.assertEquals("wrong number of products?", 3, productDao.count());
		assertEquals(3, productDao.count());
	}
	
	@Test
	public void testExists() {
		//Assert.assertEquals("product not exists?", true, productDao.exists(1L));
		assertTrue(productDao.exists(1L));
	}
	
	@Test 
	public void testFindOne() {
		Assert.assertEquals("product not found?", "Lamp", productDao.findOne(1L).getName());
		Assert.assertEquals("product not found?", "Lamp", productDao.findOne("Lamp").getName());
	}
	
	@Test
	public void testFindAll() {

		List<Product> products = productDao.findAll();

		Assert.assertEquals("wrong number of products?", 3, products.size());

	}
	
	@Test
	public void testInsert() {
		Product prod = new Product();
		prod.setName("Train");
		prod.setPrice(2999D);
		prod = productDao.insert(prod);
		/*
		 * failed if using mysql db and we run test case twice after new table
		 */
		//Assert.assertEquals("insert fail?", 4, prod.getId().longValue());
	}
	
	@Test
	public void testUpdate() {

		List<Product> products = productDao.findAll();

		for (Product p : products) {
			p.setPrice(200.12);
			productDao.update(p);
		}

		List<Product> updatedProducts = productDao.findAll();
		for (Product p : updatedProducts) {
			Assert.assertEquals("wrong price of product?", 200.12, p.getPrice());
		}

	}

}
