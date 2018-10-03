package pl.java.spring.web.project.clientappproject.login.controller;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userAlreadyExists() {
        when(userServiceMock.findByUsername("faplllosss")).thenReturn(new User());

        User duplicateUser = new User();
        duplicateUser.setId(1L);
        duplicateUser.setUsername("faplllosss");
        duplicateUser.setPassword("faplllosss123");
        duplicateUser.setPasswordConfirm("faplllosss123");

        Errors errors = new BeanPropertyBindingResult(duplicateUser, "duplicateUser");
        userValidator.validate(duplicateUser, errors);

        FieldError usernameFieldError = errors.getFieldError("username");
        assertThat("username", is(usernameFieldError.getField()));
        List<String> errorCodes = Arrays.asList(usernameFieldError.getCodes());
        assertThat(4, is(errorCodes.size()));
        assertTrue(errorCodes.contains("Duplicate.userForm.username.duplicateUser.username"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username.username"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username.java.lang.String"));
        assertTrue(errorCodes.contains("Duplicate.userForm.username"));
    }
}
