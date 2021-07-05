/*
 * Copyright (c) 2014 laidian. All Rights Reserved.
 * @author jiangchao
 * @date  2021-03-25 15:55
 */
package com.eeeffff.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 获取文件类型.
 *
 * @author jiangchao
 * @date 2021-03-25 15:55
 */
public class FileTypeUtil {
    
    private static final Map<String, String> FILE_TYPE_MAP;

    static {
        FILE_TYPE_MAP = new ConcurrentSkipListMap<>((s1, s2) -> {
            int len1 = s1.length();
            int len2 = s2.length();
            if (len1 == len2) {
                return s1.compareTo(s2);
            } else {
                return len2 - len1;
            }
        });

        FILE_TYPE_MAP.put("424d228c010000000000","bmp"); // 16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000","bmp"); // 24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000","bmp"); // 256色位图(bmp)
        FILE_TYPE_MAP.put("ffd8ff", "jpg"); // JPEG (jpg)
        FILE_TYPE_MAP.put("89504e47", "png"); // PNG (png)
        FILE_TYPE_MAP.put("4749463837", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("4749463839", "gif"); // GIF (gif)
        FILE_TYPE_MAP.put("255044462d312e", "pdf"); // Adobe Acrobat (pdf)
        // MS Excel 注意：word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10", "xls");
        FILE_TYPE_MAP.put("504B0304", "zip");
        FILE_TYPE_MAP.put("526172211a0700cf9073","rar"); 
        FILE_TYPE_MAP.put("504B0304", "office07");
        FILE_TYPE_MAP.put("D0CF11E0", "office03");
        FILE_TYPE_MAP.put("504b0304140006000800docx", "docx");//docx文件
        FILE_TYPE_MAP.put("docd0cf11e", "doc"); //doc,excel,xls，ppt相同文件头
    }


    /**
     * Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]
     *
     * @param file
     * @return fileType
     */
    public final static String getFileByFile(File file) {
        String filetype = null;
        byte[] b = new byte[50];
        try {
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filetype;
    }

    /**
     * Discription:[getFileTypeByStream]
     *
     * @param b
     * @return fileType
     */
    public final static String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        Iterator<Entry<String, String>> entryiterator = FILE_TYPE_MAP.entrySet().iterator();
        while (entryiterator.hasNext()) {
            Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getKey();
            if (StringUtils.startsWithIgnoreCase(filetypeHex,fileTypeHexValue)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    
    /**
     * Discription:[getFileHexString]
     *
     * @param b
     * @return fileTypeHex
     */
    public final static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

  

    public static void main(String args[]) { 
        
       String s1 = getFileByFile(new File("D:\\测试图片\\1M.bmp"));
       String s2 = getFileByFile(new File("D:\\测试图片\\2M.png"));
       String s3 = getFileByFile(new File("D:\\测试图片\\background.jpeg"));
       System.out.println(s1);
       System.out.println(s2);
       System.out.println(s3);
    }
    

}