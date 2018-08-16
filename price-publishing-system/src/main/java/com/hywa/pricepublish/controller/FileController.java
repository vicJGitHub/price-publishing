package com.hywa.pricepublish.controller;

import static com.hywa.pricepublish.common.enums.CommonEnum.FILE_CONTENT_NOT_NULL;
import static com.hywa.pricepublish.common.enums.CommonEnum.FILE_NOT_NULL;
import static com.hywa.pricepublish.common.enums.CommonEnum.SUCCESS;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.exception.GlobalException;
import com.hywa.pricepublish.common.utils.ExcelUtils;
import com.hywa.pricepublish.common.utils.FileUtils;
import com.hywa.pricepublish.config.EnvProperties;
import com.hywa.pricepublish.representation.PriceCollectStatisticsReps;
import com.hywa.pricepublish.representation.PriceCrawlerDataReps;
import com.hywa.pricepublish.representation.ResponseBase;
import com.hywa.pricepublish.service.collect.ArtificialCollectionService;
import com.hywa.pricepublish.service.collect.FileService;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private EnvProperties envProperties;

    @Autowired
    private ArtificialCollectionService artificialCollectionService;

    @RequestMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        CommonEnum upload = FileUtils.upload(file, envProperties.getPriceCollectFilePath());
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(upload.getIndex(), upload.getValue());
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity downloadFile(@RequestParam String fileName,
            HttpServletResponse response,
                                       HttpServletRequest request) throws UnsupportedEncodingException {
        String result = FileUtils.downloadFile(envProperties.getPicturePath(), fileName, response,request);
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(SUCCESS.getIndex(), result);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    @RequestMapping(value = "/showImage", method = RequestMethod.GET)
    public ResponseEntity showImage(@RequestParam String fileName, HttpServletResponse response) {
        String result = FileUtils.showImage(envProperties.getPicturePath(), fileName, response);
        ResponseBase responseBase = new ResponseBase();
        responseBase.setRetHead(SUCCESS.getIndex(), result);
        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }

    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(HttpServletRequest request) {
        try {
            String msg = FileUtils.handleFileUpload(request);
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(SUCCESS.getIndex(), msg);
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        } catch (FileUploadException e) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(), e.getMessage());
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "crawlerData/getExcel", method = RequestMethod.POST)
    public ResponseEntity getExcel(HttpServletResponse response,
            @RequestParam(required = false, defaultValue = "价格爬虫数据") String tableName,
            @RequestParam(required = false, defaultValue = "价格爬虫数据展示") String tableTitle,
            @RequestParam(required = false, defaultValue = "爬虫数据") String sheetName,
            @RequestBody PriceCrawlerDataReps priceCrawlerDataReps) {

        try {
            LinkedHashMap<String, String> filedNames = new LinkedHashMap<>();
            filedNames.put("productName", "产品名称");
            filedNames.put("price", "价格");
            filedNames.put("unit", "计量单位");
            filedNames.put("marketName", "市场名称");
            filedNames.put("specification", "规格");
            filedNames.put("region", "区域");
            filedNames.put("updateTime", "日期");
            filedNames.put("dataSources", "数据源");
            filedNames.put("priceType", "价格类型");

            ExcelUtils.exportExcel(priceCrawlerDataReps.getList(),
                    response, tableName, tableTitle, sheetName, filedNames);

            return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
        } catch (Exception e) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(), e.getMessage());
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "collectData/getExcel", method = RequestMethod.POST)
    public ResponseEntity getExcel(HttpServletResponse response,
            @RequestParam(required = false, defaultValue = "采集数据") String tableName,
            @RequestParam(required = false, defaultValue = "采集数据展示") String tableTitle,
            @RequestParam(required = false, defaultValue = "采集数据") String sheetName,
            @RequestBody PriceCollectStatisticsReps statisticsReps) {

        try {
            LinkedHashMap<String, String> filedNames = new LinkedHashMap<>();
            filedNames.put("productName", "产品名称");
            filedNames.put("price", "价格");
            filedNames.put("unit", "计量单位");
            filedNames.put("marketName", "市场名称");
            filedNames.put("collector", "采集人");
            filedNames.put("collectTime", "日期");
            filedNames.put("dataOrigion", "数据源");
            filedNames.put("priceType", "价格类型");
            filedNames.put("bigTypeName", "产品大类");
            filedNames.put("typeName", "产品小类");
            filedNames.put("province", "省");
            filedNames.put("city", "市");
            filedNames.put("county", "区");

            ExcelUtils.exportExcel(statisticsReps.getList(),
                    response, tableName, tableTitle, sheetName, filedNames);

            return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
        } catch (Exception e) {
            ResponseBase responseBase = new ResponseBase();
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(), e.getMessage());
            return new ResponseEntity<>(responseBase, HttpStatus.OK);
        }
    }

    @Transactional
    @PostMapping(value = "artificialCollect/uploadExcel")
    public ResponseEntity uploadArtificialCollectExcel(
            @RequestParam String userId,
            @RequestParam(required = false) String description,
            @RequestParam MultipartFile file) {

        if (file.isEmpty()) {
            throw new GlobalException(FILE_NOT_NULL.getIndex(), FILE_NOT_NULL.getValue());
        }

        List<Map<String, String>> maps = ExcelUtils
                .importExcel(file, envProperties.getPriceCollectFilePath());

        if (maps == null || maps.size() == 0) {
            FileUtils.delete(file.getOriginalFilename(), envProperties.getPriceCollectFilePath());
            throw new GlobalException(FILE_CONTENT_NOT_NULL.getIndex(),
                    FILE_CONTENT_NOT_NULL.getValue());
        }

        try {
            artificialCollectionService.save(maps, userId);

            fileService.save(userId, file.getSize(), file.getOriginalFilename(), description);
        } catch (Exception e) {
            FileUtils.delete(file.getOriginalFilename(), envProperties.getPriceCollectFilePath());
            throw e;
        }

        return new ResponseEntity<>(new ResponseBase().success(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseEntity delete(@RequestParam String fileId) {
//        fileService.delete(fileId);
//        artificialCollectionService.delete(fileId);
//        FileUtils.delete(fileName, filePath);
        return new ResponseEntity(HttpStatus.OK);
    }
}
