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

import com.gamebase.article.model.ArticleContent;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.Forum;
import com.gamebase.article.model.MsgBoard;
import com.gamebase.article.model.service.ArticleService;
import com.gamebase.article.model.service.ForumService;
import com.gamebase.article.model.service.MsgBoardService;

import net.sf.json.JSONObject;

@Controller
@SessionAttributes(names = { "newForum", "newTitle", "forumList", "forum", "title", "titleList", "contentList",
		"userId" })
public class ArticleController {

	@Autowired
	private MsgBoardService mbService;
	@Autowired
	private ForumService fService;
	@Autowired
	private ArticleService aService;

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

	/* test */
	/* query all forum name */
	@RequestMapping(value = "/forum_test")
	public String goForumTest(ModelMap model) {
		System.out.println("Welcome to Forum");
		List<Forum> forumList = fService.queryForumAndTitle();
		model.addAttribute("forumList", forumList);
		return "testForumViewPage";
	}

	/* test */
	/* insert new forum name */
	@RequestMapping(value = "/forum_test/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewForum_Test(@RequestParam("forumName") String forumName,
			@RequestParam("forumFigure") String forumFigure, ModelMap model) {
		System.out.println("insert new Forum");
//		String imgURL = (String) model.getAttribute("imgURL");
//		if (imgURL.length() == 0 || imgURL == null) {
//			System.out.println("img did not upload!");
//		} else {
//			forumFigure = imgURL;
//		}
		/* forumName */
		if (forumName.length() == 0 || forumName == null) {
			System.out.println("forumName is null !");
		}
		/* forumFigure */
		if (forumFigure.length() == 0 || forumFigure == null) {
			System.out.println("forumFigure is null !");
			forumFigure = "https://i.imgur.com/8g2jFuM.png";
		}

		Forum newForum = fService.insertForum(new Forum(forumName, forumFigure));
		JSONObject result = new JSONObject();
		result.put("newForum", newForum);
		System.out.println(result);
		return result;
	}

	/* test */
	/* query article by forum name */
	@RequestMapping(value = "/forum_test/{forumId}", method = RequestMethod.GET)
	public String getArticlesByForumId_test(@PathVariable(name = "forumId") Integer forumId, ModelMap model) {
		System.out.println("get in controller");
		Forum forum = fService.queryOneForum(new Forum(forumId));
		JSONObject j = new JSONObject();
		j.put("forum", forum);
		System.out.println(j);
		model.addAttribute("forum", forum);

		List<ArticleTitle> titleList = aService.queryTitleByForumId(forumId);
		if (titleList != null && titleList.size() != 0) {
			model.addAttribute("titleList", titleList);
			System.out.println("title list found!!");
		} else {
			System.out.println("title list not found!!");
			model.addAttribute("titleList", null);
		}
		j.put("titleList", titleList);
		System.out.println(j);
		return "testTitleViewPage";
	}

	/* test */
	/* insert new article title */
	@RequestMapping(value = "/forum_test/{forumId}/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewParent_test(@PathVariable("forumId") Integer forumId, String content, Integer userId,
			String articleTitle, ModelMap model) {
		System.out.println("insert new Parent Article");
		JSONObject result = new JSONObject();
		try {
			/* insert title */
			ArticleTitle newTitle = aService
					.inertTitle(new ArticleTitle(forumId, articleTitle, "https://i.imgur.com/8g2jFuM.png"));
			/* insert content */
			ArticleContent newContent = aService
					.insertContent(new ArticleContent(newTitle.getTitleId(), userId, content));

			result.put("newTitle", newTitle);
			result.put("newContent", newContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/* test */
	/* query article content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}", method = RequestMethod.GET)
	public String getArticleListByTitleId_test(@PathVariable(name = "forumId") Integer forumId,
			@PathVariable(name = "titleId") Integer titleId, ModelMap model) {
		System.out.println("getArticleListByTitleId_test");

		Forum forum = fService.queryOneForum(new Forum(forumId));
		JSONObject j = new JSONObject();
		j.put("forum", forum);
		System.out.println(j);
		model.addAttribute("forum", forum);

		ArticleTitle title = aService.queryTitleByTitleId(titleId);
		j.put("title", title);
		System.out.println(j);
		model.addAttribute("title", title);

		List<ArticleContent> contentList = aService.queryContentByTitleId(new ArticleContent(titleId));
		if (contentList != null && contentList.size() != 0) {
			model.addAttribute("contentList", contentList);
			System.out.println("content list found!!");
		} else {
			System.out.println("content list not found!!");
			model.addAttribute("contentList", null);
		}

		return "testContentViewPage";
	}

	/* test */
	/* insert new article content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewParent_test(@PathVariable("forumId") Integer forumId,
			@PathVariable("titleId") Integer titleId, String content, Integer userId, String articleTitle,
			ModelMap model) {
		System.out.println("insert new article content");
		JSONObject result = new JSONObject();
		try {
			/* session title */
			ArticleTitle title = (ArticleTitle) model.getAttribute("title");
			/* insert new reply content */
			ArticleContent newContent = aService
					.insertContent(new ArticleContent(title.getTitleId(), userId, content));
			
			result.put("newContent", newContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
