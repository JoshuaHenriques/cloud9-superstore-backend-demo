package org.jayhenri.store_manager.controller.login;

import org.jayhenri.store_manager.exception.alreadyexists.LoginAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidLoginException;
import org.jayhenri.store_manager.exception.notfound.LoginNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.ControllerI;
import org.jayhenri.store_manager.interfaces.service.other.LoginServiceI;
import org.jayhenri.store_manager.model.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Login>> list() {

        List<Login> list = loginService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("LoginController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

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
