package es.cdmfp.dwes.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Arranque de la tienda Bit a Bit.
 *
 * <p>Se ejecuta con {@code ./mvnw spring-boot:run} o desde IntelliJ, y la tienda
 * queda en http://localhost:8080/catalogo</p>
 */
@SpringBootApplication
public class TiendaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }
}
