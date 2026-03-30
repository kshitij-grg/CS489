package edu.miu.cs.cs489appsd.lab1.productmgmtapp;

import edu.miu.cs.cs489appsd.lab1.productmgmtapp.model.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {
    public static void main(String[] args) {
        Product[] products = {
                new Product(new BigInteger("31288741190182539912"), "Banana", LocalDate.parse("2026-01-24"), 124, new BigDecimal("0.55")),
                new Product(new BigInteger("29274582650152771644"), "Apple", LocalDate.parse("2025-12-09"), 18, new BigDecimal("1.09")),
                new Product(new BigInteger("91899274600128155167"), "Carrot", LocalDate.parse("2026-03-31"), 89, new BigDecimal("2.99")),
                new Product(new BigInteger("31288741190182539913"), "Banana", LocalDate.parse("2026-02-13"), 240, new BigDecimal("0.65"))
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) {
        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(
                sortedProducts,
                Comparator.comparing(Product::getName).thenComparing(Product::getUnitPrice, Comparator.reverseOrder())
        );

        printAsJson(sortedProducts);
        printAsXml(sortedProducts);
        printAsCsv(sortedProducts);
    }

    private static void printAsJson(Product[] products) {
        System.out.println("JSON:");
        System.out.println("[");
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            String json = String.format(
                    "  {\"productId\":\"%s\",\"name\":\"%s\",\"dateSupplied\":\"%s\",\"quantityInStock\":%d,\"unitPrice\":%s}",
                    p.getProductId(),
                    p.getName(),
                    p.getDateSupplied(),
                    p.getQuantityInStock(),
                    p.getUnitPrice()
            );
            if (i < products.length - 1) {
                json += ",";
            }
            System.out.println(json);
        }
        System.out.println("]");
        System.out.println();
    }

    private static void printAsXml(Product[] products) {
        System.out.println("XML:");
        System.out.println("<products>");
        for (Product p : products) {
            System.out.println("  <product>");
            System.out.printf("    <productId>%s</productId>%n", p.getProductId());
            System.out.printf("    <name>%s</name>%n", p.getName());
            System.out.printf("    <dateSupplied>%s</dateSupplied>%n", p.getDateSupplied());
            System.out.printf("    <quantityInStock>%d</quantityInStock>%n", p.getQuantityInStock());
            System.out.printf("    <unitPrice>%s</unitPrice>%n", p.getUnitPrice());
            System.out.println("  </product>");
        }
        System.out.println("</products>");
        System.out.println();
    }

    private static void printAsCsv(Product[] products) {
        System.out.println("CSV:");
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
        for (Product p : products) {
            System.out.printf(
                    "%s,%s,%s,%d,%s%n",
                    p.getProductId(),
                    p.getName(),
                    p.getDateSupplied(),
                    p.getQuantityInStock(),
                    p.getUnitPrice()
            );
        }
    }
}
