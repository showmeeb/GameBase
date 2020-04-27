package com.gamebase.article.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gamebase.article.model.ArticleContent;
import com.gamebase.article.model.ArticleListView;
import com.gamebase.article.model.ArticleRecord;
import com.gamebase.article.model.ArticleTitle;
import com.gamebase.article.model.ContentListView;
import com.gamebase.article.model.Forum;
import com.gamebase.article.model.ForumListView;
import com.gamebase.article.model.service.ArticleService;
import com.gamebase.article.model.service.ForumService;
import com.gamebase.general.model.service.GeneralService;

import net.sf.json.JSONObject;

@Controller
@SessionAttributes(names = { "userId" })
public class ArticleController {

	@Autowired
	private ForumService fService;
	@Autowired
	private ArticleService aService;
	@Autowired
	private GeneralService gService;

	@RequestMapping(value = "/forumHome", method = RequestMethod.GET)
	public String forumHome() {
		return "forumHome";
	}

	/* final */
	/* query all forum name */
	@RequestMapping(value = "/forum_test")
	public String goForumTest(ModelMap model) {
		System.out.println("Welcome to Forum");
		/* 選取點閱數最高前N個 */
		List<ForumListView> forumList = fService.queryForumListByClickNum(1);
		model.addAttribute("forumList", forumList);
		JSONObject j = new JSONObject();
		j.put("forumList", forumList);
		System.out.println(j);
		return "testForumViewPage";
	}

	/* test */
	/* insert new forum name and figure */
	@RequestMapping(value = "/forum_test/add")
	@ResponseBody
	public JSONObject insertNewForum_Test(@RequestParam("forumName") String forumName,
			@RequestParam("forumFigure") MultipartFile forumFigure, ModelMap model) {
		System.out.println("insert new Forum");
		/* figure upload to imgur */
		String imgURL = gService.uploadToImgur(forumFigure);
		System.out.println(imgURL);
		model.addAttribute("imgURL", imgURL);
		/* forumName */
		if (forumName.length() == 0 || forumName == null) {
			System.out.println("forumName is null !");
		}
		/* forumFigure */
		if (imgURL.length() == 0 || imgURL == null) {
			System.out.println("imgURL is null !");
			imgURL = "https://i.imgur.com/8g2jFuM.png";
		}
		Forum newForum = fService.insertForum(new Forum(forumName, imgURL));
		JSONObject result = new JSONObject();
		/* query forum rank */
		ForumListView flv = fService.queryForumListByForumId(newForum.getForumId());
		result.put("newForum", flv);
		System.out.println(result);
		return result;
	}

	/* final */
	/* query article by forum ID */
	@RequestMapping(value = "/forum_test/{forumId}", method = RequestMethod.GET)
	public String getArticlesByForumId_test(@PathVariable(name = "forumId") Integer forumId, ModelMap model) {
		System.out.println("get in controller");
		/* query foum */
		Forum forum = fService.queryOneForum(new Forum(forumId));
		JSONObject j = new JSONObject();
		j.put("forum", forum);
		System.out.println(j);
		model.addAttribute("forum", forum);
		/* query article title list */
		List<ArticleListView> articleList = aService.queryArticleListByContentRN(1, forumId);
		if (articleList != null && articleList.size() != 0) {
			model.addAttribute("articleList", articleList);
			System.out.println("article list found!!");
		} else {
			System.out.println("article list not found!!");
			model.addAttribute("articleList", null);
		}
		j.put("articleList", articleList);
		System.out.println(j);
		return "testTitleViewPage";
	}
	
	/* btn */
	/* query article by forum ID */
	@RequestMapping(value = "/forum_test/findforum/{forumId}", produces = "application/json")
	@ResponseBody
	public JSONObject getArticlesByForumId_btn(@PathVariable(name = "forumId") Integer forumId) {
		System.out.println("get in controller");
		/* query foum */
		Forum forum = fService.queryOneForum(new Forum(forumId));
		JSONObject j = new JSONObject();
		j.put("forum", forum);
		System.out.println(j);
		return j;
	}

