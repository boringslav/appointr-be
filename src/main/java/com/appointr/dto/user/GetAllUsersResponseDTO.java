package com.appointr.dto.user;

import com.appointr.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponseDTO {
    private List<UserDTO> users;
}
