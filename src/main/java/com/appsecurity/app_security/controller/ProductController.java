package com.appsecurity.app_security.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appsecurity.app_security.dto.SaveProduct;
import com.appsecurity.app_security.persistence.entity.Product;
import com.appsecurity.app_security.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(page=1,size=5) Pageable pageable){

        Page<Product> productsPage = productService.findAll(pageable);

        if(productsPage.hasContent()){
            return ResponseEntity.ok(productsPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId){

        Optional<Product> product = productService.findOneById(productId);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProduct saveProduct){
        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId ,
                                                 @RequestBody @Valid SaveProduct saveProduct){
        Product product = productService.updateOneById(productId, saveProduct);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId){
        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }

}
