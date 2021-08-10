package org.cannedcoffee.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}
