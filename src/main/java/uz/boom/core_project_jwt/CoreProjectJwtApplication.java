package uz.boom.core_project_jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.boom.core_project_jwt.entity.AuthUser;
import uz.boom.core_project_jwt.enums.Role;
import uz.boom.core_project_jwt.repository.AuthUserRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class CoreProjectJwtApplication {

    private final PasswordEncoder passwordEncoder;
    private final AuthUserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(CoreProjectJwtApplication.class, args);
    }

    //    @Bean
    public void run() throws Exception {
        CommandLineRunner runner1 = (a) -> {
            AuthUser user = AuthUser.builder()
                    .fullName("Kasimov Doniyor")
                    .email("kasimovd554@gmail.com")
                    .password(passwordEncoder.encode("doniyor1988"))
                    .phoneNumber("+998909821988")
                    .role(Role.SUPER_ADMIN)
                    .status(true)
                    .build();
            repository.save(user);
        };
        runner1.run("s", "b");
    }

}
