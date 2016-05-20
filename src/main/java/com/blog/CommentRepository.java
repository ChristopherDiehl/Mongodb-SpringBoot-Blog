package com.blog;

import java.util.ArrayList;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

	public ArrayList<Comment> findByBlogId(Sort date, String title);

}