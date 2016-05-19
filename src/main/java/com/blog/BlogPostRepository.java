package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

	public BlogPost findByTitle(String title);
	public BlogPost findById(String id);
	public List<BlogPost> findByTags(ArrayList  tags);
	public Page<BlogPost> findAll(Pageable pageable);
	public List<BlogPost> findAll(Sort date);
	public List<BlogPost> findByTags(Sort date, ArrayList tags);
	public List<BlogPost> findByTitle(Sort date, String title);
}