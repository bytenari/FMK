package task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponseDto {

    private Long memberId;
    private String username;
    private String email;
    private String phoneWithCode;
    private Boolean agreement;

}
