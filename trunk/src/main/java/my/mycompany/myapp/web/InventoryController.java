package my.mycompany.myapp.web;

import java.util.Locale;

import my.mycompany.myapp.service.IProductsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InventoryController implements IBaseController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private IProductsService productsService;

	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public String inventory(Locale locale, Model model) {
		logger.info("Execute inventory!");
		
		String now = (new java.util.Date()).toString();
		
		model.addAttribute("now", now);
		model.addAttribute("products", productsService.findAllProducts());

		return "inventory/products";
	}
	
	@RequestMapping(value = "/inventory_easyui", method = RequestMethod.GET)
	public String inventoryEasyUI(Locale locale, Model model) {
		logger.info("Execute inventoryEasyUI!");
		
		String now = (new java.util.Date()).toString();
		
		model.addAttribute("now", now);
		model.addAttribute("products", productsService.findAllProducts());

		return "inventory/products_easyui";
	}
}