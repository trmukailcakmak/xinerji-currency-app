package com.xinerji.currency.service.impl;

import com.xinerji.currency.TestDataPool;
import com.xinerji.currency.constant.MessageKey;
import com.xinerji.currency.exceptions.XinerjiException;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Role;
import com.xinerji.currency.model.entity.Users;
import com.xinerji.currency.model.mapper.LoginMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.repository.RoleRepository;
import com.xinerji.currency.repository.UserRepository;
import com.xinerji.currency.security.JWTGenerator;
import com.xinerji.currency.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private final UserMapper userMapper = UserMapper.INSTANCE;
    @Mock
    private final LoginMapper loginMapper = LoginMapper.INSTANCE;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    private UserService userService;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MessageSource messageSource;
    @Mock
    private JWTGenerator jwtGenerator;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userServiceImpl).build();
    }

    @After
    public void tearDown() {
    };

    @Test
    public void testLogin() {
        Authentication authentication = Mockito.mock(Authentication.class);

        AuthRequestDto requestDto = TestDataPool.createAuthRequestDto();
        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(true);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(jwtGenerator.generateToken(Mockito.any())).thenReturn("test-generate-token");
        AuthResponseDto responseDto = userServiceImpl.login(requestDto);
        Assert.assertNotNull(responseDto);
    }

    @Test
    public void testLogin_whenerrorcode01() {
        AuthRequestDto requestDto = TestDataPool.createAuthRequestDto();
        Mockito.when(userRepository.existsByEmail(Mockito.any())).thenReturn(false);
        XinerjiException exception = Assert.assertThrows(XinerjiException.class, () -> {
            userServiceImpl.login(requestDto);
        });

        Assert.assertEquals(MessageKey.ERR01, exception.getCode());
    }

    @Test
    public void testRegister() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        Users user = TestDataPool.createUser();
        Role role = TestDataPool.createRoleUser();

        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByPhone(Mockito.anyString())).thenReturn(false);
        Mockito.when(userMapper.mapRequestDtoToEntity(Mockito.any(UserRequestDto.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("test-password");
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(role));
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);

        UserResponseDto responseDto = userServiceImpl.register(requestDto);

        Assert.assertNotNull(responseDto);
    }

    @Test
    public void testRegister01() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        XinerjiException exception = Assert.assertThrows(XinerjiException.class, () -> {
            // Act
            userServiceImpl.register(requestDto);
        });

        assertEquals(MessageKey.ERR02, exception.getCode());
    }

    @Test
    public void testRegister02() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByPhone(Mockito.anyString())).thenReturn(true);
        XinerjiException exception = Assert.assertThrows(XinerjiException.class, () -> {
            // Act
            userServiceImpl.register(requestDto);
        });

        Assert.assertEquals(MessageKey.ERR03, exception.getCode());
    }

    @Test
    public void testRegister03() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        requestDto.setEmail(null);
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(userRepository.existsByPhone(Mockito.anyString())).thenReturn(false);
        XinerjiException exception = Assert.assertThrows(XinerjiException.class, () -> {
            // Act
            userServiceImpl.register(requestDto);
        });

        Assert.assertEquals(MessageKey.ERR02, exception.getCode());
    }
    /*@Test(expected = XinerjiException.class)
    public void testRegister04() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        requestDto.setPhone(null);
        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByPhone(Mockito.anyString())).thenReturn(true);
        // Act
        userServiceImpl.register(requestDto);
    }*/

    @Test
    public void testRegisterAdmin() {
        UserRequestDto requestDto = TestDataPool.createUserRequestDto();
        Users user = TestDataPool.createUser();
        Role role = TestDataPool.createRoleAdmin();

        Mockito.when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(userRepository.existsByPhone(Mockito.anyString())).thenReturn(false);
        Mockito.when(userMapper.mapRequestDtoToEntity(Mockito.any(UserRequestDto.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("test-password");
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(role));
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);

        UserResponseDto responseDto = userServiceImpl.register(requestDto);

        Assert.assertNotNull(responseDto);
    }
    @Test
    public void testFindUserById() {
        Long id = 1l;
        Users user = TestDataPool.createUser();
        UserResponseDto responseDto = TestDataPool.createUserResponseDto();
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapEntityToResponseDto(Mockito.any(Users.class))).thenReturn(responseDto);
        UserResponseDto responseService = userServiceImpl.findUserById(id);
        Assert.assertNotNull(responseService);
    }

    @Test
    public void testFindUserById02() {
        Users users = TestDataPool.createUser();
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        XinerjiException exception = Assert.assertThrows(XinerjiException.class, () -> {
            // Act
            userServiceImpl.findUserById(1l);
        });

        Assert.assertEquals(MessageKey.ERR01, exception.getCode());
    }

    @Test
    public void testGetUserInfo() {
        Users user = TestDataPool.createUser();
        UserResponseDto responseDto = TestDataPool.createUserResponseDto();
        Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapEntityToResponseDto(Mockito.any(Users.class))).thenReturn(responseDto);
        UserResponseDto responseService = userServiceImpl.getUserInfo("test-key");
        Assert.assertNotNull(responseService);
    }
}