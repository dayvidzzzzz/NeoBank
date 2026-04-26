package dy.system.minebanconovo.service;

import dy.system.minebanconovo.domain.entity.Account;
import dy.system.minebanconovo.domain.entity.Address;
import dy.system.minebanconovo.domain.entity.User;
import dy.system.minebanconovo.domain.enuns.AccountStatus;
import dy.system.minebanconovo.domain.enuns.AccountType;
import dy.system.minebanconovo.domain.enuns.StatusUser;
import dy.system.minebanconovo.domain.enuns.UserRole;
import dy.system.minebanconovo.dto.AddressResponseDTO;
import dy.system.minebanconovo.dto.UserRegisterDTO;
import dy.system.minebanconovo.dto.UserResponseDTO;
import dy.system.minebanconovo.dto.updateUserRequestDTO;
import dy.system.minebanconovo.exception.BusinessRuleException;
import dy.system.minebanconovo.exception.EmptyRequestException;
import dy.system.minebanconovo.exception.InvalidCpfException;
import dy.system.minebanconovo.infra.utils.CpfValidator;
import dy.system.minebanconovo.repository.AccountRepository;
import dy.system.minebanconovo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dy.system.minebanconovo.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public UserResponseDTO createUser(UserRegisterDTO data){
        if (data==null)
            throw new EmptyRequestException("Request está vazio");

        if (userRepository.existsByCpf(data.cpf()))
            throw new InvalidCpfException("cpf ja ultilizado");

        if (!CpfValidator.isValid(data.cpf()))
            throw new InvalidCpfException("cpf invalido");

        if(userRepository.existsByEmail(data.email()))
            throw new BusinessRuleException("Email ja ultilizado");

        if(userRepository.existsByLogin(data.login()))
            throw new BusinessRuleException("Login ja ultilizado");

        Address address = Address.builder()
                .number(data.addressDTO().number())
                .state(data.addressDTO().state())
                .city(data.addressDTO().city())
                .zipcode(data.addressDTO().zipcode())
                .street(data.addressDTO().street())
                .build();
        addressRepository.save(address);

        User user = User.builder()
                .fullName(data.fullName())
                .cpf(data.cpf())
                .login(data.login())
                .statusUser(StatusUser.ACTIVE)
                .role(UserRole.USER)
                .email(data.email())
                .password(data.password())
                .birthDate(data.birthDate())
                .createdAt(LocalDateTime.now())
                .address(address)
                .build();
        userRepository.save(user);

        Account account = Account.builder()
                .accountType(AccountType.CHECKING)
                .accountStatus(AccountStatus.ACTIVE)
                .user(user)
                .build();
        accountRepository.save(account);

        user.setAccounts(List.of(account));
        return toResponse(user);
    }

    public UserResponseDTO getUserById(String id){
        return toResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public UserResponseDTO updateUser(String id, updateUserRequestDTO data){
        if (data==null)
            throw new EmptyRequestException("Request esta vazio");

        if(userRepository.existsByEmail(data.email()))
            throw new BusinessRuleException("Email ja ultilizado");

        if(userRepository.existsByLogin(data.login()))
            throw new BusinessRuleException("Login ja ultilizado");

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!data.fullName().isEmpty())
            user.setFullName(data.fullName());
        if (!data.login().isEmpty())
            user.setLogin(data.login());
        if (!data.email().isEmpty())
            user.setLogin(data.login());
        if (data.birthDate()!=null)
            user.setBirthDate(data.birthDate());

        return toResponse(userRepository.save(user));
    }

    public void activeAndInactiveUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getStatusUser().equals(StatusUser.ACTIVE))
            user.setStatusUser(StatusUser.INACTIVE);
        else
            user.setStatusUser(StatusUser.ACTIVE);
        userRepository.save(user);
    }

    public void deleteUserById(String id){
        userRepository.delete(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getCpf(),
                user.getLogin(),
                user.getStatusUser(),
                user.getRole(),
                user.getEmail(),
                user.getBirthDate(),
                user.getCreatedAt(),
                new AddressResponseDTO(
                        user.getAddress().getStreet(),
                        user.getAddress().getNumber(),
                        user.getAddress().getCity(),
                        user.getAddress().getState(),
                        user.getAddress().getZipcode())
        );
    }
}
