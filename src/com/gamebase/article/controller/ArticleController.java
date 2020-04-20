package com.gamebase.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gamebase.article.model.Forum;
import com.gamebase.article.model.MsgBoard;
import com.gamebase.article.model.service.ArticleService;
import com.gamebase.article.model.service.ForumService;
import com.gamebase.article.model.service.MsgBoardService;

import net.sf.json.JSONObject;

@Controller
@SessionAttributes(names = { "newForum", "forumList", "mbList", "childList" })
public class ArticleController {

	private MsgBoardService mbService;
	private ForumService fService;
	private ArticleService aService;

	@Autowired
	public ArticleController(MsgBoardService mbService, ForumService fService) {
		this.mbService = mbService;
		this.fService = fService;
	}

	@RequestMapping(value = "/forumHome", method = RequestMethod.GET)
	public String forumHome() {

		return "forumHome";
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
//			model.addAttribute("forumName", "msg not found!!");/*影響 create new parent article forum name 取得*/
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
	@RequestMapping(value = "/forum/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewForum(@RequestParam("forumName") String forumName,
			@RequestParam("forumFigure") String forumFigure, ModelMap model) {
		System.out.println("insert new Forum");
		String imgURL = (String) model.getAttribute("imgURL");
		if (imgURL.length() == 0 || imgURL == null) {
			System.out.println("img did not upload!");
		} else {
			forumFigure = imgURL;
		}
		Forum newForum = fService.insertForum(new Forum(forumName, forumFigure));
		JSONObject result = new JSONObject();
		result.put("newForum", newForum);
		System.out.println(result);
		return result;
	}

	/* insert new parent article */
	@RequestMapping(value = "/forum/{forumName}/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewParent(@PathVariable("forumName") String forumName, String content, String accountId,
			String articleTitle, ModelMap model) {
		System.out.println("insert new Parent Article");
		JSONObject result = new JSONObject();
		try {
			MsgBoard newmb = mbService
					.insertMsg(new MsgBoard(0, Integer.parseInt(accountId), forumName, articleTitle, content));
			result.put("t", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/* insert new child article */
	@RequestMapping(value = "/forum/{forumName}/{parentId}/add", produces = "application/json")
	public JSONObject insertNewParent(@PathVariable("forumName") String forumName,
			@RequestParam("content") String content, @RequestParam("accountId") String accountId,
			@RequestParam("articleTitle") String articleTitle, @PathVariable("parentId") Integer parentId,
			ModelMap model) {
		JSONObject result = new JSONObject();
		System.out.println("insert new child");
		try {
			MsgBoard newchild = mbService
					.insertMsg(new MsgBoard(parentId, Integer.parseInt(accountId), forumName, articleTitle, content));
			result.put("t", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
