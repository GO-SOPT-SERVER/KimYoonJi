package sopt.org.SecondSeminar.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sopt.org.SecondSeminar.controller.user.MemberController;
import sopt.org.SecondSeminar.controller.user.dto.request.MemberRegisterRequestDto;
import sopt.org.SecondSeminar.controller.user.dto.request.MemberUpdateRequestDto;
import sopt.org.SecondSeminar.controller.user.dto.response.ResponseDto;
import sopt.org.SecondSeminar.domain.Member;

import java.util.ArrayList;

import static sopt.org.SecondSeminar.SecondSeminarApplication.memberList;

@Service
public class MemberService {
    public Long register(MemberRegisterRequestDto request) {
        Member newMember = new Member(
                request.getName(),
                request.getAge(),
                request.getGender(),
                request.getContact(),
                request.isMarriage()
        );

        memberList.add(newMember);
        newMember.setId((long)memberList.size());
        System.out.println(newMember.getId());

        return newMember.getId();
    }

    public Member findOne(Long memberId) {
        // Id로 회원 검색
        Member member = null;

        // memberList에서 입력 받은 memberId를 가진 객체 찾아서 반환
        for (Member targetMember : memberList) {
            if (targetMember.getId().equals(memberId)){
                member = targetMember;
            }
        }
        return member;
    }

    public Member findByName(String name) {
        // 이름으로 회원 검색
        Member member = null;

        for (Member targetMember : memberList) {
            if (targetMember.getName().equals(name)){
                member = targetMember;
            }
        }
        return member;
    }

    public String edit(MemberUpdateRequestDto request) {
        // 받아온 request 데이터를 토대로 수정할 Member 객체 찾아서 저장
        Member targetMember = findByName(request.getName());

        // 회원 정보 수정
        targetMember.setName(request.getName());
        targetMember.setAge(request.getAge());
        targetMember.setGender(request.getGender());
        targetMember.setContact(request.getContact());
        targetMember.setMarriage(request.isMarriage());

        return targetMember.getName();
    }

    public Boolean delete(String name) {
        Member targetMember = findByName(name);

        if (targetMember == null) {
            return false;
        }
        return memberList.remove(targetMember);
    }
}
