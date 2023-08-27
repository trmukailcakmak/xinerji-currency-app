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
            throw new XinerjiException(MessageKey.ERR01, this.messageSource.getMessage(MessageKey.ERR01, null, Locale.ENGLISH));
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
            throw new XinerjiException(MessageKey.ERR01,this.messageSource.getMessage(MessageKey.ERR01,null, Locale.ENGLISH));
                });
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
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new XinerjiException(MessageKey.ERR07, this.messageSource.getMessage(MessageKey.ERR07, null, Locale.ENGLISH));
        }
        if (userRequestDto.getPhone() != null && userRepository.existsByPhone(userRequestDto.getPhone())) {
            throw new XinerjiException(MessageKey.ERR08, this.messageSource.getMessage(MessageKey.ERR08, null, Locale.ENGLISH));
        }
        if ((userRequestDto.getEmail() == null || userRequestDto.getEmail().equals("") || userRequestDto.getEmail().equals(" "))) {
            throw new XinerjiException(MessageKey.ERR09, this.messageSource.getMessage(MessageKey.ERR09, null, Locale.ENGLISH));
        }
        if (userRequestDto.getName() == null) {
            throw new XinerjiException(MessageKey.ERR010, this.messageSource.getMessage(MessageKey.ERR010, null, Locale.ENGLISH));
        }

        Users user = userMapper.mapRequestDtoToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode((userRequestDto.getPassword())));

        Role roles = roleRepository.findByName(String.valueOf(roleEnum)).get();
        user.setRoles(Collections.singletonList(roles));

        user = userRepository.save(user);
        UserResponseDto responseDto = userMapper.mapEntityToResponseDto(user);
        return responseDto;
    }
}
