package com.af.utils;

import java.io.File;

/**
 * @Author AF
 * @Description 图片路径的封装
 * @Date 2019/12/8 13:53
 */
public class PathUtil {
    private static String seperator = File.separator;

    /**
     * 项目
     * @return
     */
    public static String getImageBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "F:/project/o2o/image";
        } else {
            basePath = "/project/o2o/image";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(Integer shopId){
        String imagePath = "/upload/item/shop/"+shopId+"/";
        return imagePath.replace("/", seperator);
    }

    public static void main(String[] args) {
        System.out.println(getImageBasePath());
    }
}
