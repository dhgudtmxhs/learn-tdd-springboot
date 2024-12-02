package com.example.tdd.service.product;

import com.example.tdd.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private static final Map<String, Double> CATEGORY_DISCOUNTS = new HashMap<>();

    static {
        CATEGORY_DISCOUNTS.put("전자기기", 0.1); // 10%
        CATEGORY_DISCOUNTS.put("생활용품", 0.05); // 5%
        CATEGORY_DISCOUNTS.put("패션", 0.15); // 15%
    }

    // 할인 없는 기본 가격 계산
    public double calculateDefaultPrice(Product product) {
        return product.getPrice();
    }

    // 카테고리별 할인율 적용(없을 시 0) 후 가격 계산
    public double calculateCategoryDiscountedPrice(Product product) {
        double categoryDiscountRate = CATEGORY_DISCOUNTS.getOrDefault(product.getCategory(), 0.0);
        double discountAmount = product.getPrice() * categoryDiscountRate;
        return product.getPrice() - discountAmount; // 카테고리 할인 적용 후 가격
    }

    // 카테고리 할인율 + 사용자 할인율 적용 최종 가격 계산
    public double calculateUserDiscountedPrice(Product product, double userDiscountRate) {
        double categoryDiscountRate = CATEGORY_DISCOUNTS.getOrDefault(product.getCategory(), 0.0);
        double totalDiscountRate = Math.min(0.9, categoryDiscountRate + userDiscountRate);
        double discountAmount = product.getPrice() * totalDiscountRate;
        return product.getPrice() - discountAmount; // 최종 할인 적용 후 가격
    }

    // 카테고리 할인율 + 사용자 할인율의 총 할인율 계산
    public double calculateTotalDiscountRate(Product product, double userDiscountRate) {
        double categoryDiscountRate = CATEGORY_DISCOUNTS.getOrDefault(product.getCategory(), 0.0);
        return Math.min(0.9, categoryDiscountRate + userDiscountRate); // 총 할인율 계산
    }

    // 재고 확인 및 감소
    public Product reduceStock(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        return Product.builder()
                .name(product.getName())
                .category(product.getCategory())
                .quantity(product.getQuantity() - quantity) // 재고 감소
                .price(product.getPrice())
                .discountRate(product.getDiscountRate())
                .build();
    }
}
