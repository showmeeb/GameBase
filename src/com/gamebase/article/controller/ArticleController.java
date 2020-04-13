package com.gamebase.article.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping(value = "/forum/add", method = RequestMethod.POST)
	public String insertNewForum(@RequestParam("forumName") String forumName,
			@RequestParam("forumFigure") String forumFigure, ModelMap model) {
		System.out.println("insert new Forum");
		Forum newForum = fService.insertForum(new Forum(forumName, forumFigure));
		List<Forum> newForumList = (List<Forum>) model.getAttribute("forumList");
		newForumList.add(newForum);
		model.addAttribute("forumList", newForumList);
		return "forumViewPage";
	}

	/* insert new parent article */
	@RequestMapping(value = "/forum/${forumName}/add", method = RequestMethod.POST)
	public String insertNewParent(@RequestParam("forumName") String forumName, @RequestParam("content") String content,
			@RequestParam("accountId") String accountId, @RequestParam("articleTitle") String articleTitle,
			ModelMap model) {
		System.out.println("insert new Parent");

//		MsgBoard newmb = mbService.insertMsg(
//				new MsgBoard(0, Integer.parseInt(accountId), forumName, articleTitle, content, getDateTime()));

		MsgBoard newmb = mbService.insertMsg(new MsgBoard(0, 1, forumName, "test", content, getDateTime()));

		List<MsgBoard> newmbList = (List<MsgBoard>) model.getAttribute("mbList");
		newmbList.add(newmb);
		model.addAttribute("mbList", newmbList);
		return "parentArticleViewPage";
	}

	/* insert new child article */
	@RequestMapping(value = "/forum/${forumName}/${parentId}/add", method = RequestMethod.POST)
	public String insertNewParent(@RequestParam("forumName") String forumName, @RequestParam("Content") String Content,
			@RequestParam("accountId") String accountId, @RequestParam("articleTitle") String articleTitle,
			@RequestParam("parentId") Integer parentId, ModelMap model) {

		System.out.println("insert new child");
		MsgBoard newchild = mbService.insertMsg(
				new MsgBoard(parentId, Integer.parseInt(accountId), forumName, articleTitle, Content, getDateTime()));

		List<MsgBoard> newchildList = (List<MsgBoard>) model.getAttribute("childList");
		newchildList.add(newchild);
		model.addAttribute("childList", newchildList);
		return "parentArticleViewPage";
	}

	/* 取得現在時間方法 */
	public String getDateTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		// System.out.println(strDate);
		return strDate;
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
