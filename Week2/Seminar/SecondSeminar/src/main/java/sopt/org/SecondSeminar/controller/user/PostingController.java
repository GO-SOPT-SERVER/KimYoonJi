package sopt.org.SecondSeminar.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.user.dto.request.PostingRegisterRequestDto;
import sopt.org.SecondSeminar.service.PostingService;
import sopt.org.SecondSeminar.service.UserService;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postingList;
import static sopt.org.SecondSeminar.SecondSeminarApplication.userList;

@RestController
@RequiredArgsConstructor
public class PostingController {
    private final PostingService postingService;

    @PostMapping("/posting")
    public String register(@RequestBody final PostingRegisterRequestDto request) {
        // 서비스 계층에 게시물을 등록하는 메서드를 호출
        Long postingId = postingService.register(request); //1
        System.out.println(postingId);
        System.out.println(postingList.get(postingId.intValue() - 1).toString());

        return postingId + "번 게시물이 등록되었습니다!";
    }

    @GetMapping("/posting/{postingId}")
    public String getOne(@PathVariable final Long postingId) {
        System.out.println("요청 게시물 아이디: " + postingId);

        // 서비스 계층에서 게시물 아이디로 게시물을 찾는 메서드 호출
        return "게시물 조회 성공";
    }

    @GetMapping("/posting/searchPost")
    public String searchPost(@RequestParam final String title) {
        System.out.println("게시물 이름 검색 인자: " + title);

        // 서비스 계층에서 게시물 이름으로 게시물을 찾는 메서드 호출
        return "게시물 검색 성공";
    }


}
