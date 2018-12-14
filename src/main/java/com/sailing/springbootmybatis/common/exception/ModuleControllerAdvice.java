package com.sailing.springbootmybatis.common.exception;

import com.sailing.springbootmybatis.common.response.BuildResponseUtil;
import com.sailing.springbootmybatis.common.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.common
 * @Description: 全局异常统一处理类
 * @date 2018/9/14 10:32
 */
//@ControllerAdvice
@RestControllerAdvice(basePackages = {"com.sailing.springbootmybatis.controller"})
//类似于 controller 和 restController的区别，@RestController中的所有接口返回值都为 responseBody json 格式
public class ModuleControllerAdvice {

    private Logger SERVICE_LOGGER = LoggerFactory.getLogger(ServiceException.class);

    private Logger SERVER_LOGGER = LoggerFactory.getLogger(Exception.class);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private ResponseData handleServiceException(Exception e){
        String message;
        ResponseData responseData;
        if(e instanceof ServiceException){
            message = "业务受理失败, 原因:" + e.getLocalizedMessage();
            SERVICE_LOGGER.info("业务受理失败, 原因:{}", e.getLocalizedMessage());
            responseData = BuildResponseUtil.buildServiceFailResponse(message);
        }else{
            e.printStackTrace();
            message = "程序异常, 信息:" + e;
            SERVER_LOGGER.info("程序异常, 信息:{}", e);
            responseData = BuildResponseUtil.buildServerErrorResponse(message);
        }
        return responseData;
    }
}
