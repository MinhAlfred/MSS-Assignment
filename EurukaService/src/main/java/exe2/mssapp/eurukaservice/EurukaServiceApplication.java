package exe2.mssapp.eurukaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurukaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurukaServiceApplication.class, args);
    }

}
