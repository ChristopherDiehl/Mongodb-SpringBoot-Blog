package com.blog;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

public class Comment 
{
	
	private String blogId;
	private String message;
	private String user;
	@Transient
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	@Field
	private Date date;

	public Comment(String blogId, String message, String user)
	{
		this.blogId = blogId;
		this.message = message;
		this.user = user;
		this.date = new Date();
	}

	public Comment() {
		this.date = new Date();
	}

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

	@Transient
	public String getDate()
	{
		return dateFormat.format(date);
	}
	
	@Transient
	public void setDate()
	{
		date = new Date();
	}

}