package com.inconcept.task.service.model;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class UploadImgThread extends Thread {

    private String directoryPath;
    private List<String> urls;

    public UploadImgThread(String directoryPath, List<String> urls) {
        this.directoryPath = directoryPath;
        this.urls = urls;
    }

    @Override
    public void run() {
        try {

            uploadImg(urls,directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadImg(List<String> urls, String directoryPath) throws IOException {
        System.out.println("The stream of images download has started");
        for (String u : urls) {
            URL url = new URL(u);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(directoryPath + u.substring(u.lastIndexOf("/")));
            byte[] buffer = new byte[2048];

            int length = 0;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        }
    }
}
