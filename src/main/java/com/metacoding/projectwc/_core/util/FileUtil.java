package com.metacoding.projectwc._core.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileUtil {
    public static String fileSave(String base64) {
        // TODO 확인해봐야 함
        String mimeType = base64.substring(5, base64.indexOf(";base64,"));
        String result = mimeType.split("/")[1];

        String imgName = UUID.randomUUID() + "." + result;
        String profileUrl = "images/" + imgName;
        String dbUrl = "/img/" + imgName;

        String base64Data = base64.split(",")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data);

        try {
            Path path = Paths.get(profileUrl);
            Files.write(path, decodedBytes);
            return dbUrl;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String fileSave(MultipartFile file) {
        String imgName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String profileUrl = "images/" + imgName;
        String dbUrl = "/img/" + imgName;

        try {
            Path path = Paths.get(profileUrl);
            Files.write(path, file.getBytes());
            return dbUrl;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
