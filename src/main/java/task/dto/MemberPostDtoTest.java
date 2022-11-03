package task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class MemberPostDtoTest { // 테스트를 위해 추가됨

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

    @NotNull(message = "Only 'true' would be taken as answer to apply for the event.")
    private Boolean agreement;
}
