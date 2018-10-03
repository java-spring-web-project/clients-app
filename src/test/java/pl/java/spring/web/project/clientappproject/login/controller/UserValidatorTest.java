package pl.java.spring.web.project.clientappproject.login.controller;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;

    @Test
    public void emptyUsernameField() {
        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("");
        invalidUser.setPassword("faplllosss123");
        invalidUser.setPasswordConfirm("faplllosss123");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError usernameFieldError = errors.getFieldError("username");
        assertThat("username", is(usernameFieldError.getField()));

        List<String> errorCodes = Arrays.asList(usernameFieldError.getCodes());
        assertThat(4, is(errorCodes.size()));
        assertTrue(errorCodes.contains("NotEmpty.invalidUser.username"));
        assertTrue(errorCodes.contains("NotEmpty.username"));
        assertTrue(errorCodes.contains("NotEmpty.java.lang.String"));
        assertTrue(errorCodes.contains("NotEmpty"));
    }

    @Test
    public void emptyPasswordAndConfirmPasswordFields() {
        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("faplllosss");
        invalidUser.setPassword("");
        invalidUser.setPasswordConfirm("");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError passwordFieldError = errors.getFieldError("password");
        assertThat("password", is(passwordFieldError.getField()));
        List<String> passwordErrorCodes = Arrays.asList(passwordFieldError.getCodes());
        assertThat(4, is(passwordErrorCodes.size()));
        assertTrue(passwordErrorCodes.contains("NotEmpty.invalidUser.password"));
        assertTrue(passwordErrorCodes.contains("NotEmpty.password"));
        assertTrue(passwordErrorCodes.contains("NotEmpty.java.lang.String"));
        assertTrue(passwordErrorCodes.contains("NotEmpty"));

        FieldError passwordConfirmFieldError = errors.getFieldError("passwordConfirm");
        assertThat("passwordConfirm", is(passwordConfirmFieldError.getField()));
        List<String> passwordConfirmErrorCodes = Arrays.asList(passwordConfirmFieldError.getCodes());
        assertThat(4, is(passwordConfirmErrorCodes.size()));
        assertTrue(passwordConfirmErrorCodes.contains("NotEmpty.invalidUser.passwordConfirm"));
        assertTrue(passwordConfirmErrorCodes.contains("NotEmpty.passwordConfirm"));
        assertTrue(passwordConfirmErrorCodes.contains("NotEmpty.java.lang.String"));
        assertTrue(passwordConfirmErrorCodes.contains("NotEmpty"));
    }

    @Test
    public void passwordsDontMatch() {
        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("faplllosss");
        invalidUser.setPassword("faplllosss123");
        invalidUser.setPasswordConfirm("f4plll()sss");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError passwordFieldError = errors.getFieldError("passwordConfirm");
        assertThat("passwordConfirm", is(passwordFieldError.getField()));
        List<String> passwordErrorCodes = Arrays.asList(passwordFieldError.getCodes());
        assertThat(4, is(passwordErrorCodes.size()));
        assertTrue(passwordErrorCodes.contains("Diff.userForm.password.invalidUser.passwordConfirm"));
        assertTrue(passwordErrorCodes.contains("Diff.userForm.password.passwordConfirm"));
        assertTrue(passwordErrorCodes.contains("Diff.userForm.password.java.lang.String"));
        assertTrue(passwordErrorCodes.contains("Diff.userForm.password.passwordConfirm"));

        FieldError passwordConfirmFieldError = errors.getFieldError("passwordConfirm");
        assertThat("passwordConfirm", is(passwordConfirmFieldError.getField()));
        List<String> passwordConfirmErrorCodes = Arrays.asList(passwordConfirmFieldError.getCodes());
        assertThat(4, is(passwordConfirmErrorCodes.size()));
        assertTrue(passwordConfirmErrorCodes.contains("Diff.userForm.passwordConfirm.invalidUser.passwordConfirm"));
        assertTrue(passwordConfirmErrorCodes.contains("Diff.userForm.passwordConfirm.passwordConfirm"));
        assertTrue(passwordConfirmErrorCodes.contains("Diff.userForm.passwordConfirm.java.lang.String"));
        assertTrue(passwordConfirmErrorCodes.contains("Diff.userForm.passwordConfirm.passwordConfirm"));
    }

    @Test
    public void usernameLengthBottomBoundaryTesting() {
        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("fapll");
        invalidUser.setPassword("faplllosss");
        invalidUser.setPasswordConfirm("faplllosss");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError usernameFieldError = errors.getFieldError("username");
        assertThat("username", is(usernameFieldError.getField()));
        List<String> usernameErrorCodes = Arrays.asList(usernameFieldError.getCodes());
        assertThat(4, is(usernameErrorCodes.size()));

        assertTrue(usernameErrorCodes.contains("Size.userForm.username.invalidUser.username"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username.java.lang.String"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username"));

        User validUser = new User();
        validUser.setId(1L);
        validUser.setUsername("faplll");
        validUser.setPassword("faplllosss");
        validUser.setPasswordConfirm("faplllosss");

        Errors errorsValid = new BeanPropertyBindingResult(validUser, "validUser");
        userValidator.validate(validUser, errorsValid);
        assertNull(errorsValid.getFieldError("username"));
    }

    @Test
    public void usernameLengthUpperBoundaryTesting() {
        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("fapllllololololololololollololss2");
        invalidUser.setPassword("faplllosss");
        invalidUser.setPasswordConfirm("faplllosss");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError usernameFieldError = errors.getFieldError("username");
        assertThat("username", is(usernameFieldError.getField()));
        List<String> usernameErrorCodes = Arrays.asList(usernameFieldError.getCodes());
        assertThat(4, is(usernameErrorCodes.size()));

        assertTrue(usernameErrorCodes.contains("Size.userForm.username.invalidUser.username"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username.java.lang.String"));
        assertTrue(usernameErrorCodes.contains("Size.userForm.username"));

        User validUser = new User();
        validUser.setId(1L);
        validUser.setUsername("fapllllololololololololollololss");
        validUser.setPassword("faplllosss");
        validUser.setPasswordConfirm("faplllosss");

        Errors errorsValid = new BeanPropertyBindingResult(validUser, "validUser");
        userValidator.validate(validUser, errorsValid);
        assertNull(errorsValid.getFieldError("username"));
    }
}
