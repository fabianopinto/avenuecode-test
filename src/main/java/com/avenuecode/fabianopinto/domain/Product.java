package com.avenuecode.fabianopinto.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avenuecode.fabianopinto.json.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Data
public class Product implements Serializable {

	private static final long serialVersionUID = -4157456740359566087L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Summary.class)
	private Integer id;

	@Column(nullable = false)
	@JsonView(Views.Summary.class)
	private String name;

	@JsonView(Views.Summary.class)
	private String description;

	@ManyToOne
	@JsonIgnore
	private Product parent;

	@OneToMany(mappedBy = "parent")
	@JsonView(Views.Children.class)
	private Collection<Product> children;

	@OneToMany(mappedBy = "product")
	@JsonView(Views.Images.class)
	private Collection<Image> images;

}
