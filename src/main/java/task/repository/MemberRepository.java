package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByphone(String phone);
}
