package com.blog;
import org.springframework.data.annotation.Id;

public class Comment 
{
	@Id
	private String id;
	private String blogId;
	private String comment;

	public Comment(String blogId, String comment)
	{
		this.blogId = blogId;
		this.comment = comment;
	}

	public Comment() {}

	public void setblogId(String blogId)
	{
		this.blogId = blogId;
	}

	public String getblogId ()
	{
		return blogId;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}
}