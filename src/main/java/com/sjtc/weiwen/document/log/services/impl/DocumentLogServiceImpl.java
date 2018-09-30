package com.sjtc.weiwen.document.log.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sjtc.util.BaseResult;
import com.sjtc.weiwen.document.log.controllers.form.DocumentLogVO;
import com.sjtc.weiwen.document.log.dao.DocumentLogEntityMapper;
import com.sjtc.weiwen.document.log.dao.entity.DocumentLogEntity;
import com.sjtc.weiwen.document.log.services.IDocumentLogService;
import com.sjtc.weiwen.user.services.IUserService;

@Service
public class DocumentLogServiceImpl implements IDocumentLogService {

	@Autowired
	private DocumentLogEntityMapper documentLogMapper;
	@Autowired
	private IUserService userService;

	@Transactional
	@Override
	public BaseResult save(DocumentLogVO logVO) {
		DocumentLogEntity entity = new DocumentLogEntity();
		entity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
		entity.setDocumentOid(logVO.getDocument().getOid());
		entity.setUserOid(logVO.getUser().getOid());
		entity.setDownloadTime(new Date());
		this.documentLogMapper.insertSelective(entity);
		return new BaseResult();
	}

	@Override
	public List<DocumentLogVO> getDocumentLogs(String documentOid) {
		List<DocumentLogEntity> entitys = this.documentLogMapper.selectByDocumentOid(documentOid);
		if (entitys != null && entitys.size() > 0) {
			List<DocumentLogVO> vos = new ArrayList<>();
			for (DocumentLogEntity entity : entitys) {
				DocumentLogVO vo = new DocumentLogVO();
				vo.setOid(entity.getOid());
				vo.setUser(this.userService.getUser(entity.getUserOid()));
				vo.setDownloadTime(entity.getDownloadTime());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}
}
