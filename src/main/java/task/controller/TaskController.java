package task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.Mapper.MemberMapper;
import task.dto.MemberPostDto;
import task.entity.Member;
import task.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main")
@Validated
@RequiredArgsConstructor
@Slf4j
public class TaskController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping
    public ResponseEntity joinMember(@RequestBody @Valid MemberPostDto memberPostDto){
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        memberService.createMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
