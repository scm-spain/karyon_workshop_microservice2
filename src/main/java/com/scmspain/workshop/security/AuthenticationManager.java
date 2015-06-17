package com.scmspain.workshop.security;

import com.netflix.config.DynamicPropertyFactory;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rx.Observable;

/**
 * @author Nitesh Kant
 */
public class AuthenticationManager implements AuthenticationManagerInterface {

    String authenticationHeader = DynamicPropertyFactory.getInstance().getStringProperty("server.auth_header_value", null).get();

    @Override
    public Observable<Boolean> authenticate(HttpServerRequest<ByteBuf> request) {
        if (request.getHeaders().contains(authenticationHeader)) {
            return Observable.just(Boolean.TRUE);
        } else {
            return Observable.error(new IllegalArgumentException("Should pass a header authentication!"));
        }
    }
}
