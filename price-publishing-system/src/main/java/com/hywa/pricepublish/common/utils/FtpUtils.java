package com.hywa.pricepublish.common.utils;

import com.hywa.pricepublish.config.EnvProperties;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
    private FTPClient ftpClient;
    private String strIp;  //ftp ip
    private int intPort;   //ftp port
    private String user;   //ftp username
    private String password; //ftp password
    private EnvProperties environment;

    public FtpUtils(EnvProperties envProperties) {
        this.environment = envProperties;
        this.strIp = envProperties.getFtpServerIp();
        String strPort = envProperties.getFtpServerPort();
        if(!strPort.trim().equals("")){
            this.intPort = Integer.valueOf(strPort);
        }

        this.user = envProperties.getFtpLoginUser();
        this.password = envProperties.getFtpLoginPassword();
        this.ftpClient = new FTPClient();
    }

    public boolean ftpLogin(){
        boolean isOk = false;
        // 这个编码的设定因情况而定，这里只告诉大家，能设置编码
        ftpClient.setControlEncoding("GBK");
        try {
            // 有没有端口都要登上去的意思
            if(intPort>0){
                ftpClient.connect(this.strIp, this.intPort);
            }else{
                ftpClient.connect(this.strIp);
            }
            isOk = this.ftpClient.login(this.user, this.password);
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            this.ftpClient.setBufferSize(1024 * 2);
            this.ftpClient.setDataTimeout(30 * 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isOk;
    }

    public void ftpLogout(){
        if (null != this.ftpClient && this.ftpClient.isConnected()) {
            try {
                this.ftpClient.logout();// 退出FTP服务器
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    this.ftpClient.disconnect();// 关闭FTP服务器的连接
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downloadFile(String remoteFileName ,ZipOutputStream zipOutputStream){
        try {
            zipOutputStream.putNextEntry(new ZipEntry(remoteFileName));
            this.ftpClient.enterLocalPassiveMode();
            boolean isRetrieveOk = this.ftpClient.retrieveFile(remoteFileName, zipOutputStream);
            if(!isRetrieveOk){
                return;
            }
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void downloadFile(HttpServletResponse res) throws UnsupportedEncodingException {
//        boolean login =  ftpLogin(); // 登录
//        res.setContentType("application/pdf");
//        // 这里重新编码以免中文名称的压缩包出不来
//        String downloadFileName = new String("你自己命名的压缩包.evidence.zip").getBytes("GBK"), "ISO-8859-1");
//        res.setHeader("content-Disposition", "attachment;filename="+downloadFileName);
//        ZipOutputStream zipOutputStream = null;
//        try {
//            zipOutputStream = new ZipOutputStream(res.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 这里只下载了一个文件，你也可以通过循环或其他形式，多次调用，只要在压缩流关闭之前都会压缩到一个包内，而且保留文件路径
//        downloadFile("你的文件",zipOutputStream);
//        try {
//            zipOutputStream.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        ftpLogout();// 退出
//    }

}
