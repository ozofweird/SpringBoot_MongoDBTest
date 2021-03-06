package org.example.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.controller.dto.PostsListResponseDto;
import org.example.controller.dto.PostsResponseDto;
import org.example.controller.dto.PostsSaveRequestDto;
import org.example.controller.dto.PostsUpdateRequestDto;
import org.example.domain.posts.DatabaseSequence;
import org.example.domain.posts.Posts;
import org.example.domain.posts.PostsRepository;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toDocument()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts document = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(document);
    }

    // 글 목록
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAll() {
        return postsRepository.findAll().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

    /*
    @Transactional(readOnly = true)
    - 조회 기능만 남겨두어, 조회 속도가 개선되도록 한다.

    public List<PostListResponseDto> findAll()
    .map(PostsListResponseDto::new)
    .collect(Collectors.toList());
    - postsRepository의 결과인 Posts의 stream을 map을 통해 변환한다.
     */
}
