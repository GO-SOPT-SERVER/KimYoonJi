package sopt.org.SecondSeminar.service;

import org.springframework.stereotype.Service;
import sopt.org.SecondSeminar.controller.user.dto.request.PostingRegisterRequestDto;
import sopt.org.SecondSeminar.domain.Posting;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postingList;

@Service
public class PostingService {
    public Long register(PostingRegisterRequestDto request) {

        // 받아온 request 데이터를 토대로 실제 Posting 객체 생성
        Posting newPosting = new Posting(
                request.getTitle(),
                request.getContext(),
                request.getWriter(),
                request.getTime()
        );

        postingList.add(newPosting); //1
        newPosting.setId((long) postingList.size()); //1

        System.out.println(newPosting.getId());

        // 저장한 게시물 아이디 값 반환
        return newPosting.getId();
    }
}
