package com.raderleao.admin.catalogo.infrastructure;

import com.mysql.jdbc.Driver;

public class TestConexaoBanco {
    public static void main(String[] args) {
        try {
            Driver driver = new Driver();
            System.out.println("MySQL driver loaded successfully.");
        } catch (Exception e) {
            System.err.println("Failed to load MySQL driver: " + e.getMessage());
        }
    }
}

