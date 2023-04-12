package Bank;

import java.util.Scanner;

import static Bank.Member.members;

public class MemberService {
    static Scanner scan = new Scanner(System.in);
    public static void join() {
        System.out.println("** 회원 가입을 시작합니다. **");
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("생년월일 6자리를 입력하세요 : ");
        String birth = scan.next();
        Member member = new Member(name, birth);


        members.add(member);
        System.out.println("\n--- 회원 가입이 완료되었습니다. ---");
        System.out.println(member);
    }
    public static void memberInfo() {
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.println();

        for (Member member : members) {
            if (member.getName().equals(name)) {
                System.out.println("--- " + name + " 님의 회원 정보입니다. ---");
                System.out.println(member);
            }
        }
    }
}
