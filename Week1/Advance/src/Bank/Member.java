/*
회원 정보 : 회원 ID, 회원 이름, 회원 생년월일
*/

package Bank;

import java.util.ArrayList;

public class Member {
    static ArrayList<Member> members = new ArrayList<>();
    protected String name; // 회원 이름
    protected final String birth; // 회원 생년월일

    public Member(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "\n이름 : " + name + "\n생년월일 : " + birth + "\n";
    }
}
