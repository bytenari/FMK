package task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    private String username;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneWithCode;

    @Column(nullable = false)
    private String agreement;

    public Member(String username, String email, String agreement, String phoneWithCode) {
        this.username = username;
        this.email = email;
        this.agreement = agreement;
        this.phoneWithCode = phoneWithCode;
    }
}
