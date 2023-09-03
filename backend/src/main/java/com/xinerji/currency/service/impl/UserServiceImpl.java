package com.xinerji.currency.service.impl;

import com.xinerji.currency.constant.MessageKey;
import com.xinerji.currency.exceptions.XinerjiException;
import com.xinerji.currency.model.dto.auth.AuthResponseDto;
import com.xinerji.currency.model.dto.auth.AuthRequestDto;
import com.xinerji.currency.model.dto.user.UserRequestDto;
import com.xinerji.currency.model.dto.user.UserResponseDto;
import com.xinerji.currency.model.entity.Role;
import com.xinerji.currency.model.entity.Users;
import com.xinerji.currency.model.mapper.LoginMapper;
import com.xinerji.currency.model.mapper.UserMapper;
import com.xinerji.currency.model.type.RoleEnum;
import com.xinerji.currency.repository.RoleRepository;
import com.xinerji.currency.repository.UserRepository;
import com.xinerji.currency.security.JWTGenerator;
import com.xinerji.currency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.xinerji.currency.model.type.RoleEnum.ADMIN;
import static com.xinerji.currency.model.type.RoleEnum.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final LoginMapper loginMapper= LoginMapper.INSTANCE;

    private final UserMapper userMapper= UserMapper.INSTANCE;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final MessageSource messageSource;

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        if (!userRepository.existsByEmail(authRequestDto.getUsername())) {
            throwException(MessageKey.ERR01, Locale.ENGLISH);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(),
                        authRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthResponseDto authResponseDto = new AuthResponseDto(jwtGenerator.generateToken(authentication), authRequestDto.getUsername());
        return authResponseDto;
    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        return savePerson(userRequestDto, USER);
    }

    @Override
    public UserResponseDto registerAdmin(UserRequestDto userRequestDto) {
        return savePerson(userRequestDto, ADMIN);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        Users user = userRepository.findById(id).orElseThrow(() -> {
            throw new XinerjiException(MessageKey.ERR01,this.messageSource.getMessage(MessageKey.ERR01,null, Locale.ENGLISH));});

        UserResponseDto responseDto = userMapper.mapEntityToResponseDto(user);
        return responseDto;
    }

    @Override
    public UserResponseDto getUserInfo(String key) {
        Users user = userRepository.findByEmail(key).get();
        UserResponseDto responseDto = userMapper.mapEntityToResponseDto(user);
        return responseDto;
    }

    private UserResponseDto savePerson(UserRequestDto userRequestDto, RoleEnum roleEnum) {
        nullControl(userRequestDto.getEmail(), MessageKey.ERR04);
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throwException(MessageKey.ERR02,Locale.ENGLISH);
        }
        if (userRequestDto.getPhone() != null && userRepository.existsByPhone(userRequestDto.getPhone())) {
            throwException(MessageKey.ERR03,Locale.ENGLISH);
        }

        Users user = userMapper.mapRequestDtoToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode((userRequestDto.getPassword())));
        List<Role> find = roleRepository.findAll();
        ifRoleTableNull(find);
        Role roles = roleRepository.findByName(String.valueOf(roleEnum)).get();
        user.setRoles(Collections.singletonList(roles));
        user.setDeleted(false);
        user = userRepository.save(user);
        UserResponseDto responseDto = userMapper.mapEntityToResponseDto(user);
        return responseDto;
    }

    private void ifRoleTableNull(List<Role> find) {
        if (find.isEmpty() || find ==  null) {
            Role r1 = new Role();
            r1.setName("USER");
            roleRepository.save(r1);
            Role r2 = new Role();
            r2.setName("ADMIN");
            roleRepository.save(r2);
        }
    }

    private void throwException(String errCode, Locale locale) {
        throw new XinerjiException(errCode, this.messageSource.getMessage(errCode, null, locale));
    }

    private void nullControl(String s, String errCode) {
        s.trim();
        if ((s == null || s.equals("") || s.equals(" "))) {
            throwException(errCode, Locale.ENGLISH);
        }
    }
}
