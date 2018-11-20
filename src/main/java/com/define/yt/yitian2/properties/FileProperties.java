package com.define.yt.yitian2.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

//@Component
//@ConfigurationProperties(prefix = "com.define.dragon.upload")
public class FileProperties {
    //配置文件注入时，采用模糊匹配。
    private String fileSize;
    private String requestSize;
    private Long fileSizeNum;
    private Long reqSizeNum;
    private String destPath;

    public FileProperties() {
        fileSize = new String();
        requestSize = new String();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(String requestSize) {
        this.requestSize = requestSize;
    }

    public Long getFileSizeNum() {
        return fileSizeNum;
    }

    public void setFileSizeNum(Long fileSizeNum) {
        this.fileSizeNum = fileSizeNum;
    }

    public Long getReqSizeNum() {
        return reqSizeNum;
    }

    public void setReqSizeNum(Long reqSizeNum) {
        this.reqSizeNum = reqSizeNum;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    /**
     * 用于初始化操作。
     */
    public void init() {
        fileSizeNum = new Long(0);
        reqSizeNum = new Long(0);
        if (fileSize.length() == 0) {
            setFileSizeNum(DataSize.ofMegabytes(2).toBytes());
        } else if (requestSize.length() == 0) {
            setFileSizeNum(parse(fileSize));
            setReqSizeNum(DataSize.ofMegabytes(10).toBytes());
        } else {
            fileSize = fileSize.toLowerCase();
            requestSize = requestSize.toLowerCase();
            setFileSizeNum(parse(fileSize));
            setReqSizeNum(parse(requestSize));
        }


    }

    /**
     * 对配置文件中设置的上传文件大小限定值进行单位转换。
     *
     * @param str 传入的文件大小字符串，如10M。
     * @return 返回字节数。如10M = 10485760字节
     */
    private long parse(String str) {
        long l = 0;
        String endStr = str.substring(str.length() - 1);
        String numStr = str.substring(0, str.length() - 1);
        switch (endStr) {
            case "k":
                l = DataSize.ofKilobytes(Long.valueOf(numStr)).toBytes();
                break;
            case "m":
                l = DataSize.ofMegabytes(Long.valueOf(numStr)).toBytes();
                break;
            case "g":
                l = DataSize.ofGigabytes(Long.valueOf(numStr)).toBytes();
                break;
            default:
                l = DataSize.ofBytes(Long.valueOf(fileSize)).toBytes();
        }
        return l;
    }

}
