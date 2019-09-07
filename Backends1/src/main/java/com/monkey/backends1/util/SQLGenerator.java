/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monkey.backends1.util;

/**
 *
 * @author 李阿俊
 */
public class SQLGenerator {
    public static String generateSQL(String head, String[] fields, String keywords) {
        String header = head;
        for (int i = 0; i < fields.length; i++) {
            if (i != 0) {
                header += "OR ";
            }
            header += fields[i] + " like '%" + keywords + "%' ";
        }
        return header;
    }
}
