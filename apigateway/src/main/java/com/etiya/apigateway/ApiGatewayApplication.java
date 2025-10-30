package com.etiya.apigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient // İDscovery Server'a bağlanması için eklediğimiz anotasyon
//Bu anotasyon, servisin (apigateway) EurekaServer (Discovery Server) olarak
// adlandırdığınız yapıya bağlanmasını ve kendisini ona kayıt etmesini sağlar.
//Dinamik Adresleme: Mikroservisler genellikle dinamik portlar/IP'ler üzerinde çalışır (özellikle konteynerlarda).
// Gateway'in customerservice'e ulaşması için onun hangi adreste çalıştığını bilmesi gerekir. Bu anotasyon sayesinde
// Gateway, Eureka'ya sorarak güncel adresi öğrenir.
public class ApiGatewayApplication {

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpResponse response = ctx.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "*");
            if (ctx.getRequest().getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            return chain.filter(ctx);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

//API Gateway, tüm dış trafiğin sisteme girdiği tek giriş noktası (Single Entry Point) olarak hareket eder.
// Gelen istekleri alır, hangi iç servise (örneğin customerservice veya searchservice) yönlendireceğine karar
// verir ve bu isteği iletir.