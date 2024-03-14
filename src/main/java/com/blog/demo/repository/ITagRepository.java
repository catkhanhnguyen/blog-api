package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blog.demo.model.Tag;

@Repository
public interface ITagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
