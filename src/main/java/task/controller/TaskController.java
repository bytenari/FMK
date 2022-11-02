package task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @PostMapping
    public ResponseEntity applyEvent(@RequestBody @Valid MemberPostDto memberPostDto){
        Member member = memberMapper.memberPostDtoToMember(memberPostDto);
        memberService.createMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}


