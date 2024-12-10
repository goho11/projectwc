package com.metacoding.projectwc.worldcup;

import lombok.Data;
import com.metacoding.projectwc.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.aspectj.weaver.World;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

public class WorldcupResponse {

    @Data
    public static class findByIDDTO {
        private int id;
        private String title;
        private String description;

        public findByIDDTO(Worldcup worldcup) {
            this.id = worldcup.getId();
            this.title = worldcup.getTitle();
            this.description = worldcup.getDescription();
        }
    }
    @Data
    public static class findAllDTO {
        private Integer id;
        private String title;
        private String description;
        private Integer userId;
        private Integer gamesCompleted; // 인기순
        private Timestamp createdAt; // 최신순

        public findAllDTO(Worldcup worldcup) {
            this.id = worldcup.getId();
            this.title = worldcup.getTitle();
            this.description = worldcup.getDescription();
            this.userId = worldcup.getUser().getId();
            this.gamesCompleted = worldcup.getGamesCompleted();
            this.createdAt = worldcup.getCreatedAt();

        }
    }
}
