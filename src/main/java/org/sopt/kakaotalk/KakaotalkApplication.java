package org.sopt.kakaotalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KakaotalkApplication {

  public static void main(String[] args) {
    SpringApplication.run(KakaotalkApplication.class, args);
  }
}
