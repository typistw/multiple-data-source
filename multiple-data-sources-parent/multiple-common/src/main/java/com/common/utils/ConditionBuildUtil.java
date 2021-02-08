package com.common.utils;

import com.common.annotation.QueryCondition;
import com.common.vo.page.ComplexQuery;
import com.common.vo.page.PageModel;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 条件查询工具构造类
 * @author herry.zhang
 *
 */
public class ConditionBuildUtil {
	
	public static final String EQ_CONDITION_TP="andTemplateEqualTo";//等于方法名称
	
	public static final String LIKE_CONDITION_TP="andTemplateLike";//like方法模板
	
	public static final String LT_CONDITION_TP="andTemplateLessThan";//小于等于模板
	
	public static final String LTE_CONDITION_TP="andTemplateLessThanOrEqualTo";//小于
	
	public static final String GTE_CONDITION_TP="andTemplateGreaterThanOrEqualTo";//大于等于
	
	public static final String GT_CONDITION_TP="andTemplateGreaterThan";//大于等于模板
	
	public static final String ISNULL_CONDITION_TP="andTemplateIsNull";//为空
	
	public static final String ISNOTNULL_CONDITION_TP="andTemplateIdIsNotNull";
	
	public static final String IN_CONDITION_TP="andTemplateIn";
	
	public static final Map<String,Class<?>>typeMap=new HashMap<String,Class<?>>();
	
	static{
		typeMap.put("class java.lang.Byte", Byte.class);
		typeMap.put("class java.lang.Integer", Integer.class);
		typeMap.put("class java.lang.String",String.class);
		typeMap.put("class java.lang.Long",Long.class);
		typeMap.put("class java.util.Date",Date.class);

	}
	
