package com.sjgj.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjgj.mapper.BookMapper;
import com.sjgj.mapper.FbookstoreMapper;
import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.Fbookstore;
import com.sjgj.pojo.Pstorebook;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.BookService;
import com.sjgj.service.PstorebookService;

@Controller
@RequestMapping("pstorebook")
public class PstorebookController {
	@Resource
	private PstorebookService pstorebookService;
	@Resource
	private FbookstoreMapper fbookstoreMapper;
	@Resource
	private BookMapper bookMapper;

/*	@RequestMapping("pstorebook_main.action")
	public String book_main() {
		return "frame/pstorebook_main";
	}

	@RequestMapping("pstorebook_left.action")
	public String book_left() {
		return "frame/pstorebook_left";
	}*/

	// 图书列表展示
	@RequestMapping("list.action")
	public String list(String pid, String buystore, Integer pageNo, Model model) {
		Pagination pagination = pstorebookService.selectpstorebookListByObjectQuery(pid, buystore, pageNo);
		model.addAttribute("pagination", pagination);
		List<Fbookstore> selectBookstore = fbookstoreMapper.selectBookstore();
		model.addAttribute("buystores",selectBookstore);
		// 查询条件回显
		model.addAttribute("pid", pid);
		model.addAttribute("buystore", buystore);
		model.addAttribute("pageNo", pageNo);
		return "product/pstorebooklist";
	}

	// 跳转到图书添加页面
	@RequestMapping("add.action")
	public String add(Model model) {
		model.addAttribute("books", bookMapper.selectfBook());
		List<Fbookstore> selectBookstore = fbookstoreMapper.selectBookstore();
		model.addAttribute("buystores",selectBookstore);
		
		return "product/pstorebookadd";
	}

	// 根据id更新图书信息
	@RequestMapping("update.action")
	public String update(Pstorebook pstorebook) {
		Fbook selectBookByid = bookMapper.selectBookByid(pstorebook.getBookid());
		
		pstorebook.setBookname(selectBookByid.getBookname());
		
		pstorebookService.updatePstorebookById(pstorebook);
		return "redirect:list.action";
	}

	// 跳转到编辑页面
	@RequestMapping("edit.action")
	public String edit(int id, Model model) {
		Pstorebook pstorebook = pstorebookService.selectPstorebookByid(id);
		model.addAttribute("books", bookMapper.selectfBook());
		List<Fbookstore> selectBookstore = fbookstoreMapper.selectBookstore();
		model.addAttribute("buystores",selectBookstore);
		model.addAttribute("pstorebook", pstorebook);
//		model.addAttribute("id", id);
		return "product/pstorebookedit";
	}

	// 添加图书
	@RequestMapping("save.action")
	public String addbook(Pstorebook pstorebook, Model model) {
		Fbook selectBookByid = bookMapper.selectBookByid(pstorebook.getBookid());
		pstorebook.setBookname(selectBookByid.getBookname());
		pstorebookService.insertPstorebook(pstorebook);
		// model.addAttribute();

		return "redirect:list.action";
	}

	// 删除单个图书
	@RequestMapping("deteleOne.action")
	public String deteleOne(int id) {
		pstorebookService.deletePstorebookByid(id);
		return "forward:list.action";
	}

//
//	@RequestMapping("querybname.action")
//	public void querybname(Book book, HttpServletResponse response) {
//		response.setContentType("application/json;charset=UTF-8");
//		List<Book> books = bookServcie.selectbnameBytype(book);
//		String json = JSONArray.fromObject(books).toString();
//		/* System.out.println(json); */
//		try {
//			response.getWriter().write(json);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
}
