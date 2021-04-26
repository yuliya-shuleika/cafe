package com.yuliana.cafe.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * File uploader provides operations with uploading files from server.
 *
 * @author Yulia Shuleiko
 */
public class FileUploader {

    private static final Logger logger = LogManager.getLogger();
    private static final FileUploader INSTANCE = new FileUploader();
    private static final String UPLOAD_PATH = "C:\\Users\\Yulia\\IdeaProjects\\Cafe\\src\\main\\webapp\\images\\";
    private static final String FILE_SEPARATOR = "\\";
    private static final String IMAGES_FOLDER = "/images/";
    private static final String JPG_FORMAT = ".jpg";

    private FileUploader(){}

    public static FileUploader getInstance(){
        return INSTANCE;
    }

    /**
     * Upload picture from server.
     *
     * @param currentFolder folder's name that picture will be saved
     * @param item the {@code FileItem} object
     * @return relative filepath of the uploaded picture
     */
    public String uploadPicture(String currentFolder, FileItem item) {
        String pictureName = "";
        UUID uuid = UUID.randomUUID();
        String filename = uuid.toString() + JPG_FORMAT;
        Path path = Paths.get(filename);
        String filePath = UPLOAD_PATH + currentFolder + FILE_SEPARATOR + path.getFileName();
        File uploadFile = new File(filePath);
        try {
            if (!uploadFile.exists()) {
                item.write(uploadFile);
            }
            pictureName = IMAGES_FOLDER + currentFolder + '/' + path.getFileName();
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error saving photo.");
        }
        return pictureName;
    }
}
