package com.define.yt.yitian2.common.util;

import com.define.yt.yitian2.properties.FileProperties;
import com.definesys.mpaas.common.exception.MpaasBusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class FileUtils {
    /**
     * 文件上传
     *
     * @param fileList       文件列表
     * @param fileProperties 文件上传规则配置类
     */
    public static void fileUpload(List<MultipartFile> fileList, FileProperties fileProperties) {
        checkFileSize(fileList, fileProperties);
        File filePath = new File(fileProperties.getDestPath());
        if (!filePath.exists())
            filePath.mkdirs();
        for (MultipartFile file : fileList
        ) {
            writeFile(file, filePath);
        }

    }

    /**
     * 检查文件大小是否符合规定
     *
     * @param fileList       文件列表
     * @param fileProperties 文件上传规则配置类
     */
    public static void checkFileSize(List<MultipartFile> fileList, FileProperties fileProperties) {
        long sumSize = 0;
        for (MultipartFile file : fileList
        ) {
            long size = file.getSize();
            if (size == 0) {
                throw new MpaasBusinessException(file.getOriginalFilename() + "是空文件，无法上传！");
            }
            if (size > fileProperties.getFileSizeNum())
                throw new MpaasBusinessException("文件" + file.getOriginalFilename() + "超出" + fileProperties.getFileSize());
            sumSize += size;
            if (sumSize > fileProperties.getReqSizeNum())
                throw new MpaasBusinessException("上传文件总和不能超过" + fileProperties.getRequestSize());
        }
    }

    /**
     * 将文件存储到指定的路径
     *
     * @param file 待上传的文件
     * @param path 文件存储路径
     */
    public static void writeFile(MultipartFile file, File path) {
        String fileName = new Date().getTime() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(path, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MpaasBusinessException("文件" + file.getOriginalFilename() + "上传失败！");
        }
    }
}
