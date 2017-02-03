import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class Manager {

    FTPClient ftpClient;

    String server = "192.168.11.109";
    int port = 21;
    String user = "FTP";
    String pass = "1wdfg6wd6";

    public Manager(){
        ftpClient = new FTPClient();//Create new FTPClient
        ftpClient.connect(server, port);//Connect to ftp
        ftpClient.login(user, pass);//Login to ftp
        ftpClient.enterLocalPassiveMode();//Says all data will be transfered directly from server to client
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//Files should not be modified when tranfering
    }

    public void getPicture(String pictureName){



        try

        {
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "/FBLAMobileApplicationDevelopment/Pictures/" + pictureName;
            File downloadFile1 = new File("D:/Downloads/video.mp4");
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();

            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }

        }

        catch(
        IOException ex
        )

        {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        finally

        {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