	/* test */
	/* insert new article title */
	@RequestMapping(value = "/forum_test/{forumId}/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewParent_test(@PathVariable("forumId") Integer forumId, String content, Integer userId,
			String articleTitle, String firstFigure, ModelMap model) {
		System.out.println("insert new Parent Article");
		JSONObject result = new JSONObject();
		try {
			/*first figure*/
			if (firstFigure.length() == 0 || firstFigure == null) {
				System.out.println("firstFigure is null !");
				firstFigure = "https://i.imgur.com/8g2jFuM.png";
			}
			/* insert title */
			ArticleTitle newTitle = aService
					.inertTitle(new ArticleTitle(forumId, articleTitle, firstFigure));
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

	/* final */
	/* query article content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}", method = RequestMethod.GET)
	public String getArticleListByTitleId_test(@PathVariable(name = "forumId") Integer forumId,
			@PathVariable(name = "titleId") Integer titleId, ModelMap model) {
		System.out.println("getArticleListByTitleId_test");
		/* query forum */
		Forum forum = fService.queryOneForum(new Forum(forumId));
		model.addAttribute("forum", forum);
		/* query title */
		ArticleTitle title = aService.queryTitleByTitleId(titleId);
		model.addAttribute("title", title);
		/* click num +1 */
		Integer clickNum = title.getClickNum() + 1;
		title.setClickNum(clickNum);
		title = aService.updateTitle(title);
		/* query content list view */
		List<ContentListView> contentList = aService.queryContentListByTitleId(titleId);
		if (contentList != null && contentList.size() != 0) {
			model.addAttribute("contentList", contentList);
			System.out.println("content list found!!");
		} else {
			System.out.println("content list not found!!");
			model.addAttribute("contentList", "");
		}
		/* query user record */
		model.addAttribute("userId", (Integer) 1);
		ArticleRecord record = aService
				.queryRecordByUserIdAndTitleId(new ArticleRecord((Integer) model.getAttribute("userId"), titleId));
		model.addAttribute("record", record);
		return "testContentViewPage";
	}

	/* test */
	/* insert new reply content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/add", produces = "application/json")
	@ResponseBody
	public JSONObject insertNewParent_test(@PathVariable("forumId") Integer forumId,
			@PathVariable("titleId") Integer titleId, String content, Integer userId, String articleTitle,
			ModelMap model) {
		System.out.println("insert new article content");
		JSONObject result = new JSONObject();
		try {
			/* query title */
			ArticleTitle title = aService.queryTitleByTitleId(titleId);
			/* update last reply time */
			title.setLastReplyTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			title = aService.updateTitle(title);
			model.addAttribute("title", title);
			/* insert new reply content */
			ArticleContent newContent = aService.insertContent(new ArticleContent(title.getTitleId(), userId, content));
			result.put("newContent", newContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/* final */
	/* update article data:like,unlike */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/record", produces = "application/json")
	@ResponseBody
	public JSONObject updateRecord(@PathVariable("forumId") Integer forumId, @PathVariable("titleId") Integer titleId,
			String clickedBTN, ModelMap model) {
		System.out.println("update article title record");
		JSONObject result = new JSONObject();
		/* query record */
		ArticleRecord record = aService
				.queryRecordByUserIdAndTitleId(new ArticleRecord((Integer) model.getAttribute("userId"), titleId));
		/* query title */
		ArticleTitle title = aService.queryTitleByTitleId(titleId);

		String original = "no";
		if (record == null) {

			record = aService
					.insertRecord(new ArticleRecord((Integer) model.getAttribute("userId"), titleId, clickedBTN));
			System.out.println("record is null");
		} else {

			System.out.println("record is not null");
			original = record.getRecord();
			System.out.println("original : " + original);
			if (original.equals(clickedBTN)) {
				record.setRecord("no");
			} else {
				record.setRecord(clickedBTN);
				System.out.println("no, record : " + record.getRecord());
			}

			record = aService.updateRecord(record);
		}

		title = aService.updateTitleData(title, original, clickedBTN);
		result.put("record", record);
		result.put("title", title);
		return result;
	}

	/* update forum title or forum's figure */
	@RequestMapping(value = "/forum_test/{forumId}/update", produces = "application/json")
	@ResponseBody
	public JSONObject updateForum(@PathVariable("forumId") Integer forumId,@RequestParam("forumName") String forumName,
			@RequestParam("forumFigure") MultipartFile forumFigure) {
		System.out.println("update forum");
		JSONObject result = new JSONObject();
		
		/* figure upload to imgur */
		String imgURL = gService.uploadToImgur(forumFigure);
		System.out.println(imgURL);
		/* forumName */
		if (forumName.length() == 0 || forumName == null) {
			System.out.println("forumName is null !");
		}
		/* forumFigure */
		if (imgURL.length() == 0 || imgURL == null) {
			System.out.println("imgURL is null !");
			imgURL = "https://i.imgur.com/8g2jFuM.png";
		}
		
		Forum forum = fService.queryOneForum(new Forum(forumId));
		forum.setForumName(forumName);
		forum.setForumFigure(imgURL);
		forum = fService.updateOneForum(forum);

		result.put("forum", forum);
		System.out.println(result);
		return result;
	}

	/* update aritcle title and content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/update", produces = "application/json")
	@ResponseBody
	public JSONObject updateForum(@PathVariable("forumId") Integer forumId, @PathVariable("titleId") Integer titleId,
			String titleName, String firstFigure, Integer contentId, ModelMap model) {
		System.out.println("update article title");
		JSONObject result = new JSONObject();
		/* update title */
		ArticleTitle title = aService.queryTitleByTitleId(titleId);
		title.setTitleName(titleName);
		title.setFirstFigure(firstFigure);
		title = aService.updateTitle(title);
		/* update content */
		ArticleContent content = new ArticleContent();
		content.setContentId(contentId);
		content = aService.querytOneContentByContentId(content);
		content = aService.updateContent(content);

		result.put("title", title);
		result.put("content", content);
		System.out.println(result);
		return result;
	}

	/* update reply content */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/{contentId}/update", produces = "application/json")
	@ResponseBody
	public JSONObject updateForum(@PathVariable("forumId") Integer forumId, @PathVariable("titleId") Integer titleId,
			@PathVariable("contentId") Integer contentId, String titleName, String firstFigure, String clickedBTN, ModelMap model) {
		System.out.println("update article title");
		JSONObject result = new JSONObject();
		ArticleContent content = new ArticleContent();
		String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		/*identify clicked btn*/
		if(clickedBTN.equals("delete")) {
			/* update reply content */		
			content.setContentId(contentId);
			content = aService.querytOneContentByContentId(content);
			content.setContent("<p>文章已刪除!!</p>");
			content.setUpdateTime(updateTime);
			content = aService.updateContent(content);
		}else {
			/* update reply content */
			content.setContentId(contentId);
			content = aService.querytOneContentByContentId(content);
			content = aService.updateContent(content);
		}


		result.put("content", content);
		System.out.println(result);
		return result;
	}

	/* delete forum */
	@PostMapping(value = "/forum_test/{forumId}/del", produces = "application/json")
	@ResponseBody
	public JSONObject deleteForum(Integer forumId, ModelMap model) {
		System.out.println("delete forum");
		JSONObject result = new JSONObject();
		Forum forum = new Forum(forumId);
		Boolean delStatus = fService.deleteOneForum(forum);

		result.put("delStatus", delStatus);
		System.out.println(result);
		return result;
	}

	/* delete title , content , user's record */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/del", produces = "application/json")
	@ResponseBody
	public JSONObject deleteForum(@PathVariable("forumId") Integer forumId, @PathVariable("titleId") Integer titleId,
			ModelMap model) {
		System.out.println("delete title , content , user's record");
		JSONObject result = new JSONObject();
		Boolean delStatus = aService.deleteArticleAndReply(titleId);

		result.put("delStatus", delStatus);
		System.out.println(result);
		return result;
	}

	/* delete reply *//* or content? */
	@RequestMapping(value = "/forum_test/{forumId}/{titleId}/{contentId}/del", produces = "application/json")
	@ResponseBody
	public JSONObject deleteForum(@PathVariable("forumId") Integer forumId, @PathVariable("titleId") Integer titleId,
			@PathVariable("contentId") Integer contentId, ModelMap model) {
		System.out.println("delete reply");
		JSONObject result = new JSONObject();
		Boolean delStatus = aService.deleteReply(contentId);
		result.put("delStatus", delStatus);
		System.out.println(result);
		return result;
	}
	


	//後臺全部文章列表
	@RequestMapping(path = "/getAllArticles", produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getAllArticleTitle() {
		System.out.println("query All Article Title");
		JSONObject result = new JSONObject();
		try {
			 List<ArticleTitle> a = aService.queryAllArticleTitle();
			result.put("articles",a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//後臺全部文章列表
	@RequestMapping(value = "/allArticles", method = RequestMethod.GET)
	public String showAllArticles() {
		return "allArticles";
	}

	/* upload figure to imgur */
	@RequestMapping(value = "/figureupload", produces = "application/json")
	@ResponseBody
	public Map<String, String> deleteForum(@RequestPart("upload")MultipartFile forumFigure, ModelMap model) {
		System.out.println("figure upload");
		Map<String, String> result =new HashMap<>();
		String imgurl = gService.uploadToImgur(forumFigure);
		if(imgurl != null) {
			result.put("uploaded", "true");
			result.put("url", imgurl);
			System.out.println(result);
		}else {
			result.put("uploaded", "false");
			result.put("url", null);
			System.out.println(result);
		}

		return result;
	}


}
