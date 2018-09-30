package com.sjtc.weiwen.file.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sjtc.weiwen.file.controllers.form.FileVO;
import com.sjtc.weiwen.file.services.IFileService;

@RestController
public class FileController {
	
	@Autowired
	private IFileService fileService;

	@RequestMapping("/yup")
	@ResponseBody
	public ResponseEntity<FileVO> yup(@RequestParam("fileName") MultipartFile file) {
		FileVO vo = new FileVO();
		if (file.isEmpty()) {
			vo.setCode("-1");
			vo.setMessage("上传文件为空！");
			return new ResponseEntity<FileVO>(vo, HttpStatus.OK);
		}
		String fileName = file.getOriginalFilename();
		int size = (int)file.getSize();
		System.out.println(fileName + "--->" + size);
		String path = "f:/file/";
		File dest = new File(path + fileName);
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			vo.setCode("0");
			vo.setMessage("SUCCESS");
			vo.setTitle(fileName);
			vo.setSize(size);
			vo.setType(fileName.substring(fileName.lastIndexOf(".") + 1));
			vo.setSuffix(fileName.substring(fileName.lastIndexOf(".")));
			vo.setViewPath(dest.getPath());
			vo.setDownloadPath(dest.getPath());
			this.fileService.save(vo);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			vo.setCode("-1");
			vo.setMessage("上传文件为空！");
			return new ResponseEntity<FileVO>(vo, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			vo.setCode("-1");
			vo.setMessage("上传文件为空！");
			return new ResponseEntity<FileVO>(vo, HttpStatus.OK);
		}
		return new ResponseEntity<FileVO>(vo, HttpStatus.OK);
	}
}
