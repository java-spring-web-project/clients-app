package pl.java.spring.web.project.clientappproject.login.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserValidatorMockTest {

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserService userServiceMock;

    @Test
    public void userAlreadyExists() {
        when(userServiceMock.findByUsername("faplllosss")).thenReturn(new User());

        User invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setUsername("faplllosss");
        invalidUser.setPassword("faplllosss123");
        invalidUser.setPasswordConfirm("faplllosss123");

        Errors errors = new BeanPropertyBindingResult(invalidUser, "invalidUser");
        userValidator.validate(invalidUser, errors);

        FieldError usernameFieldError = errors.getFieldError("username");
        assertEquals("username", usernameFieldError.getField());
        List<String> errorCodes = Arrays.asList(usernameFieldError.getCodes());
        assertEquals(4, errorCodes.size());
        assertTrue(errorCodes.contains("Duplicate.userForm.username.invalidUser.username"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username.username"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username.java.lang.String"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username"));
    }
}
