package com.deercoder.shop.service.impl;

import com.deercoder.commons.lib.Lib;
import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.manager.cache.impl.RedisManager;
import com.deercoder.commons.model.CacheModel;
import com.deercoder.commons.model.TokenModel;
import com.deercoder.commons.util.sql.CrudUtil;
import com.deercoder.shop.dao.ShopDao;
import com.deercoder.shop.model.Shop;
import com.deercoder.shop.model.ShopDe;
import com.deercoder.shop.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.deercoder.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.deercoder.commons.lib.Constants.TOKEN_MINUTE;
import static com.deercoder.commons.util.RestUtil.copyNonNullProperties;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopRepository repository;

	@Override
	public Object getBySearch(Map<String, Object> params, Shop data) {

		return CrudUtil.search(params, data, repository);
	}

	@Override
	public Object getById(Long id) {
		return CrudUtil.getById(id, repository);
	}

	@Override
	public Object getByIdDe(Long id) {
		List<ShopDe> list = shopDao.IdDe(id);

		if (list.size() == 0) {
			return Lib.MapNoResult;
		}

		return Lib.GetMapDataSuccess(list);
	}

	@Override
	public Object delete(Long id) {
		return CrudUtil.delete(id, repository);
	}

	@PersistenceContext
	private EntityManager em; // 配合jpa写sql

	@Autowired
	private ShopDao shopDao;

	// 用jpa 写sql
	@Override
	public Object getBySearchDe(Map<String, Object> params, ShopDe data) {

		// TODO mybatis 方式查询
		Integer clientPage = Integer.parseInt((String) params.get("clientPage"));
		Integer everyPage  = Integer.parseInt((String) params.get("everyPage"));

		com.github.pagehelper.Page<ShopDe> page =  PageHelper.startPage(clientPage, everyPage);

		List<ShopDe> list = shopDao.searchDe(params);

		if (list.size() == 0) {
			return Lib.MapNoResult;
		}

		return Lib.GetMapDataPager(list, clientPage, (int) page.getTotal(), everyPage);

		// TODO jpa 方式查询(包含子查询)
//		// 分页查询，从 0 页开始查询 everyPage 个。
//		Integer clientPage = Integer.parseInt((String) params.get("clientPage"));
//		Integer everyPage  = Integer.parseInt((String) params.get("everyPage"));
//
////		String sql = "select distinct new shop(a.id,a.name,(select count(u) from Shop u where u.area=a.area) as proxyShopNum) from Shop a inner join Activity b on a.id = b.shopId where a = :shopData";
//
//		CriteriaBuilder       cb    = em.getCriteriaBuilder();
//		CriteriaQuery<ShopDe> query = cb.createQuery(ShopDe.class);
//		Root<ShopDe>          root  = query.from(ShopDe.class); // from
//
//		// join
//		//Join<ShopDe, Activity> join = root.join(root.getModel().getList("id", Activity.class));
//
//		// 子查询
//		Subquery<Long> subquery = query.subquery(Long.class);
//		Root<Activity> rootAct  = subquery.from(Activity.class);
//		subquery
//				.distinct(true)
//				.select(cb.count(root.get("id")))
//				.where(cb.equal(root.get("id"), rootAct.get("shopId")));
//
//		// 父查询
//		query.distinct(true);
//		//query.select(root);
//		query.multiselect(
//				root.get("id"),
//				root.get("name"),
//				root.get("contactName"),
//				root.get("area"),
//				root.get("address"),
//				root.get("phone"),
//				root.get("headimg"),
//				root.get("vipTryDate"),
//				root.get("vipDate"),
//				root.get("assets"),
//				root.get("isWithDraw"),
//				root.get("sharePro"),
//				root.get("shareShopNum"),
//				root.get("isProxy"),
//				subquery.getSelection(),
//				root.get("proxyPro"),
//				root.get("createtime")
//		);
//
//		// 条件查询
////		Predicate predicates = cb.and(
////				cb.like(root.get("name"), shop.getName()),
////				cb.equal(root.get("isWithDraw"), shop.getIsWithDraw()),
////				cb.equal(root.get("isProxy"), shop.getIsProxy())
////		);
//
//		List<Predicate> predicates = new LinkedList<>();
//		if(shop.getName() != null) {
//			predicates.add(cb.like(root.get("name"), shop.getName()));
//		}
//		if(shop.getIsWithDraw() != null) {
//			predicates.add(cb.equal(root.get("isWithDraw"), shop.getIsWithDraw()));
//		}
//		if(shop.getIsProxy() != null) {
//			predicates.add(cb.equal(root.get("isProxy"), shop.getIsProxy()));
//		}
//
//		//增加查询条件
//		query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
//
//		// 设置排序规则
//		Order order = cb.desc(root.get("id"));
//		query.orderBy(order);
//		// 分组
//		query.groupBy(
//				root
//		);
//		//query.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
//		TypedQuery typedQuery = em.createQuery(query); // TypedQuery执行查询与获取元模型实例
//
//		// 总数量
//		int sumPage = em.createQuery(query).getResultList().size();
//
//		em.close();
//
//		if (sumPage == 0) {
//			return Lib.MapNoResult;
//		}
//
//		// 执行查询
//		List res = typedQuery
//				// 分页数据
//				.setFirstResult((clientPage - 1) * everyPage)
//				.setMaxResults(everyPage)
//				// 返回结果
//				.getResultList();
//
//		if(res.size() == 0){
//			return Lib.MapNoResult;
//		}
//
//		return new GetInfoPager<Object>(Lib.CodeSuccess, Lib.MsgSuccess, res, new Pager(clientPage, sumPage, everyPage));
	}

	@Override
	public Object update(Shop shop) {

		Shop existData = repository.findById(shop.getId()).get();
		if(shop.getIsProxy() != null && existData.getIsProxy() == 1 && shop.getIsProxy() == 1){
			return Lib.GetMapData(Lib.CodeText, "该区域已经存在代理");
		}
		copyNonNullProperties(shop, existData);
		return CrudUtil.update(existData, repository);
	}

	@Override
	@SuppressWarnings("Duplicates")
	public Object create(Shop shop) {
		try {
			Shop createData = repository.save(shop);
			// 使用 uuid 作为源 token
			String     token = UUID.randomUUID().toString().replace("-", "");
			TokenModel model = new TokenModel(createData.getId(), token);
			// 30 分钟有效期
			cacheManager.set(model.getToken(), new CacheModel(TOKEN_MINUTE, model));
			return Lib.GetMapData(Lib.CodeCreate, Lib.MsgCreate, model);
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeSql, e.getCause().getCause().getMessage());
		}
	}
	private CacheManager cacheManager;

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		this.cacheManager = new RedisManager(redisConnectionFactory);
	}

	@Override
	@SuppressWarnings("Duplicates")
	public Object getIdByUnionid(String unionid) {

		List list = repository.getIdByUnionid(unionid);
		if(list.size() == 0){
			return Lib.MapNoResult;
		}
		Long id = (long)list.get(0);
		// 使用 uuid 作为源 token
		String     token = UUID.randomUUID().toString().replace("-", "");
		TokenModel model = new TokenModel(id, token);
		// 30 分钟有效期
		cacheManager.set(model.getToken(), new CacheModel(TOKEN_MINUTE, model));

		return Lib.GetMapData(Lib.CodeSuccess, Lib.MsgSuccess, model);
	}
}
