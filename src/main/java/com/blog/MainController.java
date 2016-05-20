package com.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Controller

public class MainController {

	@Autowired 
	private BlogPostRepository blogRepo;
	@Autowired
	private CommentRepository commentRepo;

	@RequestMapping(value={"/","/index"}, method=RequestMethod.GET)
	public String index(ModelMap model)
	{
		Sort sort = new Sort(Sort.Direction.DESC,"date");
		ArrayList <BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findAll(sort);
		
		model.put("BLOGS",bpl);
		return "index";
	}


	@RequestMapping(value={"/searchIndex"}, method=RequestMethod.POST)
	public String searchIndex(ModelMap model, @RequestParam("search") String search)
	{
		System.out.println("searchIndex");
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
		System.out.println(request.getRemoteAddr() +"tried to access /login");
		System.out.println("Login controller");
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPost(Model model)
	{	
		model.addAttribute("bp",new BlogPost());
		return "newPost";
	}
	
	@RequestMapping("/login-error.html")
	public String loginError(Model model) 
	{
		System.out.println("ADDING LOGING ERROR");
		model.addAttribute("loginError", true);
		return "login";
	}
	
	@RequestMapping("/error.html")
	public String error(HttpServletRequest request, Model model) 
	{
		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String errorMessage = null;

		if (throwable != null) 
		{
			errorMessage = throwable.getMessage();
		}

		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
	
	@RequestMapping(value="/newPost", method = RequestMethod.GET)
	public String isLoggedIn( Model model)
	{
		model.addAttribute("bp",new BlogPost());
		System.out.println("lc is not admin");
		return "newPost";
	}

	@RequestMapping(value={"/newPost","/edit"}, method=RequestMethod.POST)
	public String ReturnUrl (@ModelAttribute BlogPost bp, Model model) 
	{
		model.addAttribute("bp", bp);
		blogRepo.save(bp);
		model.addAttribute("bp",bp);

		return "blogPost";
	}

	@RequestMapping(value={"/comment"}, method=RequestMethod.POST)
	public ModelAndView ReturnUrl (@ModelAttribute Comment comment, Model model) 
	{
		System.out.println("comments");
		commentRepo.save(comment);
		return new ModelAndView("redirect:/blogPost");	
	}


	@RequestMapping(value="/blog_post{tags}", method=RequestMethod.GET)
	public ModelMap blogByTitle(@PathVariable ArrayList<String> tags) 
	{
		ArrayList<BlogPost> bpl = (ArrayList<BlogPost>) blogRepo.findByTags(tags);
		ModelMap mp = new ModelMap();
		mp.put("BLOGS",bpl);
		return mp;
	}


	@RequestMapping("/videos")
	public String videos(ModelMap model) 
	{
		//could  do foo. But it's all static
		return "videos";
	}

	@RequestMapping(value="/blogPost" , method=RequestMethod.GET)
	public String getBlogPosts(ModelMap model, @RequestParam("getBlogId") String blogId) 
	{
		BlogPost bp = blogRepo.findById(blogId);
		Sort sort = new Sort(Sort.Direction.DESC,"date");
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
