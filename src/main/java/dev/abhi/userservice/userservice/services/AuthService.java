package dev.abhi.userservice.userservice.services;

import dev.abhi.userservice.userservice.dtos.LoginResponseDto;
import dev.abhi.userservice.userservice.dtos.LogoutResponseDto;
import dev.abhi.userservice.userservice.models.Role;
import dev.abhi.userservice.userservice.models.Session;
import dev.abhi.userservice.userservice.models.SessionStatus;
import dev.abhi.userservice.userservice.models.User;
import dev.abhi.userservice.userservice.repo.SessionRepository;
import dev.abhi.userservice.userservice.repo.UserRepository;
import dev.abhi.userservice.userservice.dtos.UserResponseDto;
import dev.abhi.userservice.userservice.utils.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

   private final UserRepository userRepository ;
   private final SessionRepository sessionRepository ;
   private final BCryptPasswordEncoder bCryptPasswordEncoder ;
   private JwtUtil jwtUtil ;

   public AuthService(UserRepository userRepository
           ,SessionRepository sessionRepository
           ,BCryptPasswordEncoder bCryptPasswordEncoder
           ,JwtUtil jwtUtil)
   {
       this.sessionRepository = sessionRepository ;
       this.userRepository = userRepository ;
       this.bCryptPasswordEncoder = bCryptPasswordEncoder ;
       this.jwtUtil = jwtUtil ;
   }

   public UserResponseDto signUp(String name, String email, String password){
       User newUser = new User() ;
       newUser.setName(name);
       newUser.setEmail(email);
       //bCryptPasswordEncoder.encode(password)
       //newUser.setPassword(password);
       newUser.setPassword(bCryptPasswordEncoder.encode(password));
       Role role = new Role() ;
       role.setRole("Mentor");
       role.setRole("ta");
       List<Role> list = new ArrayList<>();
       list.add(role);
       newUser.setRoles(list);
       User savedUser = userRepository.save(newUser) ;
       return UserResponseDto.from(savedUser) ;
   }

   // login :
   public LoginResponseDto login(String email, String password) throws Exception {
       Optional<User> user = userRepository.findByEmail(email);
       if(user.isEmpty()){
           throw new Exception("Invalid email") ;
       }
       User savedUser = user.get() ;
       if(!bCryptPasswordEncoder.matches(password,savedUser.getPassword())){
           throw new Exception("Invalid password") ;
       }

       String jwsToken = JwtUtil.generateToken(savedUser);

       Session session = new Session();
       session.setUser(savedUser);
       //session.setToken(RandomStringUtils.randomAlphanumeric(30));
       session.setToken(jwsToken);
       session.setSessionStatus(SessionStatus.ACTIVE);

       Session savedSession = sessionRepository.save(session) ;

       LoginResponseDto loginResponseDto = new LoginResponseDto() ;
       loginResponseDto.setName(savedUser.getName());
       loginResponseDto.setEmail(savedUser.getEmail());
       loginResponseDto.setToken(savedSession.getToken());
       loginResponseDto.setSessionStatus(savedSession.getSessionStatus().toString());
       return loginResponseDto ;
   }

   public LogoutResponseDto logout(Long sessionId){
       Optional<Session> optionalSession = sessionRepository.findById(sessionId) ;
       //System.out.println("sessionId : " + sessionId);
       if(optionalSession.isEmpty()){
           System.out.println("Invalid Session");
           return null ;
       }

       Session session = optionalSession.get() ;
       session.setSessionStatus(SessionStatus.ENDED);
       sessionRepository.save(session);

       LogoutResponseDto logoutResponseDto = new LogoutResponseDto() ;
       logoutResponseDto.setEmail(session.getUser().getEmail());
       logoutResponseDto.setName(session.getUser().getName());
       logoutResponseDto.setSessionId(session.getId());
       logoutResponseDto.setSessionStatus(session.getSessionStatus().toString());

       return logoutResponseDto ;

   }

   public SessionStatus validateToken(String jwsToken,Long userId){

       Optional<Session> optionalSession =
               sessionRepository.findByTokenAndUserId(jwsToken,userId) ;

       if(optionalSession.isEmpty()){
           return SessionStatus.ENDED ;
       }

       Session session = optionalSession.get();
       if(session.getSessionStatus() != SessionStatus.ACTIVE){
           return SessionStatus.ENDED;
       }
       return JwtUtil.validateTokenLocally(jwsToken,userId);
   }
}
