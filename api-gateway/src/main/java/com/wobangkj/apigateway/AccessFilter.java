package com.wobangkj.apigateway;

import com.deercoder.commons.manager.cache.CacheManager;
import com.deercoder.commons.manager.cache.impl.RedisManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.servlet.http.HttpServletRequest;

//Zuul过滤器,在实现了自定义过滤器之后，它并不会直接生效，我们还需要为其创建具体的Bean才能启动该过滤器(应用主类中创建)
//可定义一些与业务无关的通用逻辑实现对请求的过滤和拦截，比如：签名校验、权限校验、请求限流等功能。
@Slf4j
public class AccessFilter extends ZuulFilter {

	/**
	 * 过滤uri白名单
	 */
	private static final String[] IGNORE_URI = {"/login","/register", "/static/", "/common-service/", "/getId"};

	/**
	 * 缓存
	 */
	private CacheManager cacheManager;

	@Autowired
	public void setRedis(RedisConnectionFactory redisConnectionFactory) {
		this.cacheManager = new RedisManager(redisConnectionFactory);
	}

	//过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
	@Override
	public String filterType() {
		return "pre";//pre，代表会在请求被路由之前执行
	}

	//过滤器的执行顺序
	@Override
	public int filterOrder() {
		return 0;
	}

	//判断该过滤器是否需要被执行
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext     ctx     = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		//HttpSession        session = ctx.getRequest().getSession();

		log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

		/** 默认用户没有登录 */
		boolean flag = false;
		/** 获得请求的ServletPath */
		String servletPath = request.getServletPath();
		/**  判断请求是否需要拦截 */
		for (String s : IGNORE_URI) {
			if (servletPath.contains(s)) {
				flag = true;
				break;
			}
		}

		if (!flag) {
//			String token = request.getHeader("token");
//			//ctx.setSendZuulResponse(false);
//			//ctx.setResponseStatusCode(401);
//			//ctx.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
//
//			if (token == null) {
//				// no token
//				ctx.setSendZuulResponse(false);
//				ctx.setResponseStatusCode(401);
//				ctx.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
//				ctx.setResponseBody("{\"status\":" + Lib.CodeNoAuth + ", \"msg\":\"token不能为空\"}");
//				return null;
//			}
//
//			CacheModel cacheModel = cacheManager.get(token);
//			//cacheModel.
//			if (cacheModel == null) {
//				// 存在cacheModel, 表明存在对应token
//				// 简单验证
//				ctx.setSendZuulResponse(false);
//				ctx.setResponseStatusCode(401);
//				ctx.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
//				ctx.setResponseBody("{\"status\":" + Lib.CodeNoAuth + ", \"msg\":\"token不合法\"}");
//				return null;
//			}
//
//			// 延长token对应时间
//			cacheManager.set(token, cacheModel);
		}
		return null;
	}
}
