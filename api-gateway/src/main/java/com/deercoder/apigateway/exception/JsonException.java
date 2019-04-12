package com.deercoder.apigateway.exception;

import com.deercoder.commons.lib.Lib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Objects;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class JsonException {

	// 配置文件信息
	@Autowired
	private Environment env;

	// 文件大小超过指定大小，抛异常
	@ResponseBody
	@ExceptionHandler(value = MaxUploadSizeExceededException.class)
	public Object resolveFileUploadException(MaxUploadSizeExceededException e) {
		log.info("文件上传异常："+e.getMessage());

		return Lib.GetMapData(Lib.CodeFile, "文件上传超过限制,建议"+ Objects.requireNonNull(env.getProperty("spring.servlet.multipart.max-file-size"))+"以下");
	}

	// 通用错误
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Object exception(Exception e) {
		log.info("未知错误："+e.getMessage());

		return Lib.GetMapData(Lib.CodeFile, e.getMessage());
	}
}