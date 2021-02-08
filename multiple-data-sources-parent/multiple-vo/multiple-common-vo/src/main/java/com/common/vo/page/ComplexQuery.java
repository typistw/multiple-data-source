package com.common.vo.page;
/**
 * 复杂查询模型
 * @author herry.zhang
 *
 */
public class ComplexQuery {

	/**
	 * 实际参与的字段名称
	 */
	private String fieldName;
	
	/**
	 * 搜索类型
	 */
	private SearchType searchType;
	
	public ComplexQuery(String fieldName, SearchType searchType){
		this.fieldName=fieldName;
		this.searchType=searchType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	
	
	
}
