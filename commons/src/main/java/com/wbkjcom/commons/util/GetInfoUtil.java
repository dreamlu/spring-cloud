package com.wbkjcom.commons.util;

import com.wbkjcom.commons.api.GetInfoN;
import com.wbkjcom.commons.api.MapData;
import com.wbkjcom.commons.lib.Lib;

public class GetInfoUtil {

    public static GetInfoN success(Object object){
        GetInfoN getInfo = new GetInfoN(Lib.CodeSuccess, Lib.MsgSuccess, object);
        return getInfo;
    }

    public static MapData success(){
        return Lib.MapSuccess;
    }

//    // search
//    public static Object search(){
//
//    }

}
