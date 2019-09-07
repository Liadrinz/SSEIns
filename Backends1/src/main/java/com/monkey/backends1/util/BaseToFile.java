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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 文件与base64的互相转换操作
 */
public class BaseToFile {

    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    public static void decoderBase64File(String base64Code, String fname, String path, String suffix) throws Exception {
        File file = new File(path);
        if (file.exists() == false) {
            file.mkdirs();
        }
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(fname + "." + suffix);
        out.write(buffer);
        out.close();
    }
}
