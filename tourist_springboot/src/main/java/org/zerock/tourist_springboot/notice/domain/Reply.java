package org.zerock.tourist_springboot.notice.domain;

import jakarta.persistence.*;
import lombok.*;
import org.zerock.tourist_springboot.config.BaseEntity;

@Entity
@Table(name="NoticeReply", indexes={@Index(name="idx_reply_notice_bno", columnList = "notice_tno")})
@Getter
@Builder
@ToString(exclude = "notice")
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;
    private String replyText;
    private String replyer;
    public void changeText(String text){
        this.replyText = text;
    }
}
