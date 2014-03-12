package my.mycompany.myapp.service.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.repository.IProductDao;
import my.mycompany.myapp.service.IProductsService;

@Transactional
@Service
public class ProductsServiceImpl implements IProductsService {
	private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);

	@Autowired
	IProductDao productDao;

	@RequiresPermissions("product:query")
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

	public Product findOneProduct(Long id) {
		return productDao.findOne(id);
	}

	public void deleteProduct(Long id) {
		productDao.delete(id);
	}

	public Product insertProduct(Product prod) {
		return productDao.insert(prod);
	}

	@RequiresPermissions("product:manage")
	public void increasePrice(int percentage) {
		List<Product> products = productDao.findAll();
		if (products != null) {
			for (Product product : products) {
				double newPrice = product.getPrice().doubleValue() * (100 + percentage) / 100;
				product.setPrice(newPrice);
				productDao.update(product);
			}
		} else {
			logger.warn("no product to increase price");
		}
	}
}