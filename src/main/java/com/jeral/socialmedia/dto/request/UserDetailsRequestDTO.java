package com.jeral.socialmedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDTO {
    private UserRequestDTO userRequestDTO;
    private DetailsRequestDTO detailsRequestDTO;
}
