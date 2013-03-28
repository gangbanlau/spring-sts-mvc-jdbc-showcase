package my.mycompany.myapp.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.service.IProductsService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:root-context.xml",
		"classpath*:servlet-context.xml",
		"classpath*:datasource.xml" })
@TransactionConfiguration
@Transactional
public class ProductsServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImplTest.class);
	
	@Autowired
	IProductsService productService;
	
	@Test
	public void testIncreasePrice() {
		double oldPrice = productService.findOneProduct(1L).getPrice().doubleValue();
		
		this.productService.increasePrice(100);
		
		double newPrice = productService.findOneProduct(1L).getPrice().doubleValue();
		
		Assert.assertEquals("Increase price incorrect?", newPrice, oldPrice * 2);
	}
	
	@Test
	public void testFineOneProduct() {
		Assert.assertEquals("product not exists?", true, 
				null != productService.findOneProduct(1L));		
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void testFindOneProductNotExists() {
		productService.findOneProduct(1000L);
	}
	
	@Ignore("Not Ready to Run")  
	@Test(timeout = 1000)
	public void testdivisionWithException() {
		logger.warn("Not Ready to Run");
	}  
 	
}
