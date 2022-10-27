package task.Mapper;
import org.mapstruct.Mapper;
import task.dto.MemberPostDto;
import task.entity.Member;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    default Member memberPostDtoToMember(MemberPostDto memberPostDto) {
        String phoneWithCode = memberPostDto.getCountryCode() + memberPostDto.getPhone();
        Member member = new Member(
                memberPostDto.getUsername(),
                memberPostDto.getEmail(),
                memberPostDto.getAgreement(),
                phoneWithCode
        );

        return member;
    }
}
