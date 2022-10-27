package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
