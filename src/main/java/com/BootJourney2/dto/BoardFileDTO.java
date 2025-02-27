package com.BootJourney2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class BoardFileDTO {

    private Long id;
    private Long boardId;
    private String originalFileName;
    private String storedFileName;
}
