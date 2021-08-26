package org.jayhenri.cloud9.controller.login;

import java.util.List;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidLoginException;
import org.jayhenri.cloud9.exception.notfound.LoginNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.ControllerI;
import org.jayhenri.cloud9.interfaces.service.other.LoginServiceI;
import org.jayhenri.cloud9.model.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Login controller.
 */
@RestController
@RequestMapping("api/login")
public class LoginController implements ControllerI<Login> {

    private final LoginServiceI loginService;

    /**
     * Instantiates a new Login controller.
     *
     * @param loginService the login service
     */
    @Autowired
    public LoginController(LoginServiceI loginService) {

        this.loginService = loginService;
    }

    /**
     * Register response entity.
     *
     * @param login the login
     * @return the response entity
     * @throws LoginAlreadyExistsException the login already exists exception
     * @throws InvalidLoginException       the invalid login exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Login login)
            throws LoginAlreadyExistsException, InvalidLoginException {

        if (ObjectUtils.isEmpty(login))
            throw new InvalidLoginException();

        else if (loginService.existsById(login.getLoginUUID()))
            throw new LoginAlreadyExistsException();

        loginService.add(login);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("LoginController", "add");
        return new ResponseEntity<>("Successfully Created Login", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update login.
     *
     * @param login   the login
     * @param loginId the login id
     * @return the response entity
     * @throws InvalidLoginException  the invalid login exception
     * @throws LoginNotFoundException the login not found exception
     */
    @PutMapping(value = "/update/{loginId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Login login, @PathVariable UUID loginId)
            throws InvalidLoginException, LoginNotFoundException {
        if (!ObjectUtils.isEmpty(login)) {
            if (loginService.existsById(loginId)) {
                login.setLoginUUID(loginId);
                loginService.update(login);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("LoginController", "update");
                return new ResponseEntity<>("Successfully Updated Login", responseHeaders, HttpStatus.OK);
            } else
                throw new LoginNotFoundException();
        } else
            throw new InvalidLoginException();
    }

    /**
     * Delete login.
     *
     * @param loginId the email
     * @return the response entity
     * @throws LoginNotFoundException the login not found exception
     */
    @DeleteMapping(value = "/delete/{loginId}")
    public ResponseEntity<String> delete(@PathVariable UUID loginId)
            throws LoginNotFoundException {
        if (loginService.existsById(loginId)) {
            loginService.remove(loginService.getById(loginId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("LoginController", "delete");
            return new ResponseEntity<>("Successfully Deleted Login", responseHeaders, HttpStatus.OK);
        } else
            throw new LoginNotFoundException();
    }

    /**
     * List logins response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Login>> list() {

        List<Login> list = loginService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("LoginController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param loginId the login name
     */
    @GetMapping(value = "/get/{loginId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Login> get(@PathVariable UUID loginId)
            throws InvalidNameException, InvalidLoginException, LoginNotFoundException {

        if (!ObjectUtils.isEmpty(loginId)) {
            if (loginService.existsById(loginId)) {
                Login _login = loginService.getById(loginId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("LoginController", "get");
                return new ResponseEntity<>(_login, responseHeaders, HttpStatus.OK);
            } else
                throw new LoginNotFoundException();
        } else
            throw new InvalidLoginException();
    }
}
