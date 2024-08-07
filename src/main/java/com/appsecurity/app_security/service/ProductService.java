package com.appsecurity.app_security.service;


import com.appsecurity.app_security.dto.SaveProduct;
import com.appsecurity.app_security.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateOneById(Long productId, SaveProduct saveProduct);

    Product disableOneById(Long productId);
}
