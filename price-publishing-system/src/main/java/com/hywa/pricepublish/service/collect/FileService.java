package com.hywa.pricepublish.service.collect;

import com.hywa.pricepublish.representation.FileReps;

public interface FileService {

    void saveEvidenceFile(String userId, String path,String name,long size,String description);

    void save(String userId, long size, String originalFilename, String description);

    FileReps findFileInfoByDescription(int pageNum,int pageSize,String description);
}
