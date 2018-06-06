package com.gmail.grind3x.util;

import com.gmail.grind3x.model.Image;
import org.apache.commons.fileupload.FileItem;

import java.io.*;
import java.util.List;

public class Util {

    public static String parseItem(String value, List<FileItem> items) {
        for (FileItem item : items) {
            if (item.isFormField()) {
                if (item.getFieldName().equals(value)) {
                    return item.getString();
                }
            }
        }
        return null;
    }

    public static Image getImage(List<FileItem> items, String abs, String relet) {
        String filePath = "";
        String name = "";
        Image image = null;
        for (FileItem item : items) {
            if (!item.isFormField()) {
                try {
                    if (item.getSize() == 0) {
                        return null;
                    }
                    name = item.hashCode() + item.getName();
                    item.write(new File(abs + item.hashCode() + item.getName()));
                    image = new Image(name, relet);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }
}
