package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc.worldcup.Worldcup;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@NoArgsConstructor
@Getter
@Table(name = "worldcup_item_tb")
@Entity
public class WorldcupItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String itemname;

    @Column(nullable = false, unique = true)
    private String imgUrl;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer championCount;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer winCount;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer totalCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Worldcup worldcup;

    // 논리 삭제
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Builder
    public WorldcupItem(Integer id, String itemname, String imgUrl, Integer championCount, Integer winCount, Integer totalCount, Worldcup worldcup, Boolean isDeleted) {
        this.id = id;
        this.itemname = itemname;
        this.imgUrl = imgUrl;
        this.championCount = championCount;
        this.winCount = winCount;
        this.totalCount = totalCount;
        this.worldcup = worldcup;
        this.isDeleted = isDeleted;
    }


    @PrePersist
    public void prePersist() {
        if (championCount == null)
            championCount = 0;
        if (winCount == null)
            winCount = 0;
        if (totalCount == null)
            totalCount = 0;
        if (isDeleted == null)
            isDeleted = false;
    }

    public void updateImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void updateItemname(String itemname) {
        this.itemname = itemname;
    }
}
