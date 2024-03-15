package com.blog.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.Tag;
import com.blog.demo.repository.ITagRepository;

@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private ITagRepository tagRepository;

    @Override
    public Tag getTagById(long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag createTag(Tag tag) {
        if (tag.getName() != null) {
            Tag existedTag = tagRepository.findByName(tag.getName());
            if (existedTag == null) {
                return tagRepository.save(tag);
            }
            return null;
        }
        return null;
    }

    @Override
    public Tag updateTag(long id, Tag tag) {
        if (tag.getName() != null) {
            if (tagRepository.existsById(id)) {
                tag.setId(id);
                return tagRepository.save(tag);
            }
            return null;
        }
        return null;
        
        // Tag existTag = getTagById(id);
        // if(existTag != null){
        // existTag.setName(tag.getName());
        // tagRepository.save(existTag);
        // return existTag;
        // }
        // return null;
    }

    @Override
    public void deleteTag(long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
