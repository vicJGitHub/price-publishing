package com.hywa.pricepublish.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EnvProperties {

    @Value("${GD.key}")
    private String gdKey;

    @Value("${File.collect.picture.path}")
    private String picturePath;

    @Value("${File.collect.path}")
    private String priceCollectFilePath;

    @Value("${File.collect.path}")
    private String ftpServerIp;

    @Value("${File.collect.path}")
    private String ftpServerPort;

    @Value("${File.collect.path}")
    private String ftpLoginUser;

    @Value("${zip.evidence.file.path}")
    private String ftpLoginPassword;

    @Value("${evidence.file.path}")
    private String evidence;

    @Value("${zip.evidence.file.path}")
    private String zipEvidence;

}
