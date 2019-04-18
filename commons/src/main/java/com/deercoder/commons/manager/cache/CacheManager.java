package com.deercoder.commons.manager.cache;


import com.deercoder.commons.model.CacheModel;

/**
 * 缓存操作常用接口
 * @author dreamlu
 */
public interface CacheManager {

	/**
	 * 缓存
	 * CacheModel 时间可选
	 *
	 * @param key
	 * @param value
	 */
	public void set(Object key, CacheModel value);

	/**
	 * 根据key获得缓存
	 *
	 * @param key
	 * @return
	 */
	public CacheModel get(Object key);

	/**
	 * 缓存删除
	 *
	 * @param key
	 * @return
	 */
	public boolean delete(Object key);

	/**
	 * 模糊缓存删除
	 *
	 * @param keyPrex 模糊删除key
	 * @return
	 */
	public Long deletePrex(Object keyPrex);

	/**
	 * 验证是否存在, 存在则延长时间
	 * CacheModel 时间必填
	 *
	 * @param key
	 * @return
	 */
	public CacheModel check(Object key);
}
