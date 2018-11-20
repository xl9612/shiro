package com.define.yt.yitian2.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.define.dragon")
public class DragonProperties {
    private FileProperties upload;

    public FileProperties getUpload() {
        return upload;
    }

    public void setUpload(FileProperties upload) {
        this.upload = upload;
    }
}
