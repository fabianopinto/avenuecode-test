package com.avenuecode.fabianopinto.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.avenuecode.fabianopinto.json.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Data
public class Image implements Serializable {

	private static final long serialVersionUID = -3180643874628454619L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Summary.class)
	private Integer id;

	@JsonView(Views.Summary.class)
	private String type;

	@ManyToOne(optional = false)
	@JsonIgnore
	private Product product;

}
