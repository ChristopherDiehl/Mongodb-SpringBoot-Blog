package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepositorye extends MongoRepository<Comment, String> {

	public List<Comment> findByAssociatedBlogTitle(String title);

}