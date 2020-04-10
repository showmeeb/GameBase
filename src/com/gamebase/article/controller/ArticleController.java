package com.gamebase.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gamebase.article.model.MsgBoard;
import com.gamebase.article.model.service.MsgBoardService;

@Controller
public class ArticleController {

	private MsgBoardService mbService;

	@Autowired
	public ArticleController(MsgBoardService mbService) {
		this.mbService = mbService;
	}

	/* first page */
	@RequestMapping(value = "/goMsgBoard", method = RequestMethod.GET)
	public String goMsgBoard() {
		System.out.println("go msgboard");
		return "articleViewPage";
	}

	/* insert */
	@RequestMapping(value = "/insertMsgBoard/{location}", method = RequestMethod.GET)
	public String getMsgBoardsByLocation(@PathVariable(name = "location") String location, ModelMap model) {
		System.out.println("get in controller");
		List<MsgBoard> mbList = mbService.querySomeMsg(new MsgBoard(location));
		if (mbList != null && mbList.size() != 0) {
			model.addAttribute("mbList", mbList);
			System.out.println("msg list found!!");
		} else {
			System.out.println("msg not found!!");
		}

		return "articleViewPage";
	}

	/* query page */
//	@RequestMapping(path = "", method = RequestMethod.GET)
//	public String getMsgBoardsByLocation(@RequestParam(name = "Location") String Location, Model m,
//			RedirectAttributes redirectAttributes) {
//
//		return "xxx";
//	}

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
