package org.zerock.tourist_springboot.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListAllDTO {
    private Long tno;
    private String title;
    private String content;
    private int view;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long replyCount;
    private List<NoticeImageDTO> noticeImages;
}
