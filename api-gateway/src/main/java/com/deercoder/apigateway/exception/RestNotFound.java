package com.deercoder.apigateway.exception;

import com.deercoder.commons.lib.Lib;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 其他服务中断异常, 防止网关服务本身中断
 * @author: dreamlu
 * @date 2019-03-13
 */
@RestController
public class RestNotFound implements ErrorController {

	private static final String NOT_FOUND  = "404";
	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public Object handleError() {
		return Lib.GetMapData(Lib.CodeNoRoute, "服务不可达->('.')");
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}