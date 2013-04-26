package my.mycompany.myapp.web;

import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import my.mycompany.myapp.service.IProductsService;

@Controller
public class InventoryController implements IBaseController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private IProductsService productsService;

	@RequiresPermissions( {"product:query"} )
	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public String inventory(Locale locale, Model model) {
		String now = (new java.util.Date()).toString();
		
		model.addAttribute("now", now);
		model.addAttribute("products", productsService.findAllProducts());

		return "inventory/products";
	}
}