package com.example.ecom_project.controller;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable int id) {

        Product product = service.getProductByID(id);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProductByID(@PathVariable int id, @RequestBody Product product) {

        Product product1 = service.updateProductByID(id, product);

        if (product1 == null)
            return new ResponseEntity<>("There is no such product!", HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<>("Updated Product!", HttpStatus.OK);
        }

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProductByID(@PathVariable int id) {

        Product product = service.getProductByID(id);

        if (product!= null) {
            service.deleteProductByID(id);
            return new ResponseEntity<>("Deleted product!", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Invalid Action", HttpStatus.NOT_FOUND);


    }

}
