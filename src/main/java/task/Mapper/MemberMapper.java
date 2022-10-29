package task.Mapper;
import org.mapstruct.Mapper;
import task.dto.MemberPostDto;
import task.dto.MemberPostDtoTest;
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

    default Member memberPostDtoTestToMember(MemberPostDtoTest memberPostDtoTest) {
        String phoneWithCode = memberPostDtoTest.getCountryCode() + memberPostDtoTest.getPhone();
        Member member = new Member(
                memberPostDtoTest.getUsername(),
                memberPostDtoTest.getEmail(),
                memberPostDtoTest.getAgreement(),
                phoneWithCode
        );

        return member;
    }
}
