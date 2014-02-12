package my.mycompany.myapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import my.mycompany.myapp.domain.Product;
import my.mycompany.myapp.repository.IProductDao;

@Repository("IProductDao")
// @Datasource("dataSource")
public class JdbcProductDao extends JdbcDaoSupport implements IProductDao {

	private static final Logger logger = LoggerFactory.getLogger(JdbcProductDao.class);

	private SimpleJdbcInsert insertProduct;
	
	@Autowired
	JdbcProductDao(DataSource dataSource) {
		setDataSource(dataSource);
		insertProduct =
	                new SimpleJdbcInsert(dataSource)
	                        .withTableName("products")
	                        .usingGeneratedKeyColumns("id");		
	}

	public long count() {
		String sql = "SELECT count(*) FROM products";
		
		return this.getJdbcTemplate().queryForLong(sql);
	}
	
	public boolean exists(Long id) {
		String sql = "SELECT count(*) FROM products WHERE id = ?";
		
		int rowCount = this.getJdbcTemplate().queryForInt(sql, id);
		
		return (rowCount >= 1)?true:false;
	}
	
	public Product insert(Product prod) {
		logger.info("Inserting product: " + prod.getName());
		
		 Map<String, Object> parameters = new HashMap<String, Object>(3);
	        parameters.put("name", prod.getName());	      
	        parameters.put("price", prod.getPrice());
	        parameters.put("description", prod.getDescription());
	        Number newId = insertProduct.executeAndReturnKey(parameters);
	        prod.setId(newId.longValue());		
		return prod;
	}
	
	public void update(Product prod) {
		logger.info("Updating product: " + prod.getName());

		// Don't allow change product name
		
		String sql = "UPDATE products SET description = ?, price = ? WHERE id = ?";

		int count = getJdbcTemplate().update(
				sql,
				new Object[] { prod.getDescription(), prod.getPrice(),
						prod.getId() });
		logger.info("Rows affected: " + count);
	}
	
	public void delete(Long id) {
		String sql = "DELETE FROM products WHERE id = ?";
		
		this.getJdbcTemplate().update(sql, id);
	}
	
	public Product findOne(Long id) {
		String sql = "SELECT id, name, price, description FROM products WHERE id = ?";
		
		Product prod = getJdbcTemplate().queryForObject(sql, new Object[] { id },
				new ProductMapper()
				);
		
		return prod;
	}

	public Product findOne(String name) {
		String sql = "SELECT id, name, price, description FROM products WHERE name = ?";
		Product prod = getJdbcTemplate().queryForObject(sql, new Object[] { name },
				new ProductMapper()
				);
		
		return prod;		
	}
	
	public List<Product> findAll() {
		logger.info("Getting products!");
		List<Product> products = getJdbcTemplate().query(
				"SELECT id, name, description, price FROM products",
				new ProductMapper());
		return products;
	}

	private static class ProductMapper implements
			ParameterizedRowMapper<Product> {

		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product prod = new Product();
			prod.setId(rs.getLong("id"));
			prod.setName(rs.getString("name"));
			prod.setDescription(rs.getString("description"));
			prod.setPrice(new Double(rs.getDouble("price")));
			return prod;
		}

	}

}