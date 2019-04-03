package com.deercoder.shop.dao;

import com.deercoder.shop.model.ShopDe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dreamlu
 * @date 2019/04/01
 */
@Repository
@Mapper
public interface ShopDao {

	/**
	 * 典型通过sql进行查询/统计/统计条件判断/日期筛选/条件动态判断等
	 * mysql
	 * 这里对应表被删除,仿照写即可
	 */
	@Select("<script>" +
			"select distinct a.id, a.openid, a.name, a.contact_name, a.area, a.address, a.phone, a.headimg, a.vip_try_date, a.vip_date, a.assets, a.is_with_draw, a.share_pro, a.is_proxy, " +
			"a.proxy_pro, a.createtime, \n" +
			"-- 账号状态,1会员,0过期会员,2未购买\n" +
			"case when now() &gt; a.vip_date then 0\n" +
			"when now() &lt;= a.vip_date then 1\n" +
			"else 2 end as status,\n " +
			"(select count(aa.id) from activity aa where aa.shop_id=a.id and now() > aa.end_date) as his_num, \n" +
			"(select count(bb.id) from activity aa inner join client_activity bb on aa.id=bb.activity_id where aa.shop_id=a.id and now() > aa.end_date) as his_join_num, \n" +
			"(select sum(aa.money) from activity aa inner join client_activity bb on aa.id=bb.activity_id where aa.shop_id=a.id and now() > aa.end_date) as his_money, \n" +
			"(select count(0) from expend aa where aa.is_with_draw=2 and aa.shop_id = a.id) as has_with_draw, \n" +
			"(select count(0) from income aa where aa.shop_id=a.id and aa.type in (1,2)) as share_shop_num, \n" +
			"-- 统计该区域下的付款的商家数\n" +
			"(select distinct count(distinct aa.shop_id) from income aa inner join shop bb on aa.shop_id=bb.id where bb.area=a.area) as proxy_shop_num, \n" +
			"-- 代理佣金\n" +
			"(select distinct sum(distinct aa.area_money) from income aa inner join shop bb on aa.shop_id=bb.id where bb.area=a.area) as proxy_money \n" +
			"from shop a " +
			"where 1=1 " +
			"<if test='name != null'> and a.name like concat('%',#{name},'%') </if>" +
			"<if test='isWithDraw != null'> and a.is_with_draw = #{isWithDraw} </if>" +
			"<if test='isProxy != null'> and a.is_proxy = #{isProxy} </if>" +
			"group by a.id " +
			"<if test='status != null'> having status = #{status} </if>" +
			"order by a.id desc " +
			"</script>")
	List<ShopDe> searchDe(Map<String, Object> params);
	
	@Select("select a.id, a.address, a.area, a.assets, a.contact_name, a.createtime, a.headimg, a.is_proxy, a.is_with_draw, a.name, a.openid, a.phone, a.proxy_pro, a.share_pro , a.unionid, a.vip_date, a.vip_try_date, " +
			"(select proxy_pro from shop aa where aa.area=a.area and is_proxy=1) as area_proxy_pro " +
			"from shop a where a.id = #{id}")
	List<ShopDe> IdDe(Long id);
}
