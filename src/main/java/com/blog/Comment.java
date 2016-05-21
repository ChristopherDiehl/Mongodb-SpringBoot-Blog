package com.blog;
import org.springframework.data.annotation.Id;

public class Comment 
{
	
	private String blogId;
	private String message;

	public Comment(String blogId, String message)
	{
		this.blogId = blogId;
		this.message = message;
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

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
}