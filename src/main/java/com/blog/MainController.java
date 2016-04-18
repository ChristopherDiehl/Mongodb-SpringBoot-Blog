package com.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Controller

public class MainController {

	@Autowired 
		private BlogPostRepository blogRepo;

	@RequestMapping("/")
	public String index()
	{
		return "index";
	}

	@RequestMapping(value="/blog_post{title}", method=RequestMethod.GET)
	public ModelMap blogByTitle(@PathVariable String title) 
	{
	  BlogPost bp = blogRepo.findByTitle(title);
	  ModelMap model = new ModelMap();
	  model.put("BLOG_TITLE",bp.getTitle());
	  model.put("BLOG_AUTHOR",bp.getAuthor());
	  model.put("BLOG_BODY",bp.getBody());
	  model.put("BLOG_TAGS",bp.getTags());
	  model.put("BLOG_SUMMARY",bp.getSummary());
	  return model;
	}

	@RequestMapping(value="/blog_post{tags}", method=RequestMethod.GET)
	public ModelMap blogByTitle(@PathVariable ArrayList<String> tags) 
	{
	  ArrayList<BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findByTags(tags);
	  ModelMap mp = new ModelMap();
	  mp.put("BLOGS",bpl);
	  return mp;
	}

	@RequestMapping(value="/newPost", method=RequestMethod.POST)
    public final String ReturnUrl (@ModelAttribute BlogPost bp, Model model) {
    	  System.out.println("THis is redirecting");
        model.addAttribute("bp", bp);
        blogRepo.save(bp);
        return "redirect:index.jsp";
   }

	@RequestMapping("/videos")
	public String videos(ModelMap model) 
	{
		//should do foo. But it's all static
		return "THIS IS VIDEO CONTROLLER";
	}

	@RequestMapping("/blog_post")
	public void getBlogPost(ModelMap model) 
	{

		ArrayList <BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findAll();
		model.put("BLOGS",bpl);
	}
}
