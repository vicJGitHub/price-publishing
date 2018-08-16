package com.hywa.pricepublish.service.collect.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.config.EnvProperties;
import com.hywa.pricepublish.dao.entity.File;
import com.hywa.pricepublish.dao.entity.FileExample;
import com.hywa.pricepublish.dao.mapper.FileMapper;
import com.hywa.pricepublish.representation.FileReps;
import com.hywa.pricepublish.service.collect.FileService;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    EnvProperties envProperties;

    @Autowired
    FileMapper fileMapper;

    @Override
    public void saveEvidenceFile(String userId, String path, String name, long size, String description) {
        File file = new File();
        file.setId(UUIDUtils.randomUUID());
        file.setCreateTime(new Date());
        file.setCreateUser(userId);
        file.setName(name);
        file.setSize(size);
        file.setPath(path);
        file.setDescription(description);
        file.setIsDel(ConstantPool.NOT_DEL);
        fileMapper.insert(file);
    }

    @Override
    public void save(String userId, long size, String name, String description) {
        File file = new File();
        file.setId(UUIDUtils.randomUUID());
        file.setCreateTime(new Date());
        file.setCreateUser(userId);
        if (description != null) {
            file.setDescription(description);
        }
        file.setName(name);
        file.setSize(size);
        file.setPath(envProperties.getPriceCollectFilePath());
        file.setIsDel(ConstantPool.NOT_DEL);
        fileMapper.insert(file);
    }

    @Override
    public FileReps findFileInfoByDescription(int pageNum,int pageSize,String description) {
        PageHelper.startPage(pageNum,pageSize,true);
        FileExample fileExample = new FileExample();
        fileExample.createCriteria().andDescriptionEqualTo(description);
        List<File> files = fileMapper.selectByExample(fileExample);
        return new FileReps(PageInfo.of(files).getTotal(),files);
    }

}
