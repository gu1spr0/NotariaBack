package com.yorisapp.notaria.util;

import com.yorisapp.notaria.exception.Message;
import com.yorisapp.notaria.exception.MessageDescription;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class Upload {
    public String convertBase64ToFile(String path, String fileBase64, String fileName) throws IOException {
        String[] strings = fileBase64.split(",");
        if(strings.length<2){
            throw Message.GetBadRequest(MessageDescription.Base64NotValid);
        }

        String extension;
        switch (strings[0]) {//check image's extension
            case "data:text/csv;base64":
                extension = "csv";
                break;
            case "data:application/vnd.ms-excel;base64":
                extension = "csv";
                break;
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            case "data:application/pdf;base64":
                extension = "pdf";
                break;
            case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
                extension = "docx";
                break;
            case "data:application/doc;base64":
                extension = "docx";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        byte[] data = Base64.getDecoder().decode(strings[1]);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String stringDate = dateFormat.format(date);
        String fileNameComplete = fileName+stringDate + "." + extension;
        byte[] contents = Base64.getDecoder().decode(strings[1]);
        InputStream stream = new ByteArrayInputStream(contents);

        /*ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(contents.length);

        if (extension.equals("pdf"))
            meta.setContentType("application/pdf");
        else
            meta.setContentType("image/"+extension);*/

        File folder = new File(path);
        folder.mkdirs();

        Path folderDestination = Paths.get(path, fileNameComplete);
        Files.write(folderDestination, contents);


        return fileNameComplete;

    }
}
