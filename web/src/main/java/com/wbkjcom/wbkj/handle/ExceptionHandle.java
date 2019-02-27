package com.wbkjcom.wbkj.handle;

import com.wbkjcom.commons.lib.Lib;
import com.wbkjcom.wbkj.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * com.wbkjcom.wbkj.handle
 */

//统一异常处理
@ControllerAdvice
public class ExceptionHandle {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object handle(Exception e) {
		if (e instanceof MyException) {
			MyException myException = (MyException) e;
			return Lib.GetMapData(myException.getStatus(), myException.getMessage());
		} else {
			logger.error("系统异常={}", e);
			return Lib.GetMapData(Lib.CodeError, Lib.MsgError, e.getMessage());
		}
	}
}
