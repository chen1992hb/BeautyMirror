package com.beadwallet.beautymirror.di.scope;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;



@Scope//局部单例
@Retention(RetentionPolicy.RUNTIME)//执行时存在
public @interface ActivityScope {
}
