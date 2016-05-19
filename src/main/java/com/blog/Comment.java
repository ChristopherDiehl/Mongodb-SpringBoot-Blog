package com.blog;

public Class Comment 
{
	@Id
	private String id;
	private String associatedBlogTitle
	private String comment;

	public Comment(String associatedBlogTitle, String comment)
	{
		this.associatedBlogTitle = associatedBlogTitle;
		this.comment = comment;
	}

	public Comment() {}

	public setAssociatedBlogTitle(String associatedBlogTitle)
	{
		this.associatedBlogTitle = associatedBlogTitle;
	}

	public getAssociatedBlogTitle ()
	{
		return associatedBlogTitle;
	}

	public setComment(String comment)
	{
		this.comment = comment;
	}

	public getComment()
	{
		return comment;
	}
}