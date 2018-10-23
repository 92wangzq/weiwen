package com.sjtc.weiwen.system.services;

import java.util.List;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.system.controllers.form.DictionaryDataVO;
import com.sjtc.weiwen.system.controllers.form.DictionaryVO;

public interface ISystemDictionaryService {
	
	/**
	 * 增加字典类型
	 * @param dictionary
	 * @return
	 */
	BaseResult saveDictionary(DictionaryVO dictionary);
	
	/**
	 * 删除字典类型
	 * @param oid
	 * @return
	 */
	BaseResult deleteDictionary(String oid);
	
	/**
	 * 字典类型列表
	 * @param dictionary
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<DictionaryVO> getDictionarys(DictionaryVO dictionary, Integer limit, Integer offset);
	
	/**
	 * 保存字典数据
	 * @param dictionaryData
	 * @return
	 */
	BaseResult saveDictionaryData(DictionaryDataVO dictionaryData);
	
	/**
	 * 删除字典数据
	 * @param oid
	 * @return
	 */
	BaseResult deleteDictionaryData(String oid);
	
	/**
	 * 根据字典类型获取字典数据列表
	 * @param dictionaryValue
	 * @return
	 */
	List<DictionaryDataVO> getParentDictionaryDatas(String dictionaryValue);
	
	/**
	 * 根据字典数据父ID获取子级列表
	 * @param parentOid
	 * @return
	 */
	List<DictionaryDataVO> getChildDictionaryDatas(String parentOid);
	
	/**
	 * 查询字典数据信息
	 * @param oid
	 * @return
	 */
	DictionaryDataVO getDictionaryData(String oid);
	
}
