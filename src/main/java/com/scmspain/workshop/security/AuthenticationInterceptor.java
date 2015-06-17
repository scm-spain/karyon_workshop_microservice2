package com.scmspain.workshop.security;

import netflix.karyon.transport.interceptor.InboundInterceptor;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;

import javax.inject.Inject;

/**
 * @author Nitesh Kant
 */
public class AuthenticationInterceptor implements InboundInterceptor<HttpServerRequest<ByteBuf>, HttpServerResponse<ByteBuf>> {

    private final AuthenticationManagerInterface authService;

    @Inject
    public AuthenticationInterceptor(AuthenticationManagerInterface authService) {
        this.authService = authService;
    }

    @Override
    public Observable<Void> in(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        return authService.authenticate(request).map(aBoolean -> null);
    }
}
