package indi.rui.common.base.util;

//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public class FtpFileUtil {

    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "172.16.5.16";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "isli";
    //密码
    private static final String FTP_PASSWORD = "isli20150922";
    //图片路径
    private static final String FTP_BASEPATH = "/home/isli/uasc/upload";

    public  static boolean uploadFile(String originFileName,InputStream input){
        boolean success = false;
//        FTPClient ftp = new FTPClient();
//        ftp.setControlEncoding("GBK");
//        try {
//            int reply;
//            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
//            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
//            reply = ftp.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                ftp.disconnect();
//                return success;
//            }
//            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftp.makeDirectory(FTP_BASEPATH );
//            ftp.changeWorkingDirectory(FTP_BASEPATH);
//            ftp.storeFile(originFileName,input);
//            input.close();
//            ftp.logout();
//            success = true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (ftp.isConnected()) {
//                try {
//                    ftp.disconnect();
//                } catch (IOException ioe) {
//                }
//            }
//        }
        return success;
    }
}
