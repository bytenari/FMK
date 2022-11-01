package task.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task.Mapper.MemberMapper;
import task.dto.MemberPostDto;
import task.entity.Member;
import task.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOError;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping
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

    @RequestMapping()
    public ResponseEntity<Object> main() throws URISyntaxException {
        URI redirectUri = new URI("http://fmktask.s3-website.ap-northeast-2.amazonaws.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}


