package org.jayhenri.cloud9.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.UUID;

import javax.naming.InvalidNameException;

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
     * @throws InventoryAlreadyExistsException the login already exists exception
     * @throws InvalidLoginException       the invalid login exception
     * @throws LoginAlreadyExistsException
     */
    @Test
    void addLogin() throws InventoryAlreadyExistsException, InvalidLoginException, LoginAlreadyExistsException {

        given(loginService.existsById(login.getLoginUUID())).willReturn(false);

        assertThat(loginController.add(login).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(loginService).should().add(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    @Test
    void addLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.add(null));
    }

    @Test
    void addLoginThrowsLoginAlreadyExistsException() {

        given(loginService.existsById(login.getLoginUUID())).willReturn(true);

        assertThrows(LoginAlreadyExistsException.class, () -> loginController.add(login));
    }

    @Test
    void updateLogin() throws InventoryAlreadyExistsException, InvalidLoginException, LoginNotFoundException {

        given(loginService.existsById(uuid)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(loginController.update(login, uuid).getStatusCode());

        then(loginService).should().update(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    @Test
    void updateLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.add(null));
    }

    @Test
    void updateLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.update(login, uuid));
    }

    @Test
    void deleteLogin() throws LoginNotFoundException {

        given(loginService.existsById(uuid)).willReturn(true);
        given(loginService.getById(uuid)).willReturn(login);

        assertThat(HttpStatus.OK).isEqualTo(loginController.delete(uuid).getStatusCode());

        then(loginService).should().remove(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    @Test
    void deleteLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.delete(uuid));
    }

    @Test
    void list() {

    }

    @Test
    void get() throws InvalidNameException, InvalidLoginException, LoginNotFoundException {
        
        given(loginService.existsById(uuid)).willReturn(true);
        given(loginService.getById(uuid)).willReturn(login);

        assertThat(HttpStatus.OK).isEqualTo(loginController.get(uuid).getStatusCode());
        assertThat(login).isEqualTo(loginController.get(uuid).getBody());
    }

    @Test
    void getLoginThrowsLoginNotFoundException() {

        given(loginService.existsById(uuid)).willReturn(false);

        assertThrows(LoginNotFoundException.class, () -> loginController.get(uuid));
    }

    @Test
    void getLoginThrowsInvalidLoginException() {

        assertThrows(InvalidLoginException.class, () -> loginController.get(null));
    }
}
