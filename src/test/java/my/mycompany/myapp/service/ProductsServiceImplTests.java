package my.mycompany.myapp.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.service.IProductsService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:root-context.xml",
		"classpath*:servlet-context.xml",
		"classpath*:datasource.xml" })
public class ProductsServiceImplTests {

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
	
	@Test
	public void testFindOneProductNotExists() {
		boolean productNotFound = false;
		
		try {
			productService.findOneProduct(1000L);
		}
		catch (EmptyResultDataAccessException e) {
			productNotFound = true;
		}
		
		Assert.assertEquals("product exists?", true, productNotFound);		
	}
}
