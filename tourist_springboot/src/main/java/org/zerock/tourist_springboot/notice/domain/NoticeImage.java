package org.zerock.tourist_springboot.notice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.zerock.tourist_springboot.config.BaseEntity;

@Entity
@Getter
@Builder
@ToString(exclude = "notice")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeImage extends BaseEntity implements Comparable<NoticeImage> {
    @Id
    private String uuid;
    private String fileName;
    private int ord;
    @ManyToOne
    private Notice notice;

    @Override
    public int compareTo(NoticeImage o) {
        return this.ord - o.ord;
    }
    public void changeNotice(Notice notice) {
        this.notice = notice;
    }
}
