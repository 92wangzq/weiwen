package com.sjtc.weiwen.file.services.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjtc.weiwen.file.controllers.form.FileVO;
import com.sjtc.weiwen.file.dao.FileEntityMapper;
import com.sjtc.weiwen.file.dao.entity.FileEntity;
import com.sjtc.weiwen.file.services.IFileService;

@Service
public class FileServiceImpl implements IFileService {

	@Autowired
	private FileEntityMapper fileMapper;

	@Override
	public void save(FileVO vo) {
		FileEntity entity = new FileEntity();
		entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setTitle(vo.getTitle());
		entity.setSize(vo.getSize());
		entity.setType(vo.getType());
		entity.setSuffix(vo.getSuffix());
		entity.setViewPath(vo.getViewPath());
		entity.setDownloadPath(vo.getDownloadPath());
		entity.setInsertTime(new Date());
		this.fileMapper.insertSelective(entity);
		vo.setOid(entity.getOid());
	}

	@Override
	public FileVO getFile(String oid) {
		FileEntity entity = this.fileMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			FileVO vo = new FileVO();
			vo.setOid(entity.getOid());
			vo.setTitle(entity.getTitle());
			vo.setSize(entity.getSize());
			vo.setType(entity.getType());
			vo.setSuffix(entity.getSuffix());
			vo.setViewPath(entity.getViewPath());
			vo.setDownloadPath(entity.getDownloadPath());
			return vo;
		}
		return null;
	}

	@Override
	public void download(String oid, HttpServletRequest req, HttpServletResponse res) {
		FileEntity entity = this.fileMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			String fileName = entity.getTitle();
			res.setHeader("content-type", "application/octet-stream");
			res.setContentType("application/octet-stream");
			res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			byte[] buff = new byte[1024];
			BufferedInputStream bis = null;
			OutputStream os = null;
			try {
				os = res.getOutputStream();
				bis = new BufferedInputStream(new FileInputStream(new File(entity.getDownloadPath())));
				int i = bis.read(buff);
				while (i != -1) {
					os.write(buff, 0, buff.length);
					os.flush();
					i = bis.read(buff);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("success");
		}
	}
}
