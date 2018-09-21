package com.huo.longconn.dao.database;

import com.huo.longconn.dao.database.entity.Product;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author 小混蛋
 * @Desc 负责在平台启动时把使用平台的产品和平台IP黑名单加载到本地内存
 * 使用本地内存的原因是产品和IP黑名单都不会超过几十个
 */
@Log4j2
public class IniBean {
    private static volatile Map<Integer, Product> productCode2Product = new HashMap<>();
    private static volatile Set<String> blacklistSet = new HashSet<>();
    private static final String GET_PRODUCT_INFO = "select * from PRODUCT";
    private static final String GET_BLACK_LIST = "select * from BLACKLIST";

    public static void refresh() {
        initProductCache();
        initBlackListCache();
    }

    public static void initProductCache() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            ResultSet rs = connection.prepareStatement(GET_PRODUCT_INFO).executeQuery();
            Map<Integer, Product> tmpProductCode2Product = new HashMap<>();
            while (rs.next()) {
                int productCode = rs.getInt("product_code");
                String name = rs.getString("product_name");
                String saltStr = rs.getString("product_salt");
                String authUrl = rs.getString("auth_url");
                tmpProductCode2Product.put(productCode, new Product(productCode, name, saltStr, authUrl));
            }
            productCode2Product = tmpProductCode2Product;
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void initBlackListCache() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            ResultSet rs = connection.prepareStatement(GET_BLACK_LIST).executeQuery();
            Set<String> tmpBlackListSet = new HashSet<>();
            while (rs.next()) {
                String blackipStr = rs.getString(1);
                tmpBlackListSet.add(blackipStr);
            }
            blacklistSet = tmpBlackListSet;
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String getProductName(int productCode) {
        Product product = getProduct(productCode);
        if (product == null) {
            return null;
        }
        return product.getProductName();
    }

    public static String getSaltStr(int productCode) {
        Product product = getProduct(productCode);
        if (product == null) {
            return null;
        }
        return product.getProductSalt();
    }

    public static String getAuthUrl(int productCode) {
        Product product = getProduct(productCode);
        if (product == null) {
            return null;
        }
        return product.getAuthUrl();
    }

    public static Product getProduct(int productCode) {
        return productCode2Product.get(productCode);
    }

    public static Set<String> getBlackList() {
        return blacklistSet;
    }

    public static boolean isInBlackList(String ip) {
        return blacklistSet.contains(ip);
    }
}
