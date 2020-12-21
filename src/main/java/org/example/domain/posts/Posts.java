package org.example.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "posts")
public class Posts {

    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private Long id;
    private String title;
    private String content;
    private String author;

    public void setId(Long id) {
        this.id = id;
    }

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /*
    @Builder
    - 생성자 상단에 선언 시 생성자에 포함도니 필드만 빌더에 포함된다.
    - 데이터베이스에 데이터를 넣기 위한 Setter 메서드를 대체한다.
     */
}
