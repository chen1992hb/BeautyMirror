package com.beadwallet.beautymirror.exception;

import com.beadwallet.data.exception.ServerResultException;
import com.beadwallet.data.exception.ValidateKeySignException;
import com.google.gson.JsonParseException;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

  private static final String UNKNOWN_EXCEPTION = "未知异常";
  private static final String SERVER_RESULT_EXCEPTION = "服务异常";
  private static final String HTTP_EXCEPTION = "网络请求异常";
  private static final String IO_EXCEPTION = "网络连接异常";
  private static final String JSON_PARSE_EXCEPTION = "数据解析异常";
  private static final String VALIDATE_KEYSIGN_EXCEPTION = "签名验证异常";

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Exception exception) {
    String message = UNKNOWN_EXCEPTION;
    if(exception instanceof ServerResultException){
      message = SERVER_RESULT_EXCEPTION;
    }else if(exception instanceof HttpException){
      message = HTTP_EXCEPTION;
    }else if(exception instanceof IOException){
      message = IO_EXCEPTION;
    }else if(exception instanceof JsonParseException){
      message = JSON_PARSE_EXCEPTION;
    }else if(exception instanceof ValidateKeySignException){
      message = VALIDATE_KEYSIGN_EXCEPTION;
    }else{
      message += (exception.getClass().getSimpleName() + ":" + exception.getMessage());
    }
    return message;
  }
}
