package com.yuliana.cafe.util;

import com.yuliana.cafe.exception.ServiceException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomFileReader {
    private static final String UPLOAD_DIRECTORY = "C:\\logo";
    private static final String UPLOAD_PROJECT_DIRECTORY = "C:\\Users\\Yulia\\IdeaProjects\\Cafe\\src\\main\\webapp\\images";
    private static final String JPG_FORMAT = "jpg";
    private static CustomFileReader INSTANCE = new CustomFileReader();

    private CustomFileReader() {
    }

    public static CustomFileReader getInstance() {
        return INSTANCE;
    }

    public byte[] readAndWriteImage(String fileName) throws ServiceException {
        byte[] result;
        String fileUri = UPLOAD_DIRECTORY + File.separator + fileName;
        Path filePath = Path.of(fileUri);
        if (Files.exists(filePath)) {
            try {
                result = Files.readAllBytes(filePath);
                String path = UPLOAD_PROJECT_DIRECTORY + File.separator + fileName;
                if (!Files.exists(Path.of(path))) {
                    File file = new File(fileUri);
                    BufferedImage bufferedImage = ImageIO.read(file);
                    ImageIO.write(bufferedImage, JPG_FORMAT, new File(path));
                }
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        } else {
            throw new ServiceException("File doesnt exist.");
        }
        return result;
    }
}