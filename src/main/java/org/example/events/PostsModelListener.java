package org.example.events;

import lombok.RequiredArgsConstructor;
import org.example.domain.posts.Posts;
import org.example.service.SequenceGenerator.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostsModelListener extends AbstractMongoEventListener<Posts> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Posts> event) {
//        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Posts.SEQUENCE_NAME));
//        }
    }
}
