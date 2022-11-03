package task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.Mapper.MemberMapper;
import task.dto.MemberPostDto;
import task.dto.MultiResponseDto;
import task.dto.SingleResponseDto;
import task.entity.Member;
import task.service.MemberService;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


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

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") @Positive Long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(memberMapper.memberToMemberResponse(member))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.membersToMemberResponses(members),
                        pageMembers),
                HttpStatus.OK);
    }
}


