package com.java;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUploadController {
	
	static String folderpath;
	private String path;
	private String filepath;
	private String temp;
	
	@RequestMapping("/fileform")
	public String showUploadForm()
	{
		return "fileform";
	}
	
	@RequestMapping(value="/uploadimage",method =RequestMethod.POST)
	public String fileupload(@RequestParam("profile") CommonsMultipartFile file, HttpSession session,Model model,
			HttpServletRequest request)
	{
		session =request.getSession();
		
		System.out.println(session);
		System.out.println(session.getCreationTime());
		System.out.println("file upload handler");
		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getStorageDescription());
		byte[] data = file.getBytes();
		path = session.getServletContext().getRealPath("/")+file.getOriginalFilename();
		filepath =path.replaceAll(".txt", "");
		
		
		folderpath = session.getServletContext().getRealPath("/");
		
		System.out.println(folderpath);
		System.out.println(path);
		try {
		FileOutputStream fos = new FileOutputStream(path);
		fos.write(data);
		fos.close();
		System.out.println("file uploaded");
		FetchReqResp fr = new FetchReqResp();
		fr.convertDatat(path);
		
		temp = fr.filename+".xls";
	//	String newData = path+ temp; 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("msg","Converted successfully..");
		model.addAttribute("filename", temp);
		model.addAttribute("folderpath",folderpath);
		
		
		
		return "filesuccess";
		
	
	}
	
	@RequestMapping("/redirect")
	public String backTOHome(HttpSession session)
	{
		session.invalidate();
		return "index";
	}
	
	
	

}
