package com.sjtc.weiwen.system.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.DictionaryDataVO;
import com.sjtc.weiwen.system.controllers.form.DictionaryVO;
import com.sjtc.weiwen.system.dao.SysDictionaryDataEntityMapper;
import com.sjtc.weiwen.system.dao.SysDictionaryEntityMapper;
import com.sjtc.weiwen.system.dao.entity.SysDictionaryDataEntity;
import com.sjtc.weiwen.system.dao.entity.SysDictionaryEntity;
import com.sjtc.weiwen.system.services.ISystemDictionaryService;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
	
	@Autowired
	private SysDictionaryEntityMapper sysDictionaryMapper;
	@Autowired
	private SysDictionaryDataEntityMapper sysDictionaryDataMapper;

	@Override
	public BaseResult saveDictionary(DictionaryVO dictionary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult deleteDictionary(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<DictionaryVO> getDictionarys(DictionaryVO dictionary, Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		return null;
	}

	private DictionaryVO getDictionary(String oid) {
		SysDictionaryEntity entity = this.sysDictionaryMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			DictionaryVO vo = new DictionaryVO();
			vo.setOid(entity.getOid());
			vo.setDictName(entity.getDictName());
			vo.setDictValue(entity.getDictValue());
			return vo;
		}
		return null;
	}
	
	@Override
	public BaseResult saveDictionaryData(DictionaryDataVO dictionaryData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult deleteDictionaryData(String oid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DictionaryDataVO> getParentDictionaryDatas(String dictionaryValue) {
		List<SysDictionaryDataEntity> list = this.sysDictionaryDataMapper.selectParentDictionaryDataByDiction(dictionaryValue);
		if (!CollectionUtils.isEmpty(list)) {
			List<DictionaryDataVO> vos = new ArrayList<>();
			for (SysDictionaryDataEntity entity : list) {
				DictionaryDataVO vo = new DictionaryDataVO();
				vo.setOid(entity.getOid());
				vo.setDictionary(this.getDictionary(entity.getDictOid()));
				vo.setParent(this.getDictionaryData(entity.getParentOid()));
				vo.setDictDataName(entity.getDictDataName());
				vo.setDictDataCode(entity.getDictDataCode());
				vo.setState(entity.getState());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

	@Override
	public DictionaryDataVO getDictionaryData(String oid) {
		SysDictionaryDataEntity entity = this.sysDictionaryDataMapper.selectByPrimaryKey(oid);
		if (entity != null) {
			DictionaryDataVO vo = new DictionaryDataVO();
			vo.setOid(entity.getOid());
			vo.setDictionary(this.getDictionary(entity.getDictOid()));
			vo.setParent(this.getDictionaryData(entity.getParentOid()));
			vo.setDictDataName(entity.getDictDataName());
			vo.setDictDataCode(entity.getDictDataCode());
			vo.setState(entity.getState());
			return vo;
		}
		return null;
	}

	@Override
	public List<DictionaryDataVO> getChildDictionaryDatas(String parentOid) {
		List<SysDictionaryDataEntity> list = this.sysDictionaryDataMapper.selectChildDictionaryDatasByParent(parentOid);
		if (!CollectionUtils.isEmpty(list)) {
			List<DictionaryDataVO> vos = new ArrayList<>();
			for (SysDictionaryDataEntity entity : list) {
				DictionaryDataVO vo = new DictionaryDataVO();
				vo.setOid(entity.getOid());
				vo.setDictionary(this.getDictionary(entity.getDictOid()));
				vo.setParent(this.getDictionaryData(entity.getParentOid()));
				vo.setDictDataName(entity.getDictDataName());
				vo.setDictDataCode(entity.getDictDataCode());
				vo.setState(entity.getState());
				vos.add(vo);
			}
			return vos;
		}
		return null;
	}

}
