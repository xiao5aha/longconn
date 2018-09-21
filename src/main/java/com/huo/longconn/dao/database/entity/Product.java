package com.huo.longconn.dao.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int productCode;
    private String productName;
    private String productSalt;
    private String authUrl;
}
