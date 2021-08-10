package org.cannedcoffee.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)

// without other config but only this annotation, h2 db will be used for config
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    // run after each test is done.
    @After
    public void cleanup()  {
        postsRepository.deleteAll();
    }

    @Test
    public void call_posts(){
        //given
        String title = "test post";
        String content = "test content";

        // insert/update query in table Posts. if ID is given update is run, if not, insert.
        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("hsbang.thom@gmail.com")
            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();
    }

    @Test
    public void test_BaseTimeEntity(){
        //given
        LocalDateTime now = LocalDateTime.of(2021,8,11,6,42);
        postsRepository.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>createdDate " + posts.getCreatedDate() + ", modifiedDate" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
