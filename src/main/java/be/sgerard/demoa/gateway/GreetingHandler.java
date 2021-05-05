package be.sgerard.demoa.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class GreetingHandler {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public Mono<ServerResponse> hello(ServerRequest request) {
        final String checkResult = checkConnection();

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Hello, Spring! Database check result [" + checkResult + "]."));
    }

    private String checkConnection() {
        try {
            DriverManager.getConnection(url, username, password);

            return "success";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
