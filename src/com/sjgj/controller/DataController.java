package com.sjgj.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sjgj.mapper.BookMapper;
import com.sjgj.mapper.FbookstoreMapper;
import com.sjgj.mapper.FdatatimeMapper;
import com.sjgj.mapper.FuserMapper;
import com.sjgj.mapper.FuserbuyMapper;
import com.sjgj.mapper.UserMapper;
import com.sjgj.mapper.XlhMapper;
import com.sjgj.pojo.Fbook;
import com.sjgj.pojo.Fbookstore;
import com.sjgj.pojo.Fdatatime;
import com.sjgj.pojo.Fuser;
import com.sjgj.pojo.FuserQuery;
import com.sjgj.pojo.Fuserbuy;
import com.sjgj.pojo.FuserbuyQuery;
import com.sjgj.pojo.Pstorebook;
import com.sjgj.pojo.User;
import com.sjgj.pojo.XlCode;
import com.sjgj.pojo.page.Pagination;
import com.sjgj.service.FuserService;
import com.sjgj.service.PstorebookService;
import com.sjgj.utils.MSMUtils;
import com.sjgj.utils.MailUtils;

@Controller
@RequestMapping("data")
public class DataController {
	
	@Resource
	private FdatatimeMapper fdatatimeMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private FuserMapper fuserMapper;
	
	@Resource
	private FuserService fuserService;
	@Resource
	private PstorebookService pstorebookService;
	
	@Resource
	private FuserbuyMapper fuserbuyMapper;
	@Resource
	private FbookstoreMapper fbookstoreMapper;
	@Resource
	private XlhMapper xlhMapper;
	@Resource
	private BookMapper bookMapper;
	@RequestMapping("data_main.action")
	public String data_main() {
		return "frame/data_main";
	}

	@RequestMapping("data_left.action")
	public String data_left() {
		return "frame/data_left";
	}
	
	@RequestMapping("scdata_add.action")
	public String scdata_add(Model model) {
		Fdatatime selectdatatimeByid = fdatatimeMapper.selectdatatimeByid(1);
		
		model.addAttribute("fdatatime", selectdatatimeByid.getDatatime());
		return "data/scdataadd";
	}
	
