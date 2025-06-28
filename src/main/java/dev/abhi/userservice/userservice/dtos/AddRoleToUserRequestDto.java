package dev.abhi.userservice.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRoleToUserRequestDto {
    private Long userId ;
    private String roleName ;
}
