package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc.worldcup.Worldcup;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class WorldcupItemRequest {
    @Data
    public static class UpdateNameDTO {
        private String itemname;
    }

    @Data
    public static class UpdateImgDTO {
        private MultipartFile file;
    }

    @Data
    public static class SaveDTO {
        private MultipartFile file;

        public WorldcupItem toEntity(String imgUrl, Worldcup worldcup) {
            String fileName = file.getOriginalFilename();
            return WorldcupItem.builder().itemname(fileName.substring(0, fileName.lastIndexOf("."))).imgUrl(imgUrl).worldcup(worldcup).build();
        }
    }

    @Data
    public static class FindOptionsDTO {
        private int page;
        private int size;
        private String itemname;
        private String orderOption;

        public String getItemname() {
            return itemname.trim();
        }

        public int getOffset() {
            return (page - 1) * size;
        }

        public int getLimit() {
            return size;
        }
    }
}
