package com.service;

import com.model.Product;
import com.model.ProductType;

import java.util.List;

public interface ProductService {
    Product read(int id);

    Product readByProductType(int ProductTypeId);

    List<Product> readAll();

    void create (Product product);

    ProductType update(int id, Product product);

    void delete (int id);
}
