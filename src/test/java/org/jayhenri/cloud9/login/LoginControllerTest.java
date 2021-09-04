package org.jayhenri.cloud9.login;

import org.jayhenri.store_manager.controller.login.LoginController;
import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidLoginException;
import org.jayhenri.store_manager.exception.notfound.LoginNotFoundException;
import org.jayhenri.store_manager.interfaces.service.other.LoginServiceI;
import org.jayhenri.store_manager.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.naming.InvalidNameException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Login controller test.
 */
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private Login login;

    @Mock
    private LoginServiceI loginService;

    @Captor
    private ArgumentCaptor<Login> captorLogin;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private LoginController loginController;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        loginController = new LoginController(loginService);

        login = new Login();
    }

    /**
     * Add login.
     *
     * @throws InvalidLoginException           the invalid login exception
     * @throws LoginAlreadyExistsException     the login already exists exception
     */
    @Test
    void addLogin() throws InvalidLoginException, LoginAlreadyExistsException {

        given(loginService.existsById(login.getLoginUUID())).willReturn(false);

        assertThat(loginController.add(login).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(loginService).should().add(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    /**
     * Add login throws invalid login exception.
     */
    @Test
    void addLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.add(null));
    }

    /**
     * Add login throws login already exists exception.
     */
    @Test
    void addLoginThrowsLoginAlreadyExistsException() {

        given(loginService.existsById(login.getLoginUUID())).willReturn(true);

        assertThrows(LoginAlreadyExistsException.class, () -> loginController.add(login));
    }

    /**
     * Update login.
     *
     * @throws InvalidLoginException           the invalid login exception
     * @throws LoginNotFoundException          the login not found exception
     */
    @Test
    void updateLogin() throws InvalidLoginException, LoginNotFoundException {

        given(loginService.existsById(uuid)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(loginController.update(login, uuid).getStatusCode());

        then(loginService).should().update(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    /**
     * Update login throws invalid login exception.
     */
    @Test
    void updateLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.add(null));
    }

    /**
     * Update login throws login not found exception.
     */
    @Test
    void updateLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.update(login, uuid));
    }

    /**
     * Delete login.
     *
     * @throws LoginNotFoundException the login not found exception
     */
    @Test
    void deleteLogin() throws LoginNotFoundException {

        given(loginService.existsById(uuid)).willReturn(true);
        given(loginService.getById(uuid)).willReturn(login);

        assertThat(HttpStatus.OK).isEqualTo(loginController.delete(uuid).getStatusCode());

        then(loginService).should().remove(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    /**
     * Delete login throws login not found exception.
     */
    @Test
    void deleteLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.delete(uuid));
    }

    /**
     * List.
     */
    @Test
    void list() {

    }

    /**
     * Get.
     *
     * @throws InvalidNameException   the invalid name exception
     * @throws InvalidLoginException  the invalid login exception
     * @throws LoginNotFoundException the login not found exception
     */
    @Test
    void get() throws InvalidNameException, InvalidLoginException, LoginNotFoundException {

        given(loginService.existsById(uuid)).willReturn(true);
        given(loginService.getById(uuid)).willReturn(login);

        assertThat(HttpStatus.OK).isEqualTo(loginController.get(uuid).getStatusCode());
        assertThat(login).isEqualTo(loginController.get(uuid).getBody());
    }

    /**
     * Gets login throws login not found exception.
     */
    @Test
    void getLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.get(uuid));
    }

    /**
     * Gets login throws invalid login exception.
     */
    @Test
    void getLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.get(null));
    }
}
