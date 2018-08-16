package com.hywa.pricepublish.common.utils;

import static com.hywa.pricepublish.common.enums.CommonEnum.FAILURE;
import static com.hywa.pricepublish.common.enums.CommonEnum.SUCCESS;

import com.hywa.pricepublish.common.enums.CommonEnum;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
public class FileUtils {

    public static CommonEnum upload(MultipartFile file, String filePath) {

        if (file.isEmpty()) {
            return FAILURE;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return SUCCESS;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FAILURE;
    }

    //文件下载相关代码
    public static String downloadFile(String filePath, String fileName,
                                      HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
        if (fileName != null) {
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                return "文件不存在";
            } else {
                response.setContentType("application/force-download");// 设置强制下载不打开
                //不能直接在header中设置中文，普通浏览器要使用URLEncoder.encode方法encode一下，其中火狐浏览器要区别处理，要转成ISO-8859-1编码
                if(request.getHeader("user-agent").toLowerCase().indexOf("firefox")>-1) {
                    response.addHeader("Content-Disposition",
                            "attachment;filename=" + new String(fileName.getBytes("utf-8"),"ISO-8859-1"));

                }else {
                    response.addHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode(fileName,"utf-8"));
                }
/*                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
*/                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    log.info(fileName + ": 下载成功");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    //图片查看
    public static String showImage(String filePath, String fileName, HttpServletResponse response) {
        File file = new File(filePath, fileName);
        FileInputStream fis = null;
        if (!file.exists()) {
            return "文件不存在";
        }
        try {
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];

            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //多文件下载
    public static String handleFileUpload(HttpServletRequest request) throws FileUploadException {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();

                } catch (Exception e) {
                    stream = null;
                    throw new FileUploadException("You failed to upload " + i + " => "
                            + e.getMessage());
                }
            } else {
                throw new FileUploadException("You failed to upload " + i
                        + " because the file was empty.");
            }
        }
        return "upload successful";
    }

    public static void delete(String filename, String filePath) {
        File file = new File(filePath + filename);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
