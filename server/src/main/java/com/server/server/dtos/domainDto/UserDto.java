package com.server.server.dtos.domainDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private String uuid;
    private String email;
    private String password;
    private String name;
    private String plan;
    private String token;

    private LocalDateTime created;

    @JsonProperty("is_valid")
    private Boolean isValid;
}
