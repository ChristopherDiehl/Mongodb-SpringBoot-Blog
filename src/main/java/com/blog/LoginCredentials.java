package com.blog;

public class LoginCredentials 
{

	private String inputUsername;
	private String inputPsswd;
	public LoginCredentials()
	{}
	
	public LoginCredentials(String inputUsername, String inputPsswd)
	{
		this.inputUsername = inputUsername;
		this.inputPsswd = inputPsswd;
	}

	public boolean isAdmin()
	{
		return "EmCataldi17".equals(inputUsername) && "EmCl@ash17!".equals(inputPsswd);
	}
	
	public String getinputUsername()
	{
		return inputUsername;
	}

	public String getinputPsswd()
	{
		return inputPsswd;
	}

	public void setinputPsswd(String inputPsswd)
	{
		this.inputPsswd = inputPsswd;
	}
	
	public void setinputUsername(String inputUsername)
	{
		this.inputUsername = inputUsername;
	}
}