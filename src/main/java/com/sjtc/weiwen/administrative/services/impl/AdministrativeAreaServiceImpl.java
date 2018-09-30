package com.sjtc.weiwen.administrative.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.administrative.controllers.form.AdministrativeAreaVO;
import com.sjtc.weiwen.administrative.dao.AdministrativeAreaEntityMapper;
import com.sjtc.weiwen.administrative.dao.entity.AdministrativeAreaEntity;
import com.sjtc.weiwen.administrative.services.IAdministrativeAreaService;

@Service
public class AdministrativeAreaServiceImpl implements IAdministrativeAreaService {

	@Autowired
	private AdministrativeAreaEntityMapper administrativeAreaMapper;
	
	
	@Transactional
	@Override
	public BaseResult save(AdministrativeAreaVO vo) {
		BaseResult result = new BaseResult();
		AdministrativeAreaEntity areaEntity = new AdministrativeAreaEntity();
		if (StringUtil.isEmpty(vo.getOid())) {
			areaEntity.setOid(UUID.randomUUID().toString().replaceAll("-", ""));
			areaEntity.setTitle(vo.getTitle());
			areaEntity.setPOid(vo.getPOid());
			this.administrativeAreaMapper.insert(areaEntity);
		} else {
			areaEntity.setTitle(vo.getTitle());
			areaEntity.setPOid(vo.getPOid());
			this.administrativeAreaMapper.updateByPrimaryKeySelective(areaEntity);
		}
		return result;
	}


	@Transactional
	@Override
	public BaseResult delete(String oid) {
		BaseResult result = new BaseResult();
		this.administrativeAreaMapper.deleteByPrimaryKey(oid);
		return result;
	}


	@Override
	public PageInfo<AdministrativeAreaVO> getAreas(AdministrativeAreaVO vo, Integer limit, Integer offset) {
		Page<AdministrativeAreaEntity> page = PageHelper.startPage(offset, limit, true);
		List<AdministrativeAreaEntity> list = this.administrativeAreaMapper.selectAreaListPage(vo);
		if (list != null && list.size() != 0) {
			List<AdministrativeAreaVO> vos = new ArrayList<>();
			for (AdministrativeAreaEntity entity : list) {
				AdministrativeAreaVO areaVO = new AdministrativeAreaVO();
				areaVO.setOid(entity.getOid());
				areaVO.setTitle(entity.getTitle());
				areaVO.setPOid(entity.getPOid());
				vos.add(areaVO);
			}
			PageInfo<AdministrativeAreaVO> pageInfo = new PageInfo<>();
			pageInfo.setPageNum(page.getPageNum());
			pageInfo.setPageSize(page.getPageSize());
			pageInfo.setPages(page.getPages());
			pageInfo.setTotal(page.getTotal());
			pageInfo.setRows(vos);
			return pageInfo;
		}
		return null;
	}

	
	
	@Override
	public List<AdministrativeAreaVO> getChildrens(String pOid) {
		List<AdministrativeAreaEntity> list = this.administrativeAreaMapper.selectChildrensByOid(pOid);
		if (list != null && list.size() != 0) {
			List<AdministrativeAreaVO> vos = new ArrayList<>();
			for (AdministrativeAreaEntity entity : list) {
				AdministrativeAreaVO vo = new AdministrativeAreaVO();
				vo.setOid(entity.getOid());
				vo.setPOid(entity.getPOid());
				vo.setTitle(entity.getTitle());
				vo.setNodeId(entity.getOid());
				vo.setText(entity.getTitle());
				vo.setNodes(this.getChildrens(entity.getOid()));
				vos.add(vo);
				System.out.println(1111111);
			}
			return vos;
		}
		return null;
	}


	@Override
	public AdministrativeAreaVO getArea(String oid) {
		AdministrativeAreaEntity entity = this.administrativeAreaMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			AdministrativeAreaVO vo = new AdministrativeAreaVO();
			vo.setOid(entity.getOid());
			vo.setPOid(entity.getPOid());
			vo.setTitle(entity.getTitle());
			return vo;
		}
		return null;
	}


	@Override
	public List<AdministrativeAreaVO> getAreaParent(String pOid) {
		AdministrativeAreaEntity entity = this.administrativeAreaMapper.selectByPrimaryKey(pOid);
		if (entity != null) {
			List<AdministrativeAreaVO> vos = new ArrayList<>();
			AdministrativeAreaVO vo = new AdministrativeAreaVO();
			vo.setOid(entity.getOid());
			vo.setPOid(entity.getPOid());
			vo.setTitle(entity.getTitle());
			vo.setNodeId(entity.getOid());
			vo.setText(entity.getTitle());
			vo.setNodes(this.getChildrens(entity.getOid()));
			vos.add(vo);
			return vos;
		}
		
		return null;
	}


	@Override
	public List<String> getAreaOids(String oid) {
		return null;
	}

}
