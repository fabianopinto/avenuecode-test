package com.avenuecode.fabianopinto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenuecode.fabianopinto.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {}
