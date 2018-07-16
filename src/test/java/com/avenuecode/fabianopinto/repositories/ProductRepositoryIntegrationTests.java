package com.avenuecode.fabianopinto.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.fabianopinto.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductRepositoryIntegrationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void findsAllProducts() {
		List<Product> products = productRepository.findAll();
		assertEquals("Must be a total of four products", 4, products.size());
		for (Product product : products) {
			assertFalse("All products must have a name", StringUtils.isEmpty(product.getName()));
		}
	}

	@Test
	public void findsAllProductsAndChildren() {
		List<Product> products = productRepository.findAll(makeProductSort());
		assertEquals("Must be a total of four products", 4, products.size());
		assertTrue("The first product has no children", products.get(0).getChildren().isEmpty());
		assertEquals("The second product has two children", 2, products.get(1).getChildren().size());
		assertTrue("The third product has no children", products.get(2).getChildren().isEmpty());
		assertTrue("The fourth product has no children", products.get(3).getChildren().isEmpty());
	}

	private Sort makeProductSort() {
		return new Sort("id");
	}

}
