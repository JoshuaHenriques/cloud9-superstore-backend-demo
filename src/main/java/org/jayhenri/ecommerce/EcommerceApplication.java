package org.jayhenri.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The type Ecommerce application.
 */
@SpringBootApplication
@EnableJpaAuditing
public class EcommerceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
