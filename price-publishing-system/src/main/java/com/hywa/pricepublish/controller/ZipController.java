package com.hywa.pricepublish.controller;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.FileUtils;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.common.utils.ZipUtils;
import com.hywa.pricepublish.config.EnvProperties;
import com.hywa.pricepublish.representation.PrInformRep;
import com.hywa.pricepublish.representation.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/zip")
public class ZipController {

    @Autowired
    private EnvProperties envProperties;
    /**
     * 根据事件id,打包压缩下载文件
     * 证据文件不存在时,抛出FileNotFound异常,在异常管理捕获后友好返回
     */
    @RequestMapping(value = "/downLoadZipFile",method = RequestMethod.GET)
    public void downLoadZipFile(PrInformRep prInformRep, HttpServletRequest request, HttpServletResponse response) throws IOException{
        if (StringUtils.isEmpty(prInformRep.getId())){
            throw new GlobalException(CommonEnum.PARAMETER_NOT_NULL.getIndex(),CommonEnum.PARAMETER_NOT_NULL.getValue());
        }
        String zipName = prInformRep.getPersonBeAccused()+"的价格举报证据"+".zip";
        new File(envProperties.getZipEvidence()).mkdirs();
        ZipUtils.doCompress(envProperties.getEvidence()+prInformRep.getId()+"\\",envProperties.getZipEvidence()+zipName);
        FileUtils.downloadFile(envProperties.getZipEvidence(), zipName, response, request);
    }
}
