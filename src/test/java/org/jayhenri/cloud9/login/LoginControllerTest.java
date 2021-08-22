package org.jayhenri.cloud9.login;

import org.jayhenri.cloud9.controller.login.LoginController;
import org.jayhenri.cloud9.interfaces.controller.ControllerI;
import org.jayhenri.cloud9.interfaces.service.other.LoginServiceI;
import org.jayhenri.cloud9.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Login controller test.
 */
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private ControllerI<Login> loginController;

    @Mock
    private LoginServiceI loginService;

    /**
     * Set up.
     */
    @BeforeEach
    void SetUp() {

        loginController = new LoginController(loginService);
    }

    /**
     * List.
     */
    @Test
    void list() {
        ResponseEntity<List<Login>> response = loginController.list();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
