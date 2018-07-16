package com.avenuecode.fabianopinto.controllers;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class ProductControllerIntegrationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void findAllProductsSummary() throws Exception {
		mvc.perform(get("/products/"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	public void findAllProductsWithChildren() throws Exception {
		mvc.perform(get("/products/?options=children"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("[0]", hasKey("children")))
		.andExpect(jsonPath("[0]", not(hasKey("images"))));
	}

	@Test
	public void findAllProductsWithImages() throws Exception {
		mvc.perform(get("/products/?options=images"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("[0]", not(hasKey("children"))))
		.andExpect(jsonPath("[0]", hasKey("images")));
	}

	@Test
	public void findAllProductsComplete() throws Exception {
		mvc.perform(get("/products/?options=complete"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("[0]", hasKey("children")))
		.andExpect(jsonPath("[0]", hasKey("images")));
	}

	@Test
	public void findOneProductsSummary() throws Exception {
		mvc.perform(get("/products/2"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasKey("id")))
		.andExpect(jsonPath("$", hasKey("name")))
		.andExpect(jsonPath("$", hasKey("description")))
		.andExpect(jsonPath("$", not(hasKey("children"))))
		.andExpect(jsonPath("$", not(hasKey("images"))));
	}

	@Test
	public void findOneProductsWithChildren() throws Exception {
		mvc.perform(get("/products/2?options=children"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasKey("id")))
		.andExpect(jsonPath("$", hasKey("name")))
		.andExpect(jsonPath("$", hasKey("description")))
		.andExpect(jsonPath("$", hasKey("children")))
		.andExpect(jsonPath("$", not(hasKey("images"))));
	}

	@Test
	public void findOneProductsWithImages() throws Exception {
		mvc.perform(get("/products/2?options=images"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasKey("id")))
		.andExpect(jsonPath("$", hasKey("name")))
		.andExpect(jsonPath("$", hasKey("description")))
		.andExpect(jsonPath("$", not(hasKey("children"))))
		.andExpect(jsonPath("$", hasKey("images")));
	}

	@Test
	public void findOneProductsComplete() throws Exception {
		mvc.perform(get("/products/2?options=complete"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasKey("id")))
		.andExpect(jsonPath("$", hasKey("name")))
		.andExpect(jsonPath("$", hasKey("description")))
		.andExpect(jsonPath("$", hasKey("children")))
		.andExpect(jsonPath("$", hasKey("images")));
	}

	@Test
	public void findOneProductsChildren() throws Exception {
		mvc.perform(get("/products/2/children"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("[0]", hasKey("id")))
		.andExpect(jsonPath("[0]", hasKey("name")))
		.andExpect(jsonPath("[0]", hasKey("description")));
	}

	@Test
	public void findOneProductsImages() throws Exception {
		mvc.perform(get("/products/2/images"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("[0]", hasKey("id")))
		.andExpect(jsonPath("[0]", hasKey("type")));
	}

}
