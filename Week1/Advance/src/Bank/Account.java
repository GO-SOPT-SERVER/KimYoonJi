/*
계좌 정보 : 회원 정보 + 계좌번호, 계좌 비밀번호, 잔액
*/

package Bank;

import java.util.ArrayList;
import java.util.HashMap;

public class Account extends Member {
    static ArrayList<Account> accounts = new ArrayList<>();
    static HashMap<Long, Integer> idpw = new HashMap<>();
    private static Long accountNum = 100L; // 계좌번호 생성
    private final Long realAccNum;
    private final int password; // 계좌 비밀번호
    private Long balance; // 잔액

    public Account(String name, String birth, int password, Long balance) {
        super(name, birth);
        this.password = password;
        this.balance = balance;
        accountNum += 10;
        this.realAccNum = accountNum;
    }

    public Long getAccountNum() {
        return realAccNum;
    }

    public int getPassword() {
        return password;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void deposit(Long amount) {
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            System.out.println("출금이 완료되었습니다.");
        } else {
            System.out.println("Error : 출금 실패! 잔액이 부족합니다.");
        }
    }

    @Override
    public String toString() {
        return "이름 : " + name + "\n생년월일 : " + birth +
                "\n계좌번호 : " + realAccNum + "\n잔액 : " + balance + "\n";
    }

}
