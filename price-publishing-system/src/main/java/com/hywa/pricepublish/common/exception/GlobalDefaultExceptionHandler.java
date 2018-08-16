package com.hywa.pricepublish.common.exception;

import static com.hywa.pricepublish.common.enums.CommonEnum.FAILURE;
import static com.hywa.pricepublish.common.enums.CommonEnum.PARAMETER_NOT_NULL;

import com.google.common.base.Joiner;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Log;
import com.hywa.pricepublish.dao.mapper.LogMapper;
import com.hywa.pricepublish.representation.ResponseBase;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@Slf4j
@CrossOrigin
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    @Autowired
    private LogMapper logMapper;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseBase> defaultErrorHandler(
            HttpServletRequest req,
            HttpServletResponse rep,
            Exception e) {

        Log operationLog = new Log();

        try {
            String userId = req.getSession().getAttribute("userId").toString();
            operationLog.setUserid(userId);
        } catch (Exception e1) {
            operationLog.setUserid("临时用户");
        }

        operationLog.setId(UUIDUtils.randomUUID());
        operationLog.setCreateTime(new Date());
        operationLog.setReqIp(req.getRemoteAddr());
        operationLog.setReqPort(req.getRemotePort());
        operationLog.setReqHost(req.getRemoteHost());
        operationLog.setReqUri(req.getRequestURI());
        operationLog.setRealErrorMessage(e.getMessage());
        operationLog.setParameter(req.getQueryString());

        log.error(req.getRequestURL() + "<:=====:>" +
                req.getMethod() + "<:=====:>" +
                req.getQueryString() + "<:=====:>" +
                e.getMessage());
        e.printStackTrace();

        ResponseBase responseBase = new ResponseBase<>();

        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            Short errorCode = globalException.getErrorCode();
            String message = globalException.getMessage();
            operationLog.setErrorCode(errorCode);
            operationLog.setErrorMessage(message);
            responseBase.setRetHead(errorCode, message);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error(e.getClass().toString());
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getAllErrors();
            List<String> errorMessages = new ArrayList<>();
            //获得所有错误提示码,将错误提示码拼接成字符串
            allErrors.forEach(objectError -> {
                errorMessages.add(objectError.getDefaultMessage());
                log.error(objectError.getDefaultMessage());
            });
            responseBase.setRetHead(FAILURE.getIndex(), Joiner.on(",").join(errorMessages));
        } else if (e instanceof MissingServletRequestParameterException) {
            log.error(e.getClass().toString());
            responseBase.setRetHead(
                    CommonEnum.PARAMETER_NOT_NULL.getIndex(),
                    CommonEnum.PARAMETER_NOT_NULL.getValue());
        } else if (e instanceof FileNotFoundException) {
            log.error(e.getClass().toString());
            responseBase.setRetHead(
                    CommonEnum.FILE_NOT_FOUND.getIndex(),
                    CommonEnum.FILE_NOT_FOUND.getValue());
        } else if (e instanceof MaxUploadSizeExceededException) {
            log.error(e.getClass().toString());
            responseBase.setRetHead(
                    CommonEnum.MAX_UPLOAD_SIZE_EXCEEDED.getIndex(),
                    CommonEnum.MAX_UPLOAD_SIZE_EXCEEDED.getValue());
        } else {
            log.error(e.getClass().toString() + ":" + e.getMessage());
            responseBase.setRetHead(
                    CommonEnum.INTERNAL_ERROR.getIndex(),
                    CommonEnum.INTERNAL_ERROR.getValue());
        }

        try {
            logMapper.insert(operationLog);
        } catch (Exception internalException) {
            log.error(internalException.getMessage());
        }

        return new ResponseEntity<>(responseBase, HttpStatus.OK);
    }
}