	@RequestMapping("appdata_add.action")
	public String appdata_add(Model model) {
		Fdatatime selectdatatimeByid = fdatatimeMapper.selectdatatimeByid(2);

		model.addAttribute("fdatatime", selectdatatimeByid.getDatatime());
		return "data/appadd";
	}
	// 导入商城数据接口2.0
	@RequestMapping("scdatainsert2.action")
	public String scdatainsert2(String csvUrl) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String commenttime = sdf.format(date);
		String returnurl=null;
		String ns=csvUrl.substring(csvUrl.lastIndexOf("/")+1);
		String p1s=ns.substring(8);
		String pid=p1s.substring(0, p1s.indexOf("."));
		try {
			CsvReader reader = new CsvReader(csvUrl, ',', Charset.forName("GBK"));
			String[] header = {};
			int i=0;
		
			while (reader.readRecord()) {
				
				// 把头保存起来
				if (reader.getCurrentRecord() == 0) {
					header = reader.getValues();
				}	
					
				String[] tmp = { reader.getValues()[0], reader.getValues()[1] };
				// 修改记录，并只写入第一个字段和第二字段
				if (!header[1].equals(tmp[1]) && ("".equals(tmp[1]) || tmp == null)) {
					tmp[1] = "空";
				} else {
					if (i>0) {
						
//					System.out.println(reader.getValues()[1].toString());//昵称
//						System.out.println(reader.getValues()[2].toString());//邮箱
//					System.out.println(reader.getValues()[13].toString()); //地区
//					System.out.println(reader.getValues()[16].toString()); //手机号
//					System.out.println(reader.getValues()[17].toString()); //订单时间
//					System.out.println(reader.getValues()[19].toString());  //购买图书名字
//					System.out.println(reader.getValues()[26].toString());   //书店
						Fbookstore selectBookstoreByname = fbookstoreMapper.selectBookstoreByname(reader.getValues()[26].toString());
						if (selectBookstoreByname!=null) {
							
						}
						else {
							Fbookstore fbookstore=new Fbookstore();
							fbookstore.setStorename(reader.getValues()[26].toString());
							fbookstoreMapper.insertBookstore(fbookstore);
						}
						
						returnurl=reader.getValues()[26].toString();
						if ("".equals(reader.getValues()[16].toString())||reader.getValues()[16].toString()==null) {
							
						}
						else {
							List<Fuser> fuser = fuserService.selectfuserByphone(reader.getValues()[16].toString().substring(1));
							String substring = reader.getValues()[17].toString().substring(0, 4);
							int number=Integer.parseInt(substring)+1;
							String finalString=number+"";
							if (fuser==null||fuser.size()<1) {    //买家信息不存在情况
								Fuser fuser1=new Fuser();
								fuser1.setArea(reader.getValues()[13].toString().substring(0, 8));
								if (reader.getValues()[2].toString().contains("@")) {
									fuser1.setEmail(reader.getValues()[2].toString());
								}
								else {
									
								}
								fuser1.setExamyear(finalString);
								fuser1.setEnglishtype("未确定");
								fuser1.setPhone(reader.getValues()[16].toString().substring(1));
								fuser1.setTname(reader.getValues()[1].toString());
								
//								fuser1.setExamyear(finalString);
								int insertfuser = fuserService.insertfuser(fuser1);
								List<Pstorebook> selectPstorebookBypidandstore = pstorebookService.selectPstorebookBypidandstore(pid, reader.getValues()[26].toString());
								if (selectPstorebookBypidandstore==null||selectPstorebookBypidandstore.size()<1) {
									
								}
								else {
									for (Pstorebook pstorebook : selectPstorebookBypidandstore) {
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(pstorebook.getBookid());
										fuserbuy1.setFuid(fuser1.getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(pstorebook.getBookid());
											fuserbuy.setFuid(fuser1.getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
											Fuser fuser3=new Fuser();
											fuser3.setEnglishtype(bookMapper.selectBookByid(pstorebook.getBookid()).getBooktype());
											fuser3.setExamyear(finalString);
											fuser3.setFuid(fuser1.getFuid());
											fuserService.updatallfuserByfuid(fuser3);
										}
											else {
			
										}
									}
									
								}
							
							}
							
							//买家信息已导入后台后的情况
							else {
								
								List<Pstorebook> selectPstorebookBypidandstore = pstorebookService.selectPstorebookBypidandstore(pid, reader.getValues()[26].toString());
								if (selectPstorebookBypidandstore==null||selectPstorebookBypidandstore.size()<1) {
									
								}
								else {
									for (Pstorebook pstorebook : selectPstorebookBypidandstore) {
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(pstorebook.getBookid());
										fuserbuy1.setFuid(fuser.get(0).getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(pstorebook.getBookid());
											fuserbuy.setFuid(fuser.get(0).getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
											Fuser fuser3=new Fuser();
											fuser3.setEnglishtype(bookMapper.selectBookByid(pstorebook.getBookid()).getBooktype());
											fuser3.setExamyear(finalString);
											fuser3.setFuid(fuser.get(0).getFuid());
											fuserService.updatallfuserByfuid(fuser3);
										}
											else {
			
										}
									}
									
								}
								
							}

						
						}
						
					}
				
				}
				i=i+1;
			}
			System.out.println(i);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Fdatatime fdatatime1=new Fdatatime();
		fdatatime1.setId(1);
		Date date =new Date();
		fdatatime1.setDatatime(sdf.format(date));
		fdatatimeMapper.updateapptimeById(fdatatime1);
		
		Fdatatime fdatatime2=new Fdatatime();
		fdatatime2.setId(3);
		fdatatime2.setDatatime(sdf.format(date));
		fdatatimeMapper.updateapptimeById(fdatatime2);
		
			
		return "forward:list.action?buystore="+returnurl;
	}
	
	
	// 导入商城数据接口1.0
	@RequestMapping("scdatainsert.action")
	public String scdatainsert(String csvUrl) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String commenttime = sdf.format(date);
		String returnurl=null;
		try {
			CsvReader reader = new CsvReader(csvUrl, ',', Charset.forName("GBK"));
			String[] header = {};
			int i=0;
		
			while (reader.readRecord()) {
				
				// 把头保存起来
				if (reader.getCurrentRecord() == 0) {
					header = reader.getValues();
				}	
					
				String[] tmp = { reader.getValues()[0], reader.getValues()[1] };
				// 修改记录，并只写入第一个字段和第二字段
				if (!header[1].equals(tmp[1]) && ("".equals(tmp[1]) || tmp == null)) {
					tmp[1] = "空";
				} else {
					if (i>0) {
						
//					System.out.println(reader.getValues()[1].toString());//昵称
//						System.out.println(reader.getValues()[2].toString());//邮箱
//					System.out.println(reader.getValues()[13].toString()); //地区
//					System.out.println(reader.getValues()[16].toString()); //手机号
//					System.out.println(reader.getValues()[17].toString()); //订单时间
//					System.out.println(reader.getValues()[19].toString());  //购买图书名字
//					System.out.println(reader.getValues()[26].toString());   //书店
						returnurl=reader.getValues()[26].toString();
						if ("".equals(reader.getValues()[16].toString())||reader.getValues()[16].toString()==null) {
							
						}
						else {
							List<Fuser> fuser = fuserService.selectfuserByphone(reader.getValues()[16].toString().substring(1));
							String substring = reader.getValues()[17].toString().substring(0, 4);
							int number=Integer.parseInt(substring)+1;
							String finalString=number+"";
							if (fuser==null||fuser.size()<1) {    //买家信息不存在情况
								Fuser fuser1=new Fuser();
								fuser1.setArea(reader.getValues()[13].toString().substring(0, 8));
								if (reader.getValues()[2].toString().contains("@")) {
									fuser1.setEmail(reader.getValues()[2].toString());
								}
								else {
									
								}
								fuser1.setExamyear(finalString);
								fuser1.setEnglishtype("未确定");
								fuser1.setPhone(reader.getValues()[16].toString().substring(1));
								fuser1.setTname(reader.getValues()[1].toString());
								
//								fuser1.setExamyear(finalString);
								int insertfuser = fuserService.insertfuser(fuser1);
								if (reader.getValues()[19].toString().contains("基础版")) {
									
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("未确定");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("基础版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
								}
								if (reader.getValues()[19].toString().contains("珍藏版")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("珍藏版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
										
									}
										else {
									
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
										}
								}
								if (reader.getValues()[19].toString().contains("精编")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("精编");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
								}
								if (reader.getValues()[19].toString().contains("基础训练")) {
										
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("基础训练");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									}
								if (reader.getValues()[19].toString().contains("提高冲刺")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("提高冲刺");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									
								}
								if (reader.getValues()[19].toString().contains("写作高分突破")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("写作高分突破");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									}
								if (reader.getValues()[19].toString().contains("英语（一）最后预测5套题")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("英语（一）最后预测5套题");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("英语（二）最后预测5套题")) {
									
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("英语（二）最后预测5套题");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("理解80篇")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("理解80篇");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("经典版")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser1.getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("经典版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser1.getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser1.getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
								}
								if (reader.getValues()[19].toString().contains("试卷版")) {
									if (reader.getValues()[19].toString().contains("基础试卷版")) {
										Fuser fuser3=new Fuser();
										fuser3.setEnglishtype("未确定");
										fuser3.setExamyear(finalString);
										fuser3.setFuid(fuser1.getFuid());
										fuserService.updatallfuserByfuid(fuser3);
										Fbook fbook1=new Fbook();
										fbook1.setBookname("基础试卷版");
										fbook1.setBookyear(finalString);
										Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
											
										}
										else {
											
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(selectBookByyearandname.getBookid());
										fuserbuy1.setFuid(fuser1.getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(selectBookByyearandname.getBookid());
											fuserbuy.setFuid(fuser1.getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
										}
											else {
											
										}
										}
									}
									 if (reader.getValues()[19].toString().contains("经典试卷版")) {
										Fuser fuser3=new Fuser();
										fuser3.setEnglishtype("英语二");
										fuser3.setExamyear(finalString);
										fuser3.setFuid(fuser1.getFuid());
										fuserService.updatallfuserByfuid(fuser3);
										Fbook fbook1=new Fbook();
										fbook1.setBookname("经典试卷版");
										fbook1.setBookyear(finalString);
										Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
											
										}
										else {
											
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(selectBookByyearandname.getBookid());
										fuserbuy1.setFuid(fuser1.getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(selectBookByyearandname.getBookid());
											fuserbuy.setFuid(fuser1.getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
										}
											else {
											
										}
										}
									}
									 if ((!reader.getValues()[19].toString().contains("基础试卷版"))&&(!reader.getValues()[19].toString().contains("经典试卷版"))) {
											Fuser fuser3=new Fuser();
											fuser3.setEnglishtype("英语一");
											fuser3.setExamyear(finalString);
											fuser3.setFuid(fuser1.getFuid());
											fuserService.updatallfuserByfuid(fuser3);
											Fbook fbook1=new Fbook();
											fbook1.setBookname("历年考研英语真题解析及复习思路（试卷版）");
											fbook1.setBookyear(finalString);
											Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
											if (selectBookByyearandname==null) {
												
											}
											else {
												
											Fuserbuy fuserbuy1=new Fuserbuy();
											fuserbuy1.setBookid(selectBookByyearandname.getBookid());
											fuserbuy1.setFuid(fuser1.getFuid());
											List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
											if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
												Fuserbuy fuserbuy=new Fuserbuy();
												fuserbuy.setBookid(selectBookByyearandname.getBookid());
												fuserbuy.setFuid(fuser1.getFuid());
												fuserbuy.setBuystore(reader.getValues()[26].toString());
												fuserbuy.setBuytime(reader.getValues()[17].toString());
												fuserbuyMapper.insertfuserbuy(fuserbuy);
											}
												else {
												
											}
											}
										}
								}
							
							
							
							}
							
							//买家信息已导入后台后的情况
							else {
								
									if (reader.getValues()[19].toString().contains("基础版")) {
									
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("未确定");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("基础版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
								}
								if (reader.getValues()[19].toString().contains("珍藏版")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("珍藏版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
										
									}
										else {
									
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
										}
								}
								if (reader.getValues()[19].toString().contains("精编")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("精编");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
										else {
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
										}
								}
								if (reader.getValues()[19].toString().contains("基础训练")) {
										
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("基础训练");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									}
								if (reader.getValues()[19].toString().contains("提高冲刺")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("提高冲刺");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									
								}
								if (reader.getValues()[19].toString().contains("写作高分突破")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("写作高分突破");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
									}
								if (reader.getValues()[19].toString().contains("英语（一）最后预测5套题")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语一");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("英语（一）最后预测5套题");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("英语（二）最后预测5套题")) {
									
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("英语（二）最后预测5套题");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("理解80篇")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("理解80篇");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
									
								}
								if (reader.getValues()[19].toString().contains("经典版")) {
									Fuser fuser3=new Fuser();
									fuser3.setEnglishtype("英语二");
									fuser3.setExamyear(finalString);
									fuser3.setFuid(fuser.get(0).getFuid());
									fuserService.updatallfuserByfuid(fuser3);
									Fbook fbook1=new Fbook();
									fbook1.setBookname("经典版");
									fbook1.setBookyear(finalString);
									Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
									if (selectBookByyearandname==null) {
										
									}
									else {
										
									Fuserbuy fuserbuy1=new Fuserbuy();
									fuserbuy1.setBookid(selectBookByyearandname.getBookid());
									fuserbuy1.setFuid(fuser.get(0).getFuid());
									List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
									if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
										Fuserbuy fuserbuy=new Fuserbuy();
										fuserbuy.setBookid(selectBookByyearandname.getBookid());
										fuserbuy.setFuid(fuser.get(0).getFuid());
										fuserbuy.setBuystore(reader.getValues()[26].toString());
										fuserbuy.setBuytime(reader.getValues()[17].toString());
										fuserbuyMapper.insertfuserbuy(fuserbuy);
									}
										else {
										
									}
									}
								}
								if (reader.getValues()[19].toString().contains("试卷版")) {
									if (reader.getValues()[19].toString().contains("基础试卷版")) {
										Fuser fuser3=new Fuser();
										fuser3.setEnglishtype("未确定");
										fuser3.setExamyear(finalString);
										fuser3.setFuid(fuser.get(0).getFuid());
										fuserService.updatallfuserByfuid(fuser3);
										Fbook fbook1=new Fbook();
										fbook1.setBookname("基础试卷版");
										fbook1.setBookyear(finalString);
										Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
											
										}
										else {
											
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(selectBookByyearandname.getBookid());
										fuserbuy1.setFuid(fuser.get(0).getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(selectBookByyearandname.getBookid());
											fuserbuy.setFuid(fuser.get(0).getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
										}
											else {
											
										}
										}
									}
									 if (reader.getValues()[19].toString().contains("经典试卷版")) {
										Fuser fuser3=new Fuser();
										fuser3.setEnglishtype("英语二");
										fuser3.setExamyear(finalString);
										fuser3.setFuid(fuser.get(0).getFuid());
										fuserService.updatallfuserByfuid(fuser3);
										Fbook fbook1=new Fbook();
										fbook1.setBookname("经典试卷版");
										fbook1.setBookyear(finalString);
										Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
										if (selectBookByyearandname==null) {
											
										}
										else {
											
										Fuserbuy fuserbuy1=new Fuserbuy();
										fuserbuy1.setBookid(selectBookByyearandname.getBookid());
										fuserbuy1.setFuid(fuser.get(0).getFuid());
										List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
										if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
											Fuserbuy fuserbuy=new Fuserbuy();
											fuserbuy.setBookid(selectBookByyearandname.getBookid());
											fuserbuy.setFuid(fuser.get(0).getFuid());
											fuserbuy.setBuystore(reader.getValues()[26].toString());
											fuserbuy.setBuytime(reader.getValues()[17].toString());
											fuserbuyMapper.insertfuserbuy(fuserbuy);
										}
											else {
											
										}
										}
									}
									 if ((!reader.getValues()[19].toString().contains("基础试卷版"))&&(!reader.getValues()[19].toString().contains("经典试卷版"))) {
											Fuser fuser3=new Fuser();
											fuser3.setEnglishtype("英语一");
											fuser3.setExamyear(finalString);
											fuser3.setFuid(fuser.get(0).getFuid());
											fuserService.updatallfuserByfuid(fuser3);
											Fbook fbook1=new Fbook();
											fbook1.setBookname("历年考研英语真题解析及复习思路（试卷版）");
											fbook1.setBookyear(finalString);
											Fbook selectBookByyearandname = bookMapper.selectBookByyearandname(fbook1);
											if (selectBookByyearandname==null) {
												
											}
											else {
												
											Fuserbuy fuserbuy1=new Fuserbuy();
											fuserbuy1.setBookid(selectBookByyearandname.getBookid());
											fuserbuy1.setFuid(fuser.get(0).getFuid());
											List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
											if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
												Fuserbuy fuserbuy=new Fuserbuy();
												fuserbuy.setBookid(selectBookByyearandname.getBookid());
												fuserbuy.setFuid(fuser.get(0).getFuid());
												fuserbuy.setBuystore(reader.getValues()[26].toString());
												fuserbuy.setBuytime(reader.getValues()[17].toString());
												fuserbuyMapper.insertfuserbuy(fuserbuy);
											}
												else {
												
											}
											}
										}
								}
							
								
							}

							Fbookstore selectBookstoreByname = fbookstoreMapper.selectBookstoreByname(reader.getValues()[26].toString());
							if (selectBookstoreByname!=null) {
								
							}
							else {
								Fbookstore fbookstore=new Fbookstore();
								fbookstore.setStorename(reader.getValues()[26].toString());
								fbookstoreMapper.insertBookstore(fbookstore);
							}
						}
						
					}
				
				}
				i=i+1;
			}
			System.out.println(i);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Fdatatime fdatatime1=new Fdatatime();
		fdatatime1.setId(1);
		Date date =new Date();
		fdatatime1.setDatatime(sdf.format(date));
		fdatatimeMapper.updateapptimeById(fdatatime1);
		
		Fdatatime fdatatime2=new Fdatatime();
		fdatatime2.setId(3);
		fdatatime2.setDatatime(sdf.format(date));
		fdatatimeMapper.updateapptimeById(fdatatime2);
		
			
		return "forward:list.action?buystore="+returnurl;
	}
	

	// 导入app数据接口
	@RequestMapping("appdatainsert.action")
	public String appdatainsert() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String commenttime = sdf.format(date);
//			Fuser selectlastfuser = fuserService.selectlastfuser();
//			int selectUserbyphone = userMapper.selectUserbyphone(selectlastfuser.getPhone());
			List<User> users = userMapper.selectUser(25000);
			for (User user : users) {
				if (user.getPhone()==null || user.getPhone().equals("")) {
					
				}
				else {
					List<Fuser> fuser = fuserService.selectfuserByphone(user.getPhone());
					if (fuser==null||fuser.size()<1) {
						Fuser fuser1=new Fuser();
						fuser1.setArea(user.getProvince());
						fuser1.setEmail(user.getEmail());
						fuser1.setPhone(user.getPhone());
						fuser1.setTname(user.getNickName());
						
						int insertfuser = fuserService.insertfuser(fuser1);
						
						List<XlCode> selectuserbuybyUid = xlhMapper.selectuserbuybyUid(user.getUid());
						if (selectuserbuybyUid!=null&&selectuserbuybyUid.size()>0) {
							
							
							for (XlCode xlCode : selectuserbuybyUid) {
								Fuser fuser3=new Fuser();
								Fbook selectBookByname = bookMapper.selectBookByname(xlCode.getBname());
								if (xlCode.getBname().contains("基础版")||xlCode.getBname().contains("基础试卷版")) {
								
									fuser3.setEnglishtype("未确定");
								}
								else {
									fuser3.setEnglishtype(selectBookByname.getBooktype());
								}
								
								fuser3.setExamyear(selectBookByname.getBookyear());
//								int insertfuser = fuserService.insertfuser(fuser1);
								fuser3.setFuid(fuser1.getFuid());
								fuserService.updatallfuserByfuid(fuser3);
								Fuserbuy fuserbuy1=new Fuserbuy();
								fuserbuy1.setFuid(fuser1.getFuid());
								fuserbuy1.setBookid(selectBookByname.getBookid());
								List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
								if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
									Fuserbuy fuserbuy=new Fuserbuy();
									fuserbuy.setBookid(selectBookByname.getBookid());
									fuserbuy.setFuid(fuser1.getFuid());
									fuserbuy.setBuystore("世纪高教图书专营店");
									fuserbuy.setBuytime(xlCode.getRegistertime());
									fuserbuyMapper.insertfuserbuy(fuserbuy);
								}
								else {
									
								}
								
							}
						}
						else {
							Fuser fuser2=new Fuser();
							
							fuser2.setEnglishtype("未确定");
							String substring = sdf.format(user.getRegisterTime()).substring(0, 4);
							int number=Integer.parseInt(substring)+1;
							String finalString=number+"";
							fuser2.setExamyear(finalString);
							fuser2.setFuid(fuser1.getFuid());
							fuserService.updatallfuserByfuid(fuser2);
						}
					
						
//						fuserService.insertfuser(fuser);
					}
					
					
					
					//这里面学注册过的
					else {
						//这里面学注册过的
//						Fuser fuser2=new Fuser();
						List<XlCode> selectuserbuybyUid = xlhMapper.selectuserbuybyUid(user.getUid());
						if (selectuserbuybyUid!=null&&selectuserbuybyUid.size()>0) {
							
							
							for (XlCode xlCode : selectuserbuybyUid) {
								Fbook selectBookByname = bookMapper.selectBookByname(xlCode.getBname());
								if (xlCode.getBname().contains("基础版")||xlCode.getBname().contains("基础试卷版")) {
								
									fuser.get(0).setEnglishtype("未确定");
								}
								else {
									fuser.get(0).setEnglishtype(selectBookByname.getBooktype());
								}
								
								fuser.get(0).setExamyear(selectBookByname.getBookyear());
								fuserService.updatallfuserByfuid(fuser.get(0));
								Fuserbuy fuserbuy1=new Fuserbuy();
								fuserbuy1.setFuid(fuser.get(0).getFuid());
								fuserbuy1.setBookid(selectBookByname.getBookid());
								List<Fuserbuy> selectfuserbuyBybookidandfuid = fuserbuyMapper.selectfuserbuyBybookidandfuid(fuserbuy1);
								if (selectfuserbuyBybookidandfuid==null||selectfuserbuyBybookidandfuid.size()<1) {
									Fuserbuy fuserbuy=new Fuserbuy();
									fuserbuy.setBookid(selectBookByname.getBookid());
									fuserbuy.setFuid(fuser.get(0).getFuid());
									fuserbuy.setBuystore("世纪高教图书专营店");
									fuserbuy.setBuytime(xlCode.getRegistertime());
									fuserbuyMapper.insertfuserbuy(fuserbuy);
								}
								else {
									
								}
								
							}
						}
						else {
//							fuser.setEnglishtype("未确定");
//							String substring = sdf.format(user.getRegisterTime()).substring(0, 4);
//							int number=Integer.parseInt(substring)+1;
//							String finalString=number+"";
//							fuser.setExamyear(finalString);
//							fuserService.updatallfuserByfuid(fuser);
						}
					}
				}
				
			}
			Fdatatime fdatatime1=new Fdatatime();
			fdatatime1.setId(2);
			Date date =new Date();
			fdatatime1.setDatatime(sdf.format(date));
			fdatatimeMapper.updateapptimeById(fdatatime1);
			
