package com.codegym.ktra2.service.impl;

import com.codegym.ktra2.model.Product;
import com.codegym.ktra2.repository.IProductRepository;
import com.codegym.ktra2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }


    @Override
    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> findAllByOrderByAmountDesc() {
        return productRepository.findAllByOrderByAmountDesc();
    }

    @Override
    public List<Product> findTop3ByOrderByPriceDesc() {
        return productRepository.findTop3ByOrderByPriceDesc();
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }
}
