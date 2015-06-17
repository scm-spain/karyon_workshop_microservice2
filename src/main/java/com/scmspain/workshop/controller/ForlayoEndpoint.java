package com.scmspain.workshop.controller;

import com.google.inject.Singleton;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;
import scmspain.karyon.restrouter.annotation.Endpoint;
import scmspain.karyon.restrouter.annotation.Path;

import javax.ws.rs.HttpMethod;
import java.util.Map;

@Singleton
@Endpoint
public class ForlayoEndpoint {


    @Path(value = "/forlayo2/{id}", method = HttpMethod.GET )
    public Observable<Void> getForlayos2Resource(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response, Map<String,String> pathParams) {

        String id = pathParams.get("id");

        return response.writeStringAndFlush("lalalla: " + id+"\n")
                .concatWith(response.close());



    }

}
