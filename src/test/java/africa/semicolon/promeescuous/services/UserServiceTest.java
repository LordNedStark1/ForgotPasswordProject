package africa.semicolon.promeescuous.services;


import africa.semicolon.promeescuous.dto.response.RegistrationResponse;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static africa.semicolon.promeescuous.utils.AppUtil.BLANK_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testThatUserCanRegister(){
        RegistrationResponse response = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", response.getMessage());
    }
    @Test
    public void testForgotPassword(){
        RegistrationResponse regResponse = userService.register("ned@gmail.com", "12345");
        assertEquals("Registration successful", regResponse.getMessage());

        ForgotPasswordResponse forgotPasswordResponse = userService.forgetPassword(regResponse.getEmail());

    }





}



