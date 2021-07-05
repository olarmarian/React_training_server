package ro.fortech.comments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Comment {

    private int id;
    private String content;
    private String user;
    private LocalDate date;
    private LocalDate lastModifiedDate;
    private String category;
}
