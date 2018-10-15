package com.sjtc.weiwen.lawrelated.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sjtc.util.BaseResult;
import com.sjtc.util.PageInfo;
import com.sjtc.weiwen.lawrelated.controllers.form.CaseTypeStatisticalVO;
import com.sjtc.weiwen.lawrelated.controllers.form.LawRelatedVO;

public interface ILawRelatedService {

	/**
	 * 保存数据
	 * @param vo
	 * @return
	 */
	BaseResult save(LawRelatedVO vo);

	/**
	 * 删除
	 * @param oid
	 * @return
	 */
	BaseResult delete(String oid);

	/**
	 * 列表查询
	 * @param vo
	 * @param limit
	 * @param offset
	 * @return
	 */
	PageInfo<LawRelatedVO> getlawRelateds(LawRelatedVO vo, Integer limit, Integer offset);

	/**
	 * 详细信息
	 * @param oid
	 * @return
	 */
	LawRelatedVO getLawRelated(String oid);

	/**
	 * 导出报表
	 * @param vo
	 * @param response
	 */
	void downExcel(LawRelatedVO vo, HttpServletResponse response);

	/**
	 * 根据案件类型统计
	 * @return
	 */
	List<CaseTypeStatisticalVO> caseTypeStatistical();

	/**
	 * 案件类型统计饼状图
	 * @return
	 */
	List<CaseTypeStatisticalVO> getCaseTypeStatisticalDonut();

}
