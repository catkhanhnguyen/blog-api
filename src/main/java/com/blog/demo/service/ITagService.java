package com.blog.demo.service;

import java.util.List;
import com.blog.demo.model.Tag;

public interface ITagService {

    Tag getTagById(long id);

    Tag createTag(Tag tag);

    Tag updateTag(long id, Tag tag);

    void deleteTag(long id);

    List<Tag> getAllTags();
}
