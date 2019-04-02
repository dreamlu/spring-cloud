package com.deercoder.commons.util.sql;

import com.deercoder.commons.api.GetInfo;
import com.deercoder.commons.api.MapData;
import com.deercoder.commons.api.Pager;
import com.deercoder.commons.lib.Lib;
import com.deercoder.commons.util.Util;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * des: 通用增删改查
 * des: 缓存待完善...
 * author: dreamlu
 */

public class CrudUtil {

	/**
	 * des: JpaRepository repository 属性不抽取, 防止并发引用, 全局问题
	 */
	// search
	@SuppressWarnings("Duplicates")
	public static Object search(Map<String, Object> params, Object data, JpaRepository repository) {
		try {
			// 创建 ExampleMatcher
			// 过滤查询条件, 但不会拦截返回结果
			ExampleMatcher exampleMatcher = ExampleMatcher.matching()
					// 忽略 id 和 createTime 字段。
					//.withIgnorePaths("id")
					// 忽略大小写。
					.withIgnoreCase()
					// 忽略为空字段。
					.withIgnoreNullValues();

			// 携带 ExampleMatcher。
			Example<Object> example = Example.of(data, exampleMatcher);

			// 分页查询，从 0 页开始查询 everyPage 个。
			Integer  clientPage;
			Integer  everyPage;
			Page page;

			// every参数判断
			// 返回所有, 不分页
			String every = (String) params.get("every");
			// 查询所有数据
			if ("all".equals(every)) {
				List content = repository.findAll(example, new Sort(Sort.Direction.DESC, "id"));
				return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, content);
			} else {
				// 分页查询，从 0 页开始查询 everyPage 个。
				clientPage = Integer.parseInt((String) params.get("clientPage"));
				everyPage  = Integer.parseInt((String) params.get("everyPage"));
				page = repository.findAll(example, PageRequest.of(clientPage - 1, everyPage, new Sort(Sort.Direction.DESC, "id")));
			}

			// 分页表
			List content = page.getContent();
			if (content.size() == 0) {
				return Lib.GetMapData(Lib.CodeNoResult, Lib.MsgNoResult);
			}

			// ignore参数判断v1
			// 反射原理
			// 返回数据拦截处理(将值赋值为null)
			/*String ignore = (String) params.get("ignore");
			if (ignore != null) {
				String[] ignores = ignore.split(",");
				for (Object data : content) {
					for (String field : ignores) {
						Util.setFieldValue(field, null, data);
					}
				}
			}*/

			// ignore参数判断v2
			// 第三方json包
			// 返回数据拦截处理(将字段直接删除)
			// 小bug, 将json中null字段同时删除
			// JSONObject jsonObject=new JSONObject(content);
			// JSONArray jsonArray= jsonObject.getJSONArray(null);
			String ignore = (String) params.get("ignore");
			if (ignore != null && ignore.length() != 0 && content.size() != 0) {
				String[]  ignores   = ignore.split(",");
				JSONArray jsonArray = new JSONArray(content);
				// Iterator<String> iterator = ignores
				for (String field : ignores) {
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonData = jsonArray.getJSONObject(i);//得到对象中的第i条记录
						jsonData.remove(field);
					}
				}
				content = jsonArray.toList();
			}


			// 总数量 替换 总页数
			// 针对一些接口需要统计总数量问题, 不必重写接口
			long sumPage = page.getTotalElements();//.getTotalPages();

			return new GetInfo<Object>(Lib.CodeSuccess, Lib.MsgSuccess, content, new Pager(clientPage, (int) sumPage, everyPage));
		} catch (IllegalArgumentException ex) {
			return Lib.GetMapData(Lib.CodeText, Lib.MsgArgsErr);
		} catch (EmptyResultDataAccessException ex) {
			return Lib.GetMapData(Lib.CodeText, Lib.MsgNoData);
		} catch (Exception e) {
			// 暂无数据
			if(e.getCause().getClass() == EntityNotFoundException.class){
				return Lib.MapNoResult;
			}

			throw e;
//			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}

	// get by id
	// create
	public static Object getById(Object id, JpaRepository repository) {

		try {
			Object data = repository.findById(id).get();
			return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, data);
		} catch (NoSuchElementException ex) {
			return Lib.GetMapData(Lib.CodeText, Lib.MsgNoData);
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}

	// delete
	public static MapData delete(Object id, JpaRepository repository) {

		try {
			repository.deleteById(id);
			return Lib.MapDelete;
		} catch (EmptyResultDataAccessException ex) {
			return Lib.GetMapData(Lib.CodeText, Lib.MsgNoData);
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}

	// update
	public static MapData update(Object data, JpaRepository repository) {
		try {
			Object udateData = repository.save(data);
			Object id        = Util.getFieldValue("id", udateData);
			if (id == null) {
				return Lib.GetMapData(Lib.CodeText, "id不能为空");
			}
			return Lib.MapUpdate;
		} catch (NoSuchElementException e) {
			return Lib.GetMapData(Lib.CodeSql, "条件值不存在");
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}

	// create
	// return id
	public static Object create(Object data, JpaRepository repository) {

		try {
			Object createData = repository.save(data);
			return Lib.GetMapData(Lib.CodeCreate, Lib.MsgCreate, new HashMap<String, Object>() {
				{
					put("id", Util.getFieldValue("id", createData));
				}
			});
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}
}
