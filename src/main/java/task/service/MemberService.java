package task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.entity.Member;
import task.repository.MemberRepository;


@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }
}
