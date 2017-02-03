package com.caleb.fbla.fblamobileappdevelopment2017;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.URI;
import java.text.SimpleDateFormat;

import android.content.Context;
import android.net.Uri;

import java.util.Locale;
import java.util.Calendar;

import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

public class Manager extends AppCompatActivity{

    FTPClient ftpClient;

    String server = "192.168.11.109";
    int port = 21;
    String user = "FTP";
    String pass = "1wdfg6wd6";
    String appData;

    public Manager(String AppData) throws IOException{
        Log.e("FTP", "ranm");
        ftpClient = new FTPClient();//Create new FTPClient
        ftpClient.connect(server, port);//Connect to ftp
        ftpClient.login(user, pass);//Login to ftp
        ftpClient.enterLocalPassiveMode();//States all data will be transferred directly from server to client
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//Files should not be modified when transferring
        appData = AppData;
    }

    public Uri getPicture(String pictureName) throws IOException{

        Log.e("FTP", "ran gp");
        //TODO remove
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);//set date and time format
        String formattedDate = df.format(c.getTime());

        Uri u = null;

        try


        {
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "FBLAMobileApplicationDevelopment/Pictures/" + pictureName;
            File folder = new File(appData, "/MyImage");
            folder.mkdirs();
            File image = new File(folder, pictureName);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(image));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();

            u = Uri.fromFile(image);

            if (success) {
                Log.e("FTP", "File #1 has been downloaded successfully.");
            }
            else{
                Log.e("FTP", "File #1 has been downloaded unsuccessfully.");
            }

        }

        catch(
                IOException ex
                )

        {
            Log.e("FTP", "1Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        catch(
                Exception ex
                )

        {
            Log.e("FTP", "1Error: " + ex.getMessage());
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

        return u;
    }

    public Uri getDB() throws IOException{

        Log.e("FTP", "ran gp");
        //TODO remove
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);//set date and time format
        String formattedDate = df.format(c.getTime());

        Uri u = null;

        try


        {
            // APPROACH #1: using retrieveFile(String, OutputStream)
            String remoteFile1 = "ProductDB.sqlite3";
            File folder = new File(appData);
            folder.mkdirs();
            File db = new File(folder, "ProductDB.sqlite3");




            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(db));
            boolean success = ftpClient.retrieveFile(remoteFile1, outputStream1);
            outputStream1.close();

            u = Uri.fromFile(db);

            if (success) {
                Log.e("FTP", "File #1 has been downloaded successfully.");
            }
            else{
                Log.e("FTP", "File #1 has been downloaded unsuccessfully.");
            }

        }

        catch(
                IOException ex
                )

        {
            Log.e("FTP", "1Error: " + ex.getMessage());
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

        return u;
    }

    public boolean save(String srcFilePath, String desFileName) throws Exception{
        boolean status = false;

            FileInputStream srcFileStream = new FileInputStream(srcFilePath);
            
            status = ftpClient.storeFile(desFileName, srcFileStream);

            srcFileStream.close();

            return status;

        //return status;
    }

}
