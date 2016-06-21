package com.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

@Controller

public class MainController {

	
	@Autowired 
	private BlogPostRepository blogRepo;
	@Autowired
	private CommentRepository commentRepo;
	
	private final Logger logger = LogManager.getLogger("com.blog");

	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String index(ModelMap model)
	{

		Sort sort = new Sort(Sort.Direction.DESC,"date");
		ArrayList <BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findAll(sort);

		model.put("BLOGS",bpl);
		return "index";
	}

	@RequestMapping(value={"/resume"}, method=RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource getFile(ServletRequest request) 
	{
		logger.trace(request.getRemoteAddr()+ ": resume");
		ClassLoader classLoader = getClass().getClassLoader();
		return new FileSystemResource(classLoader.getResource("static/files/Resume.pdf").getFile());

	}

	@RequestMapping(value={"/searchIndex"}, method=RequestMethod.POST)
	public String searchIndex(ModelMap model, @RequestParam("search") String search)
	{
		Sort sort = new Sort(Sort.Direction.DESC,"date");
		ArrayList <BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findByTitle(sort,search);
		
		if(bpl.size()  <= 0)
		{
			ArrayList <String> tags = new ArrayList<String>();
			tags.add(search);
			bpl = (ArrayList<BlogPost>) blogRepo.findByTags(sort,tags);
		}
		
		model.put("BLOGS",bpl);
		return "index";
	}

	@RequestMapping(value="/aboutUs")
	public String aboutUs()
	{
		return "aboutUs";
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

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest request)
	{	
		logger.trace(request.getRemoteAddr() +" tried to access /login");
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(Model model)
	{	

		model.addAttribute("bp",new BlogPost());
		return "newPost";
	}
	
	@RequestMapping(value="/newPost", method = RequestMethod.GET)
	public String isLoggedIn( Model model)
	{
		model.addAttribute("bp",new BlogPost());
		return "newPost";
	}

	@RequestMapping(value={"/newPost","/edit"}, method=RequestMethod.POST)
	public String ReturnUrl (@ModelAttribute BlogPost bp, Model model) 
	{
		model.addAttribute("bp", bp);
		blogRepo.save(bp);
		model.addAttribute("bp",bp);
		model.addAttribute("comments",null);
		model.addAttribute("comment",new Comment());

		return "blogPost";
	}

	@RequestMapping(value={"/comment"}, method=RequestMethod.POST)
	public ModelAndView ReturnUrl (@ModelAttribute Comment comment, Model model, ServletRequest request) 
	{
		logger.trace(request.getRemoteAddr()+": comment :"+comment.getMessage()+"blogID: "+comment.getblogId());
		commentRepo.save(comment);
		
		return new ModelAndView("redirect:/blogPost?getBlogId="+comment.getblogId());	
	}


	@RequestMapping(value="/blog_post{tags}", method=RequestMethod.GET)
	public ModelMap blogByTitle(@PathVariable ArrayList<String> tags) 
	{
		ArrayList<BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findByTags(tags);
		ModelMap mp = new ModelMap();
		mp.put("BLOGS",bpl);
		return mp;
	}


	@RequestMapping("/portfolio")
	public String portfolio(ModelMap model) 
	{
		//could  do foo. But it's all static
		return "portfolio";
	}

	
	
	@RequestMapping(value="/blogPost" , method=RequestMethod.GET)
	public String getBlogPosts(ModelMap model, @RequestParam("getBlogId") String blogId) 
	{
		BlogPost bp = blogRepo.findById(blogId);
		Sort sort = new Sort(Sort.Direction.ASC,"date");
		ArrayList <Comment> comments = commentRepo.findByBlogId(sort,bp.getId());

		model.put("comments",comments);
		model.put("comment",new Comment());
		model.put("bp",bp);

		return "blogPost";
	}
	
	@RequestMapping(value="/edit" , method=RequestMethod.GET)
	public String editBlogPost(ModelMap model, @RequestParam("getBlogId") String blogId) 
	{
		BlogPost bp = blogRepo.findById(blogId);
		model.put("bp",bp);
		return "edit";
	}


}
