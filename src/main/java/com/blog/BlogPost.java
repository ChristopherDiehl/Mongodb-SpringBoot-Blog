package com.blog;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class BlogPost 
{
	@Id
	private String id;
	private String title;
	private String author;
	private String body;
	private String summary;
	private String firstParagraph;
	private ArrayList <String> tags;
	@Transient
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	@Field
	private Date date;

	public BlogPost() {
		tags = new ArrayList<String>();
		this.date = new Date();
	}

	public BlogPost(String title, String author, String body, String summary, String tags) 
	{
		this.title = title;
		this.author = author;
		this.body = body;
		this.summary = summary;
		this.firstParagraph = getFirstParagraph();
		this.date = new Date();
		this.tags = (ArrayList<String>) Arrays.asList(tags.split("\\s*,\\s*"));
		System.out.println("tags: "+tags.toString());
	}

	@Override
	public String toString()
	{
		return String.format("Blog[id=%s, title='%s', author='%s', body='%s']",id,title,author,body);
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getSummary()
	{
		return summary;
	}
	

	
	public void setTags(ArrayList<String>tags)
	{	
		this.tags = tags;
		System.out.println("tags: "+tags.toString());

	}	

	public ArrayList <String> getTags()
	{
		return tags;
	}
	public String getBody()
	{
		return this.body;
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
	public String getAuthor()
	{
		return this.author;
	}

	public String getFirstParagraph()
	{
		String [] firstParagraph = body.split("</p>");
		if(firstParagraph.length > 0){
			System.out.println(firstParagraph[0]);

			return firstParagraph[0];
		}
		return "";
	}
	public String getTitle()
	{
		return this.title;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getId()
	{
		return id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}	

	public void setBody(String body)
	{
		this.body = body;
		this.firstParagraph = getFirstParagraph();
	}

}
