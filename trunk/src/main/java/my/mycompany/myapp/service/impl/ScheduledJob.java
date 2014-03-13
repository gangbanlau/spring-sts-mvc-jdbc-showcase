package my.mycompany.myapp.service.impl;

import java.util.List;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.repository.IProductDao;
import my.mycompany.myapp.service.IScheduledJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledJob implements IScheduledJob {
	 private static final Logger logger= LoggerFactory.getLogger(ScheduledJob.class);
	 @Autowired
	 private IProductDao productDao;

	    @Scheduled(cron = "0 0 0  ? * SAT")
	    @Override
	    public void RunPreferentialPrice() {
	      logger.info("Execute RunPreferentialPrice!");
	      List<Product> products = productDao.findAll();
			if (products != null) {
				for (Product product : products) {
					double newPrice = product.getPrice().doubleValue() * (100 - 15) / 100;
					product.setPrice(newPrice);
					productDao.update(product);
				}
			} else {
				logger.warn("no product to increase price");
			}
	    
	    }

	    @Scheduled(cron = "0 0 0 ? * MON")
	    @Override
	    public void RestorePrice() {
	      logger.info("Execute RestorePrice!");
	      List<Product> products = productDao.findAll();
			if (products != null) {
				for (Product product : products) {
					double newPrice = product.getPrice().doubleValue() * (100 + 15) / 100;
					product.setPrice(newPrice);
					productDao.update(product);
				}
			} else {
				logger.warn("no product to increase price");
			}
	    }
}
