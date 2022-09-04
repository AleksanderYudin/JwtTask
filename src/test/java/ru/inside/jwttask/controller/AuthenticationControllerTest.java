//package ru.inside.jwttask.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.inside.jwttask.dto.AuthenticationRequestDto;
//import ru.inside.jwttask.security.jwt.JwtTokenProvider;
//import ru.inside.jwttask.service.UserService;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class AuthenticationControllerTest {
//
//    @Autowired
//    private AuthenticationController controller;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void accessAllowedTest() throws Exception {
//
//        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();
//        requestDto.setUsername("john smith");
//        requestDto.setPassword("pass");
//
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                requestDto.getUsername(), requestDto.getPassword()));
//        boolean isAuthenticated = authenticate.isAuthenticated();
//        assertTrue(isAuthenticated);
//
////        MultiValueMapAdapter adapter = new MultiValueMapAdapter(new TreeMap<>());
////        Map<String, String> requestBody = new TreeMap<>();
////        requestBody.put("username", "admin");
////        requestBody.put("password", "pass");
////        this.mockMvc.perform(post("/login")
////                .params(new MultiValueMapAdapter<String, String>(requestBody))
////
//    }
////
////    @Test
////    void accessDeniedTest() throws Exception {
////
////        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();
////        requestDto.setUsername("vladimir");
////        requestDto.setPassword("pass");
////
////        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
////                requestDto.getUsername(), requestDto.getPassword()));
////        boolean isAuthenticated = authenticate.isAuthenticated();
////        assertFalse(isAuthenticated);
////
////    }
//}