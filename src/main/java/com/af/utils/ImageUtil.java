package com.af.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author AF
 * @Description
 * @Date 2019/12/8 14:17
 */
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    //创建缩略图
    public static String generateThumbnail(CommonsMultipartFile thumbnail,String rootFilePath, String shopThumbnail) {
        //随机生成文件名
        String realFileName = FileUtil.getRandomFileName();
        //获取文件后缀名
        String extension = getFileExtension(thumbnail);
        //创建文件地址
        makeDirPath(rootFilePath+shopThumbnail);
        String relativeAddr = rootFilePath+shopThumbnail+File.separator+ realFileName + extension;
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.25f).toFile(relativeAddr);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return File.separator+shopThumbnail+File.separator+ realFileName + extension;
    }


    //创建详情图
    public static String generateThumbnailDesc(CommonsMultipartFile thumbnail,String rootFilePath, String shopThumbnail) {
        //随机生成文件名
        String realFileName = FileUtil.getRandomFileName();
        //获取文件后缀名
        String extension = getFileExtension(thumbnail);
        //创建文件地址
        makeDirPath(rootFilePath+File.separator+shopThumbnail);
        String relativeAddr = rootFilePath+File.separator+shopThumbnail+File.separator+ realFileName + extension;
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(600, 600).outputQuality(0.25f).toFile(relativeAddr);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return shopThumbnail+File.separator+ realFileName + extension;
    }


    public static String generateNormalImg(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = FileUtil.getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(337, 640).outputQuality(0.5f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static List<String> generateNormalImgs(List<CommonsMultipartFile> imgs, String targetAddr) {
        int count = 0;
        List<String> relativeAddrList = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            makeDirPath(targetAddr);
            for (CommonsMultipartFile img : imgs) {
                String realFileName = FileUtil.getRandomFileName();
                String extension = getFileExtension(img);
                String relativeAddr = targetAddr + realFileName + count + extension;
                File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
                count++;
                try {
                    Thumbnails.of(img.getInputStream()).size(600, 300).outputQuality(0.5f).toFile(dest);
                } catch (IOException e) {
                    throw new RuntimeException("创建图片失败：" + e.toString());
                }
                relativeAddrList.add(relativeAddr);
            }
        }
        return relativeAddrList;
    }

    private static void makeDirPath(String targetAddr) {
        File dirPath = new File(targetAddr);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

}
