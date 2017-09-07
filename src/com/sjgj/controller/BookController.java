package com.sjgj.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.BookService;

@Controller
@RequestMapping("book")
public class BookController {
	@Resource
	private BookService bookServcie;

	@RequestMapping("book_main.action")
	public String book_main() {
		return "frame/book_main";
	}

	@RequestMapping("book_left.action")
	public String book_left() {
		return "frame/book_left";
	}

	// 图书列表展示
	@RequestMapping("list.action")
	public String list(String bookname, String booktype, Integer pageNo, Model model) {
		Pagination pagination = bookServcie.selectBrandListByObjectQuery(bookname, booktype, pageNo);
		model.addAttribute("pagination", pagination);

		// 查询条件回显
		model.addAttribute("bookname", bookname);
		model.addAttribute("booktype", booktype);
		model.addAttribute("pageNo", pageNo);
		return "product/list";
	}

	// 跳转到图书添加页面
	@RequestMapping("add.action")
	public String add() {
		return "product/add";
	}

	// 根据id更新图书信息
	@RequestMapping("update.action")
	public String update(Fbook book) {
		bookServcie.updateBookById(book);
		return "redirect:list.action";
	}

	// 跳转到编辑页面
	@RequestMapping("edit.action")
	public String edit(int bookid, Model model) {
		Fbook fbook = bookServcie.selectBookByid(bookid);
		model.addAttribute("fbook", fbook);
		return "product/edit";
	}

	// 添加图书
	@RequestMapping("save.action")
	public String addbook(Fbook fbook, Model model) {
		bookServcie.insertUser(fbook);
		// model.addAttribute();

		return "redirect:list.action";
	}

	// 删除单个图书
	@RequestMapping("deteleOne.action")
	public String deteleOne(int bookid) {
		bookServcie.deleteByid(bookid);
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
