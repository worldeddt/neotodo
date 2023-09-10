package spring.neotodobackend.security.provider;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.neotodobackend.core.exceptions.CommonException;
import spring.neotodobackend.core.infra.UserMemberDto;
import spring.neotodobackend.domain.UserDomain;
import spring.neotodobackend.domain.entity.User;
import spring.neotodobackend.domain.enums.UserStatus;
import spring.neotodobackend.infra.UserRepository;

import static spring.neotodobackend.core.error.enums.BadRequestCode.NOT_FOUND_USER;


@Service
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user  = userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE.name())
                .orElseThrow(() -> CommonException.init(NOT_FOUND_USER));

        return UserMemberDto.init(UserDomain.init(user));
    }
}
