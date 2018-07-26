package com.beadwallet.data.exception;

public class ValidateKeySignException extends Exception {

  public ValidateKeySignException() {
    super("签名验证失败");
  }
}
