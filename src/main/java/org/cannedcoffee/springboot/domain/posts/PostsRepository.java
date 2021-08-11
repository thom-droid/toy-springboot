package org.cannedcoffee.springboot.domain.posts;

/*
 * it is called Repository and same as Dao (DB layer)
 * just by extending to JpaRepository<Entity class, PK type>, all plain CRUD methods are created.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p  FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
