package task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.dto.MemberPostDto;
import task.entity.Member;
import task.exception.BusinessLogicException;
import task.exception.ExceptionCode;
import task.repository.MemberRepository;

import java.util.Locale;
import java.util.Optional;


@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        agreementYes(member);
        verifyExistEmail(member.getEmail());
        trimmingZero(member);
        codePlusPhone(member);
        verifyExistPhone(member.getPhoneWithCode());
        return memberRepository.save(member);
    }


    // 동의서가 false이면 예외처리
    public void agreementYes(Member member) {
        if (member.getAgreement() == false) {
            throw new BusinessLogicException(ExceptionCode.AGREEMENT_NOT_VALID);
        }
    }

    //존재하는 email인지 확인
    public void verifyExistEmail(String email) {
        Optional<Member> checkEmail = memberRepository.findByEmail(email);

        if (checkEmail.isPresent())
            throw new BusinessLogicException(ExceptionCode.EMAIL_EXISTS);
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

    // 존재하는 번호인지 확인. 이미 유효성검사에서 unique 설정으로 걸러지기는 하지만, unique 설정을 해제할 경우 필요함.
    public void verifyExistPhone(String phone) {
        Optional<Member> checkPhone = memberRepository.findByEmail(phone);

        if (checkPhone.isPresent())
            throw new BusinessLogicException(ExceptionCode.PHONE_EXISTS);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId")));
    }
}

