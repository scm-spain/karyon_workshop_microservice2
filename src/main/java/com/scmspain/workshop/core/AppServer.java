package com.scmspain.workshop.core;


import com.google.inject.Singleton;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.governator.annotations.Modules;
import com.scmspain.workshop.controller.ForlayoEndpoint;
import com.scmspain.workshop.core.health.HealthCheck;
import com.scmspain.workshop.security.AuthenticationManager;
import com.scmspain.workshop.security.AuthenticationManagerInterface;
import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.KaryonBootstrap;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrap;
import netflix.karyon.eureka.KaryonEurekaModule;
import scmspain.karyon.restrouter.KaryonRestRouterModule;

@ArchaiusBootstrap()
@KaryonBootstrap(name = "AppServer", healthcheck = HealthCheck.class)
@Singleton
@Modules(include = {
    //ShutdownModule.class,
    //KaryonWebAdminModule.class,
    AppServer.KaryonRestRouterModuleImpl.class,
    KaryonEurekaModule.class,
})
public interface AppServer {
    class KaryonRestRouterModuleImpl extends KaryonRestRouterModule{

        private DynamicPropertyFactory properties = DynamicPropertyFactory.getInstance();

        @Override
        protected void configureServer() {

            interceptorSupport().forUri("/*").intercept(LoggingInterceptor.class);
            int port = properties.getIntProperty("server.port", 8082).get();
            int threads = properties.getIntProperty("server.threads",50).get();
            server().port(port).threadPoolSize(threads);


        }
        @Override
        public void configure()
        {
            bind(AuthenticationManagerInterface.class).to(AuthenticationManager.class);
            bind(ForlayoEndpoint.class).asEagerSingleton();

            super.configure();
        }
    }
}