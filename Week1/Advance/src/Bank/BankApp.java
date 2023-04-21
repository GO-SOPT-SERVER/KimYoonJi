package Bank;

import java.util.ArrayList;
import java.util.Scanner;

public class BankApp {
    private static final Scanner scan = new Scanner(System.in);

    private boolean isExit;

    public void run() {
        do {
            System.out.println("***** 안녕하세요, 솝트은행입니다. *****\n");

            System.out.println("1. 회원 가입");
            System.out.println("2. 회원 정보 조회");
            System.out.println("3. 계좌 개설");
            System.out.println("4. 계좌 정보 조회");
            System.out.println("5. 예금");
            System.out.println("6. 출금");
            System.out.println("7. 송금");
            System.out.println("8. 계좌 삭제");
            System.out.println("9. 종료");

            System.out.print("\n원하시는 서비스 번호를 입력해주세요 : ");
            int service = Integer.parseInt(scan.nextLine());
            System.out.println();

            switch (service) {
                case 1:
                    MemberService.join();
                    break;
                case 2:
                    MemberService.memberInfo();
                    break;
                case 3:
                    AccountService.createAccount();
                    break;
                case 4:
                    AccountService.accountInfo();
                    break;
                case 5:
                    AccountService.deposit();
                    break;
                case 6:
                    AccountService.withdraw();
                    break;
                case 7:
                    AccountService.remit();
                    break;
                case 8:
                    AccountService.deleteAccount();
                    break;
                case 9:
                    System.out.println("서비스를 종료합니다.");
                    isExit = true;
                    break;
            }
        } while(!isExit);
    }

    public static void main(String[] args) {
        BankApp Sopt = new BankApp();
        Sopt.run();
    }
}
