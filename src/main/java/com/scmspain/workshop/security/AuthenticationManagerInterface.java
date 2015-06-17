package com.scmspain.workshop.security;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

/**
 * @author Nitesh Kant
 */
public interface AuthenticationManagerInterface {

    Observable<Boolean> authenticate(HttpServerRequest<ByteBuf> request);
}