			Fdatatime fdatatime2=new Fdatatime();
			fdatatime2.setId(3);
			fdatatime2.setDatatime(sdf.format(date));
			fdatatimeMapper.updateapptimeById(fdatatime2);
			
			
			Fbookstore selectBookstoreByname = fbookstoreMapper.selectBookstoreByname("世纪高教图书专营店");
			if (selectBookstoreByname!=null) {
				
			}
			else {
				Fbookstore fbookstore=new Fbookstore();
				fbookstore.setStorename("世纪高教图书专营店");
				fbookstoreMapper.insertBookstore(fbookstore);
			}
			
		return "redirect:list.action";
	}

	
	
	
	
	
	// 用户列表展示
	@RequestMapping("list.action")
	public String list(String phone, String englishtype, String examyear,String area,Integer bookid,String buystore, Integer pageNo, Model model) {
	Pagination pagination = fuserService.selectFuserListByObjectQuery(phone, englishtype, examyear, area,bookid,buystore,pageNo);
		model.addAttribute("pagination", pagination);
		Fdatatime selectdatatimeByid = fdatatimeMapper.selectdatatimeByid(3);
		
		model.addAttribute("fdatatime", selectdatatimeByid.getDatatime());
		model.addAttribute("books", bookMapper.selectfBook());
		List<Fbookstore> selectBookstore = fbookstoreMapper.selectBookstore();
		model.addAttribute("buystores",selectBookstore);
		// 查询条件回显
		model.addAttribute("bookid", bookid);
		model.addAttribute("buystore", buystore);
		model.addAttribute("phone", phone);
		model.addAttribute("englishtype", englishtype);
		model.addAttribute("area", area);
		model.addAttribute("examyear", examyear);
		model.addAttribute("pageNo", pageNo);
		return "data/datalist";
	}


	// 跳转到编辑页面
	@RequestMapping("edit.action")
	public String edit(int fuid, Model model) {
		List<FuserbuyQuery> list1=new ArrayList<FuserbuyQuery>();
		List<Fuserbuy> list = fuserbuyMapper.selectfuserbuyByfuid(fuid);
		if (list==null||list.size()<1) {
			model.addAttribute("buystore", "世纪高教图书专营店");
		}
		else {
			for (Fuserbuy fuserbuy : list) {
				FuserbuyQuery fuserbuyquery=new FuserbuyQuery();
				fuserbuyquery.setBookname(bookMapper.selectBookByid(fuserbuy.getBookid()).getBookname());
				fuserbuyquery.setBooktype(bookMapper.selectBookByid(fuserbuy.getBookid()).getBooktype());
				fuserbuyquery.setBuystore(fuserbuy.getBuystore());
				fuserbuyquery.setBuytime(fuserbuy.getBuytime());
				list1.add(fuserbuyquery);
			}
			model.addAttribute("buystore", list.get(0).getBuystore());
		}
		model.addAttribute("fuid", fuid);
		
		model.addAttribute("fuserbuyquerys", list1);
		return "data/userbuy";
	}



	// 删除单个
	@RequestMapping("deteleOne.action")
	public String deteleOne(int fuid) {

		
		fuserService.deleteByid(fuid);
		
		fuserbuyMapper.deleteuserbuyByfuid(fuid);
		
		return "forward:list.action?buystore=世纪高教图书专营店";
	}
	
	// 跳转到发送短息页面
	@RequestMapping("tosendlist.action")
	public String tosendlist(String phone, String englishtype, String examyear, String area,Integer bookid,String buystore, Model model) {
		FuserQuery fuserQuery = new FuserQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(phone != null){
			fuserQuery.setPhone(phone);
			params.append("phone=").append(phone);
		}
		if(englishtype != null){
			fuserQuery.setEnglishtype(englishtype);
			params.append("&englishtype=").append(englishtype);  // 
		}
		if(examyear != null){
			fuserQuery.setExamyear(examyear);
			params.append("&examyear=").append(examyear);  // 
		}
		if(area != null){
			fuserQuery.setArea(area);
			params.append("&area=").append(area);  // 
		}
		if(bookid != null){
			fuserQuery.setBookid(bookid);
			params.append("&bookid=").append(bookid);  // 
		}
		if(buystore != null){
			fuserQuery.setBuystore(buystore);
			params.append("&buystore=").append(buystore);  // 
		}
		// 查询条件回显
		int totalCount =fuserMapper.selectfuserCountByObjectQuery(fuserQuery);// 总条数
		model.addAttribute("phone", phone);
		model.addAttribute("englishtype", englishtype);
		model.addAttribute("area", area);
		model.addAttribute("examyear", examyear);
		model.addAttribute("totlecount", totalCount);
		model.addAttribute("bookid", bookid);
		model.addAttribute("buystore", buystore);
		return "data/send";
	}
	// 跳转到发送邮件页面
		@RequestMapping("tosendeamillist.action")
		public String tosendeamillist(String phone, String englishtype, String examyear, String area,Integer bookid,String buystore, Model model) {
			FuserQuery fuserQuery = new FuserQuery(); // 封装查询条件的query对象
			StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
			if(phone != null){
				fuserQuery.setPhone(phone);
				params.append("phone=").append(phone);
			}
			if(englishtype != null){
				fuserQuery.setEnglishtype(englishtype);
				params.append("&englishtype=").append(englishtype);  // 
			}
			if(examyear != null){
				fuserQuery.setExamyear(examyear);
				params.append("&examyear=").append(examyear);  // 
			}
			if(area != null){
				fuserQuery.setArea(area);
				params.append("&area=").append(area);  // 
			}
			if(bookid != null){
				fuserQuery.setBookid(bookid);
				params.append("&bookid=").append(bookid);  // 
			}
			if(buystore != null){
				fuserQuery.setBuystore(buystore);
				params.append("&buystore=").append(buystore);  // 
			}
			// 查询条件回显
			int totalCount =fuserMapper.selectfuserCountByObjectQuery(fuserQuery);// 总条数
			model.addAttribute("phone", phone);
			model.addAttribute("englishtype", englishtype);
			model.addAttribute("area", area);
			model.addAttribute("examyear", examyear);
			model.addAttribute("totlecount", totalCount);
			model.addAttribute("bookid", bookid);
			model.addAttribute("buystore", buystore);
			return "data/sendemail";
		}

	// 发送短信
	@RequestMapping("sendmsm.action")
	public String sendmsm(String phone, String englishtype, String examyear, String area,Integer bookid,String buystore,String content) {
		FuserQuery fuserQuery = new FuserQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(phone != null){
			fuserQuery.setPhone(phone);
			params.append("phone=").append(phone);
		}
		if(englishtype != null){
			fuserQuery.setEnglishtype(englishtype);
			params.append("&englishtype=").append(englishtype);  // 
		}
		if(examyear != null){
			fuserQuery.setExamyear(examyear);
			params.append("&examyear=").append(examyear);  // 
		}
		if(area != null){
			fuserQuery.setArea(area);
			params.append("&area=").append(area);  // 
		}
		if(bookid != null){
			fuserQuery.setBookid(bookid);
			params.append("&bookid=").append(bookid);  // 
		}
		if(buystore != null){
			fuserQuery.setBuystore(buystore);
			params.append("&buystore=").append(buystore);  // 
		}
		List<Fuser> list = fuserMapper.selectfuserListByObjectQuery(fuserQuery) ;// 结果集
		System.out.println(list.size());
		for (Fuser fuser : list) {
			
			try {
				MSMUtils.SendSms(fuser.getPhone(),content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "forward:list.action?buystore="+buystore;
	}

	// 发送邮件
	@RequestMapping("sendeamil.action")
	public String sendeamil(String phone, String englishtype, String examyear, String area,Integer bookid,String buystore,String content,String title) {
		FuserQuery fuserQuery = new FuserQuery(); // 封装查询条件的query对象
		StringBuilder params = new StringBuilder(); // 封装分页工具栏参数
		if(phone != null){
			fuserQuery.setPhone(phone);
			params.append("phone=").append(phone);
		}
		if(englishtype != null){
			fuserQuery.setEnglishtype(englishtype);
			params.append("&englishtype=").append(englishtype);  // 
		}
		if(examyear != null){
			fuserQuery.setExamyear(examyear);
			params.append("&examyear=").append(examyear);  // 
		}
		if(area != null){
			fuserQuery.setArea(area);
			params.append("&area=").append(area);  // 
		}
		if(bookid != null){
			fuserQuery.setBookid(bookid);
			params.append("&bookid=").append(bookid);  // 
		}
		if(buystore != null){
			fuserQuery.setBuystore(buystore);
			params.append("&buystore=").append(buystore);  // 
		}
		List<Fuser> list = fuserMapper.selectfuserListByObjectQuery(fuserQuery) ;// 结果集
		System.out.println(list.size());
		for (Fuser fuser : list) {
			if (fuser.getEmail()==null||"".equals(fuser.getEmail())) {
				
			}
			else {
				try {
					MailUtils cn = new MailUtils();
					cn.setAddress("shijigaojiao2016@163.com", fuser.getEmail(), title);
					cn.send("smtp.163.com", "shijigaojiao2016@163.com", "sjgj2016",content);
//					MSMUtils.SendSms(fuser.getPhone(), content);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
//		return "redirect:list.action?buystore=世纪高教图书专营店";
		return "forward:list.action?buystore="+buystore;
	}
}
