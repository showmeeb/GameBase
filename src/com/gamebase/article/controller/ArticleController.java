package com.gamebase.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.article.model.Forum;
import com.gamebase.article.model.MsgBoard;
import com.gamebase.article.model.service.ForumService;
import com.gamebase.article.model.service.MsgBoardService;

@Controller
@SessionAttributes(names = { "forumName", "newForum", "forumList", "mbList", "childList" })
public class ArticleController {

	private MsgBoardService mbService;
	private ForumService fService;

	@Autowired
	public ArticleController(MsgBoardService mbService, ForumService fService) {
		this.mbService = mbService;
		this.fService = fService;
	}

	/* query all forum name */
	@RequestMapping(value = "/forum")
	public String goMsgBoard(ModelMap model) {
		System.out.println("Welcome to Forum");
		List<Forum> forumList = fService.queryAllForum();
		model.addAttribute("forumList", forumList);
		return "forumViewPage";
	}

	/* query article by forum name */
	@RequestMapping(value = "/forum/{forumName}", method = RequestMethod.GET)
	public String getArticlesByForumName(@PathVariable(name = "forumName") String forumName, ModelMap model) {
		System.out.println("get in controller");
		List<MsgBoard> mbList = mbService.queryParentMsg(new MsgBoard(forumName));
		if (mbList != null && mbList.size() != 0) {
			model.addAttribute("forumName", forumName);
			model.addAttribute("mbList", mbList);
			System.out.println("article list found!!");
		} else {
			System.out.println("msg not found!!");
			model.addAttribute("forumName", "msg not found!!");
			model.addAttribute("mbList", null);
		}
		return "parentArticleViewPage";
	}

	/* query child articles */
	@RequestMapping(value = "/forum/{forumName}/{parentId}", method = RequestMethod.GET)
	public String getArticleListByParentId(@PathVariable(name = "forumName") String forumName,
			@PathVariable(name = "parentId") Integer parentId, ModelMap model) {
		System.out.println("get in controller");
		List<MsgBoard> childList = mbService.queryArticlesByParentId(parentId);
		if (childList != null && childList.size() != 0) {
			model.addAttribute("forumName", forumName);
			model.addAttribute("childList", childList);
			System.out.println("child article list found!!");
		} else {
			System.out.println("msg not found!!");
			model.addAttribute("forumName", "child article not found!!");
			model.addAttribute("childList", null);
		}

		return "childArticleViewPage";
	}
	
	/* insert new forum name */
	/*old forum name will miss*/
	@RequestMapping(value = "/forum/add", method = RequestMethod.POST)
	public String insertNewForum(@RequestParam("forumName") String forumName, @RequestParam("forumFigure") String forumFigure, ModelMap model) {
		System.out.println("insert new Forum");
		Forum newForum = fService.insertForum(new Forum(forumName, forumFigure));
		model.addAttribute("newForum", newForum);
		return "forumViewPage";
	}

	/* update page */
//	@RequestMapping(path = "", method = RequestMethod.GET)
//	public String getMsgBoardsByLocation(@RequestParam(name = "Location") String Location, Model m,
//			RedirectAttributes redirectAttributes) {
//
//		return "xxx";
//	}

	/* delete one msgboard page */
//	@RequestMapping(path = "", method = RequestMethod.GET)
//	public String getMsgBoardsByLocation(@RequestParam(name = "Location") String Location, Model m,
//			RedirectAttributes redirectAttributes) {
//
//		return "xxx";
//	}
}
