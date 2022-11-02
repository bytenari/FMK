package task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 15, nullable = false, updatable = false)
    @NotBlank
    @Size
    private String username;

    @Column(nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String countryCode;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String phone;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String phoneWithCode;

    @NotNull
    @Column(nullable = false)
    private Boolean agreement;

//    public Member(String username, String email, String countryCode, String phone, String phoneWithCode, Boolean agreement) {
//        this.username = username;
//        this.email = email;
//        this.countryCode = countryCode;
//        this.phone = phone;
//        this.agreement = agreement;
//        this.phoneWithCode = phoneWithCode;
//    }
}
