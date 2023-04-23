package sopt.org.SecondSeminar.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.user.dto.request.MemberRegisterRequestDto;
import sopt.org.SecondSeminar.controller.user.dto.request.MemberUpdateRequestDto;
import sopt.org.SecondSeminar.controller.user.dto.response.ResponseDto;
import sopt.org.SecondSeminar.service.MemberService;

import static sopt.org.SecondSeminar.SecondSeminarApplication.memberList;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/member")
    public String register(@RequestBody final MemberRegisterRequestDto request){
        Long memberId = memberService.register(request);
        System.out.println(memberList.get(memberId.intValue() - 1).toString());

        return memberId + "번 회원이 등록되었습니다!";
    }

    @GetMapping("/member/{memberId}")
    public ResponseDto<String> getOneMember(@PathVariable final Long memberId) {
        if (memberService.findOne(memberId) == null) { // 예외 처리 404
            System.out.println("존재하지 않는 회원입니다.");
            return new ResponseDto<String>(HttpStatus.NOT_FOUND.value(), "404 NOT_FOUND");
        }

        System.out.println("회원 조회에 성공했습니다.");
        System.out.println(memberService.findOne(memberId).toString());

        return new ResponseDto<String>(HttpStatus.OK.value(), "200 OK!");
    }

    @PutMapping("/member")
    public String editMember(@RequestBody MemberUpdateRequestDto request) {
        String memberName = memberService.edit(request);
        System.out.println(memberService.findByName(memberName).toString());
        return memberName + "님 회원 정보 수정이 완료되었습니다.";
    }

    @DeleteMapping("/member/delete")
    public ResponseDto delete(@RequestParam final String name) {
        Boolean isDeleted = memberService.delete(name);
        if (isDeleted == false) { // 예외처리 404
            System.out.println("존재하지 않는 회원입니다!");
            return new ResponseDto<String>(HttpStatus.NOT_FOUND.value(), "404 NOT_FOUND");
        }
        System.out.println(name + "회원 정보 삭제가 완료되었습니다.");
        return new ResponseDto<String>(HttpStatus.OK.value(), "200 OK!");
    }
}
