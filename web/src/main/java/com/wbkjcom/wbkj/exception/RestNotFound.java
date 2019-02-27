package com.wbkjcom.wbkj.exception;

import com.wbkjcom.commons.lib.Lib;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * des: 404拦截
 * author: dreamlu
 */

@RestController
public class RestNotFound implements ErrorController {

    private static final String NOT_FOUND = "404";
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Object handleError() {
        return Lib.GetMapData(Lib.CodeNoRoute, "接口不存在->('.')");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}