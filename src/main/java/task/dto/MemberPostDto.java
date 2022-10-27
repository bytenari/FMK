package task.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
public class MemberPostDto {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 15, message = "Name should be within the range of 2 to 15 letters.")
    private String username;

    @Email(message = "Not a proper form of email.")
    @NotBlank(message = "Email cannot be blank.")
    private String email;

    @NotBlank(message = "Country Code cannot be blank.")
    private String countryCode;

    @NotBlank(message = "Phone number cannot be blank.")
    private String phone;

    @NotBlank(message = "Phone number cannot be blank.")
    private String agreement;

    
}
