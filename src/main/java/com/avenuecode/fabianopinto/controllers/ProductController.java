package com.avenuecode.fabianopinto.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avenuecode.fabianopinto.domain.Image;
import com.avenuecode.fabianopinto.domain.Product;
import com.avenuecode.fabianopinto.json.Views;
import com.avenuecode.fabianopinto.repositories.ProductRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public MappingJacksonValue findAllProducts(@RequestParam(required = false) List<String> options) {
		List<Product> products = productRepository.findAll();
		MappingJacksonValue wrapper = new MappingJacksonValue(products);
		wrapper.setSerializationView(resolveSerializationView(options));
		return wrapper;
	}

	@RequestMapping(value = "/{id}")
	public MappingJacksonValue findOneProduct(@PathVariable Integer id, @RequestParam(required = false) List<String> options) {
		Product product = productRepository.findOne(id);
		MappingJacksonValue wrapper = new MappingJacksonValue(product);
		wrapper.setSerializationView(resolveSerializationView(options));
		return wrapper;
	}

	private Class<?> resolveSerializationView(List<String> options) {
		if (options == null) {
			return Views.Summary.class;
		}
		if (options.contains("complete") || options.contains("children") && options.contains("images")) {
			return Views.Complete.class;
		}
		if (options.contains("children")) {
			return Views.Children.class;
		}
		if (options.contains("images")) {
			return Views.Images.class;
		}
		return Views.Summary.class;
	}

	@RequestMapping(value = "/{id}/children")
	@JsonView(Views.Summary.class)
	public Collection<Product> findOneProductChildren(@PathVariable Integer id) {
		return productRepository.findOne(id).getChildren();
	}

	@RequestMapping(value = "/{id}/images")
	@JsonView(Views.Summary.class)
	public Collection<Image> findOneProductImages(@PathVariable Integer id) {
		return productRepository.findOne(id).getImages();
	}

}
