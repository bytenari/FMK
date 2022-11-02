package task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.dto.MemberPostDto;
import task.entity.Member;
import task.exception.BusinessLogicException;
import task.exception.ExceptionCode;
import task.repository.MemberRepository;

import java.util.Locale;


@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        agreementYes(member);
        trimmingZero(member);
        codePlusPhone(member);
        return memberRepository.save(member);
    }

    // 동의서가 false이면 예외처리
    public void agreementYes(Member member) {
        if (member.getAgreement() == false) {
            throw new BusinessLogicException(ExceptionCode.AGREEMENT_NOT_VALID);
        }
    }

    // 국가코드에 따라 전화번호 데이터를 처리해야 함.
    // 이탈리아, 산마리노, 바티칸을 제외하고는 전화번호 앞의 0을 제거하는 로직 필요.
    public void trimmingZero(Member member) {
        String phone = member.getPhone();
        if (member.getCountryCode() != "39" ||
                member.getCountryCode() != "3906698" ||
                member.getCountryCode() != "378" &&
            phone.charAt(0) == 0) {
            member.setPhone(phone.substring(1));
        }
    }

    // 국가코드와 전화번호를 합친 데이터를 저장함.
    public void codePlusPhone(Member member) {
        member.setPhoneWithCode(member.getCountryCode() + member.getPhone());
    }

    // 클라이언트단에서 국가코드를 선택하게 하면, 그 정보를 국가번호로 변환하여 저장하는 것이 데이터 통일성을 높일 수 있음
    // ex) 국기와 국가명이 있는 스크롤에서 국가를 선택하게 함. 대한민국(국가코드 KR)이라면 이를 82라는 번호로 변환하여 데이터베이스에 저장.

}

