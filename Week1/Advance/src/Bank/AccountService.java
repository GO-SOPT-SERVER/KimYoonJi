package Bank;

import java.util.Objects;
import java.util.Scanner;

import static Bank.Account.*;

public class AccountService {

    static Scanner scan = new Scanner(System.in);
    public static void createAccount() {
        System.out.println("** 계좌 생성을 시작합니다. **");
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("생년월일 6자리를 입력하세요 : ");
        String birth = scan.next();
        System.out.print("비밀번호 4자리를 입력하세요 : ");
        int password = scan.nextInt();
        System.out.print("초기 입금액을 입력하세요 : ");
        Long balance = scan.nextLong();

        Account account = new Account(name, birth, password, balance);


        accounts.add(account);
        idpw.put(account.getAccountNum(), account.getPassword());
        System.out.println("\n--- 계좌 생성이 완료되었습니다. ---\n");
        System.out.println(account);
    }

    public static void accountInfo() {
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.println();

        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                System.out.println("--- " + name + " 님의 계좌 정보입니다. ---\n");
                System.out.println(account);
            }
        }
    }

    public static void deposit() {
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("예금하실 계좌번호를 입력하세요 : ");
        long accountNum = scan.nextLong();
        System.out.print("계좌 비밀번호를 입력하세요 : ");
        int password = scan.nextInt();

        if (idpw.containsKey(accountNum)) {
            if (!idpw.get(accountNum).equals(password)) {
                System.out.println("비밀번호가 틀렸습니다.\n");
            }
            else {
                System.out.print("예금하실 금액을 입력하세요 : ");
                Long amount = scan.nextLong();

                for (Account account : accounts) {
                    if (account.getName().equals(name)) {
                        account.deposit(amount);
                        System.out.println("\n예금이 완료되었습니다.");
                        System.out.println("잔액 : " + account.getBalance() + "\n");
                    }
                }
            }
        }
        else {
                System.out.println("존재하지 않는 계좌입니다.");
            }
    }

    public static void withdraw() {
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("출금하실 계좌번호를 입력하세요 : ");
        long accountNum = scan.nextLong();
        System.out.print("계좌 비밀번호를 입력하세요 : ");
        int password = scan.nextInt();

        if (idpw.containsKey(accountNum)) {
            if (!idpw.get(accountNum).equals(password)) {
                System.out.println("비밀번호가 틀렸습니다.\n");
            }
            else {
                System.out.print("출금하실 금액을 입력하세요 : ");
                Long amount = scan.nextLong();

                for (Account account : accounts) {
                    if (account.getName().equals(name)) {
                        account.withdraw(amount);
                        System.out.println("잔액 : " + account.getBalance() + "\n");
                    }
                }
            }
        }
    }

    public static void remit() {
        System.out.print("이름을 입력하세요 : ");
        String myName = scan.next();
        System.out.print("고객님의 계좌번호를 입력하세요 : ");
        long myAccountNum = scan.nextLong();
        System.out.print("고객님의 계좌 비밀번호를 입력하세요 : ");
        int password = scan.nextInt();

        if (idpw.containsKey(myAccountNum)) {
            if (!idpw.get(myAccountNum).equals(password)) {
                System.out.println("비밀번호가 틀렸습니다.\n");
            }
            else {
                System.out.print("송금 받을 분의 이름을 입력하세요 : ");
                String name = scan.next();
                System.out.print("송금하실 계좌번호를 입력하세요 : ");
                long accountNum = scan.nextLong();
                System.out.print("송금하실 금액을 입력하세요 : ");
                long amount = scan.nextLong();

                for (Account myAccount : accounts) {
                    if (myAccount.getName().equals(myName)) {
                        myAccount.withdraw(amount);
                        System.out.println("\n송금이 완료되었습니다.");
                        System.out.println("나의 잔액 : " + myAccount.getBalance() + "\n");
                    }
                }
                for (Account account : accounts) {
                    if (account.getName().equals(name)) {
                        account.deposit(amount);
                    }
                }
            }
        }
    }

    public static void deleteAccount() {
        System.out.println("** 계좌를 삭제합니다. **");
        System.out.print("이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("고객님의 계좌번호를 입력하세요 : ");
        long accountNum = scan.nextLong();
        System.out.print("삭제할 계좌의 비밀번호 4자리를 입력하세요 : ");
        int password = scan.nextInt();

        if (idpw.containsKey(accountNum)) {
            if (!idpw.get(accountNum).equals(password)) {
                System.out.println("비밀번호가 틀렸습니다.\n");
            } else {
                for (Account account : accounts) {
                    if (account.getName().equals(name)) {
                        accounts.remove(account);
                        System.out.println("\n계좌 삭제가 완료되었습니다.\n");
                        break;
                    }
                }
            }
        }
    }
}
