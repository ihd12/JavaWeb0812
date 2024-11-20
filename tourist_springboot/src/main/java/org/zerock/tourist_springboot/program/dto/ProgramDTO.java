package org.zerock.tourist_springboot.program.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDTO {
    private Long no;
    private String title;
    private String text;
    private String subtext;
    private String schedule;
    private String img;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
