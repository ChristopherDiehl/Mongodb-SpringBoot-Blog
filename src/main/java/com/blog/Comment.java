package com.blog;
import org.springframework.data.annotation.Id;

public class Comment 
{
	
	private String blogId;
	private String message;
	private String user;

	public Comment(String blogId, String message, String user)
	{
		this.blogId = blogId;
		this.message = message;
		this.user = user;
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

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}
}