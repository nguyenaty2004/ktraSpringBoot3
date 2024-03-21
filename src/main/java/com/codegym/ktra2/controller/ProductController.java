package com.codegym.ktra2.controller;

import com.codegym.ktra2.model.Product;
import com.codegym.ktra2.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAllProduct(){
        List<Product> products = (List<Product>) productService.findAll();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    @GetMapping("search/{min}/{max}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable double min, @PathVariable double max){
        List<Product> products = productService.findByPriceBetween(min, max);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
    }
    @GetMapping("sortBy")
    public ResponseEntity<List<Product>> sortByAmountProduct(){
        List<Product> products = productService.findAllByOrderByAmountDesc();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("top3")
    public ResponseEntity<List<Product>> findByTop3Price(){
        List<Product> products = productService.findTop3ByOrderByPriceDesc();
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("searchCategory/{category}")
    public ResponseEntity<List<Product>> findByCategory(@RequestParam String category){
        List<Product> products = productService.findByCategoryName(category);
        if (products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id){
        Optional<Product> customerOptional = productService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customerOptional.get(),HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        Optional<Product> customerOptional = productService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(customerOptional.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id){
        Optional<Product> customerOptional = productService.findById(id);
        if (!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }
}