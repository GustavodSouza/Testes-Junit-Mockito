package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test") // Identifica que é para usar o application-test.properties
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Sucesso ao encontrar usuário")
    void findUserByDocumentSuccess() {
        String document = "99999999901";
        UserDTO data = new UserDTO("Fernanda", "Teste", document, new BigDecimal(10), "teste@gmail.com", "4444", UserType.COMMON);

        this.created(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Erro ao encontrar usuário")
    void findUserByDocumentError() {
        String document = "99999999901";

       // UserDTO data = new UserDTO("Fernanda", "Teste", document, new BigDecimal(10), "teste@gmail.com", "4444", UserType.COMMON);

      //  this.created(data);

        Optional<User> result = this.userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User created(UserDTO data) {
        User newUser = new User(data);
        this.entityManager.persist(newUser);

        return newUser;
    }

}