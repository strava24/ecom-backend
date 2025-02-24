package com.example.ecom_project.service;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }


    /*
   findById returns optional product not the product, cause the DB can exist without having a product with the Id we're searching for
   so we have to define .orElse and return something if the product is not found
     */
    public Product getProductByID(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product updateProductByID(int id, Product product) {
        return repo.save(product);
    }


    public void deleteProductByID(int id) {
        repo.deleteById(id);
    }

    public void addProduct(Product product) {
        repo.save(product);
    }
}
