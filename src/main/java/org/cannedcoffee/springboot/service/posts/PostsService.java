package org.cannedcoffee.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.cannedcoffee.springboot.domain.posts.Posts;
import org.cannedcoffee.springboot.domain.posts.PostsRepository;
import org.cannedcoffee.springboot.web.dto.PostsResponseDto;
import org.cannedcoffee.springboot.web.dto.PostsSaveRequestDto;
import org.cannedcoffee.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){

        //retrieve data from db and set to entity
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= "+ id));

        //update
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }


    public PostsResponseDto findById(Long id) {

        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id= "+ id));

        return new PostsResponseDto(entity);
    }
}
