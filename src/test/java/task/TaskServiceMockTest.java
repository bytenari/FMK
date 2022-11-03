package task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import task.entity.Member;
import task.exception.BusinessLogicException;
import task.exception.ExceptionCode;
import task.repository.MemberRepository;
import task.service.MemberService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TaskServiceMockTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    public void createMemberTest() {
        // given
        Member member = new Member(1L, "염빛나리", "bytenari@gmail.com", "82", "01053163253", "8201053163253", true);

        given(memberRepository.findByEmail(member.getEmail()))
                .willReturn(Optional.of(member));

        assertThrows(BusinessLogicException.class, () -> memberService.createMember(member));
    }
}
