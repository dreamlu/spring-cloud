package com.kedacom.commons.util;

import com.kedacom.commons.api.Result;

public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result(0,"成功",object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code,String msg){
        Result result = new Result(code,msg);
        return result;
    }
}
