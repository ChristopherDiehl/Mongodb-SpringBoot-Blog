package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

	public BlogPost findByTitle(String title);
	public List<BlogPost> findByTags(ArrayList<String> tags);

}