	/**
	 * 构造查询条件
	 * @param t
	 * @param entityClass
	 * @param searchParam
	 * @param searchTypeParam
	 * @return
	 */
	public static <T>T buildCondition(T t, Class<?> entityClass,
			Object searchParam,@SuppressWarnings("unchecked") Map<String,List<ComplexQuery>>...searchTypeParam) {
		List<Field> fieldListAll = new ArrayList<>() ;
		//声明的字段
		Field fields[]=searchParam.getClass().getDeclaredFields();
		Class fatherClass = searchParam.getClass().getSuperclass();
		if(fatherClass!=null){
			//获取父类的属性字段
			fieldListAll.addAll(Arrays.asList(fatherClass.getDeclaredFields()));
		}
		fieldListAll.addAll(Arrays.asList(fields));
		try {
		Object criteria= ReflectionUtils.invokeMethod(t,"createCriteria", null,null);//执行createCriteria方法
			for(Field field:fieldListAll){
				QueryCondition queryCondition=field.getAnnotation(QueryCondition.class);
				String fieldName=field.getName();
				ReflectionUtils.makeAccessible(field);
				Object fieldVal=ReflectionUtils.getFieldValue(searchParam, fieldName);//字段不为空 
				if((queryCondition!=null&&fieldVal!=null&&!StringUtils.isEmpty(fieldVal+""))||(searchTypeParam!=null&&searchTypeParam.length>0&&searchTypeParam[0].containsKey(fieldName))){//查询条件注解不为空时
						invokeConditionBuild(searchParam,criteria, queryCondition, fieldName, fieldVal, field.getType()+"",searchTypeParam);
				}
			}
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * 转换动态方法名称
	 * @param queryCondition
	 * @param fieldName
	 * @return
	 */
	private static void invokeConditionBuild(Object entity, Object criteria, QueryCondition queryCondition, String fieldName, Object fieldVal, String fieldType, Map<String,List<ComplexQuery>>...searchTypeParam){
		String eqMethodName=convertField(fieldName);
		try {
			boolean defaultQuery=true;
			if(searchTypeParam!=null&&searchTypeParam.length>0&&searchTypeParam[0].containsKey(fieldName)){
				defaultQuery=false;
			}
			if(defaultQuery){//采用字段上方注解的默认查询方式
				switch (queryCondition.searchType()) {
				case EQ:
					if(fieldVal!=null&&!StringUtils.isEmpty(""+fieldVal))
					ReflectionUtils.invokeMethod(criteria, EQ_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldVal});
					break;
				case LIKE:
					if(fieldVal!=null&&!StringUtils.isEmpty(""+fieldVal))
					ReflectionUtils.invokeMethod(criteria, LIKE_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{"%"+fieldVal+"%"});
					break;
				case ISNOTNULL:
					ReflectionUtils.invokeMethod(criteria, ISNOTNULL_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldVal});
					break;
				case ISNULL:
					ReflectionUtils.invokeMethod(criteria, ISNULL_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldVal});
					break;
				case LT:
					if(fieldVal!=null&&!StringUtils.isEmpty(""+fieldVal))
					ReflectionUtils.invokeMethod(criteria, LT_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldVal});
					break;
				case IN:
					String inFieldName=queryCondition.relateInField();
					ReflectionUtils.invokeMethod(criteria, IN_CONDITION_TP.replace("Template", inFieldName), new Class<?>[]{List.class}, new Object []{fieldVal});
				default:
					break;
				}
			}else{
				Map<String,List<ComplexQuery>>complexQueryParam=searchTypeParam[0];
				List<ComplexQuery> complexQuerys=complexQueryParam.get(fieldName);
				for(ComplexQuery complexQuery:complexQuerys){
					switch (complexQuery.getSearchType()) {
						case EQ:
							if(fieldVal!=null&&!StringUtils.isEmpty(""+fieldVal))
							ReflectionUtils.invokeMethod(criteria, EQ_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldVal});
							break;
						case LIKE:
							if(fieldVal!=null&&!StringUtils.isEmpty(""+fieldVal))
							ReflectionUtils.invokeMethod(criteria, LIKE_CONDITION_TP.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{"%"+fieldVal+"%"});
							break;
						case GT:
							dealForComplexQuery(criteria, entity, complexQuery, eqMethodName, fieldType, GT_CONDITION_TP,fieldName);
							break;
						case LT:
							dealForComplexQuery(criteria, entity, complexQuery, eqMethodName, fieldType, LT_CONDITION_TP,fieldName);
							break;
						case GTE:
							dealForComplexQuery(criteria, entity, complexQuery, eqMethodName, fieldType, GTE_CONDITION_TP,fieldName);
							break;
						case LTE:
							dealForComplexQuery(criteria, entity, complexQuery, eqMethodName, fieldType, LTE_CONDITION_TP,fieldName);
							break;
						default:
							break;
					}
				}
			}
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//加入条件
	}
	
	/**
	 * 处理 大于等于，大于，小于,小于等于这些查询条件
	 * @param criteria
	 * @param entity
	 * @param param
	 * @param eqMethodName
	 * @param fieldType
	 * @param methodTemplate
	 */
	private static void dealForComplexQuery(Object criteria, Object entity, ComplexQuery query, String eqMethodName, String fieldType, String methodTemplate, String fieldName){
			if(query!=null){
				try {
					Object fieldRealValue=ReflectionUtils.getFieldValue(entity, query.getFieldName());//字段不为空
					if(fieldRealValue!=null){
						ReflectionUtils.invokeMethod(criteria, methodTemplate.replace("Template", eqMethodName), new Class<?>[]{typeMap.get(fieldType+"")},new Object []{fieldRealValue});
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * 转换字段名称
	 * @param source
	 * @return
	 */
	private static String  convertField(String source){
    	String fistStr=(source.charAt(0)+"");
		return source.replaceFirst(fistStr, fistStr.toUpperCase());
    }
	
	/**
	 * 解析分页参数
	 * @param pageModel
	 * @param entityClass
	 * @return
	 */
	public static <T>T parsePageParam(PageModel pageModel, T entityClass){
		Integer start=(pageModel.getCurrentPage()-1)*pageModel.getPageSize();
		Integer limit=pageModel.getPageSize();
		ReflectionUtils.setFieldValue(entityClass, "limitStart",start);
		ReflectionUtils.setFieldValue(entityClass, "limitLength", limit);
		return entityClass;
	}

}
