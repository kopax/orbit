package com.inmaytide.orbit.exceptions.handler;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

@FunctionalInterface
public interface ResponseErrorBuilder extends Serializable {

    ResponseError build(Throwable e, HttpStatus status);

}