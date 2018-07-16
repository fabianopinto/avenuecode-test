package com.avenuecode.fabianopinto.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.avenuecode.fabianopinto.domain.Image;
import com.avenuecode.fabianopinto.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ImageRepositoryIntegrationTests {

	@Autowired
	private ImageRepository imageRepository;

	@Test
	public void findsAllImages() {
		List<Image> images = imageRepository.findAll();
		assertEquals("Must be a total of four images", 4, images.size());
		for (Image image : images) {
			assertNotNull("Every image must be related to a product", image.getProduct());
		}
	}

	@Test
	public void findsSpecificProductImages() {
		List<Image> images = imageRepository.findAll(makeImageExample());
		assertEquals("Must be two images for this product", 2, images.size());
		for (Image image : images) {
			assertEquals("Each image must be related to this product", Integer.valueOf(2), image.getProduct().getId());
		}
	}

	private Example<Image> makeImageExample() {
		Product product = new Product();
		product.setId(2);
		Image image = new Image();
		image.setProduct(product);
		return Example.of(image);
	}

}
