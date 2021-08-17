package org.jayhenri.cloud9.login;

import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.repository.login.LoginRepository;
import org.jayhenri.cloud9.service.login.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Login service test.
 */
@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    private Login login;

    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;

    @Captor
    private ArgumentCaptor<Login> captorLogin;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        loginService = new LoginService(loginRepository);

        login = new Login(
                true,
                true,
                true,
                "test.blizzard@gmail.uk",
                "2017740301",
                "somepassword"
        );
    }

    /**
     * Test add.
     */
    @Test
    void add() {

        loginService.add(login);

        then(loginRepository).should().save(captorLogin.capture());

        assertThat(login).isEqualTo(captorLogin.getValue());
    }

    /**
     * Test update.
     */
    @Test
    void update() {

        loginService.update(login);

        then(loginRepository).should().save(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {

        loginService.remove(login);

        then(loginRepository).should().delete(captorLogin.capture());

        assertThat(captorLogin.getValue()).isEqualTo(login);
    }

    /**
     * Find all.
     */
    @Test
    void findAllLogins() {

        loginService.findAll();

        then(loginRepository).should().findAll();
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(loginRepository.existsById(uuid)).willReturn(true);

        boolean exists = loginService.existsById(uuid);

        then(loginRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(loginRepository.existsById(uuid)).willReturn(false);

        boolean exists = loginService.existsById(uuid);

        then(loginRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(loginRepository.getById(uuid)).willReturn(login);

        Login _login = loginService.getById(uuid);

        then(loginRepository).should().getById(captorUUID.capture());

        assertThat(_login).isEqualTo(login);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Exists by Phone Number
     */
    @Test
    void existsByPhoneNumber() {

        given(loginRepository.existsByPhoneNumber("2017740301"))
                .willReturn(true);

        Boolean bool = loginService.existsByPhoneNumber("2017740301");
        then(loginRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("2017740301");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by Phone Number
     */
    @Test
    void doesNotExistsByPhoneNumber() {

        given(loginRepository.existsByPhoneNumber("2017740301"))
                .willReturn(false);

        Boolean bool = loginService.existsByPhoneNumber("2017740301");
        then(loginRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("2017740301");
        assertThat(bool).isFalse();
    }
}
