package thespeace.member;

import lombok.Data;

/**
 * <h1>간단한 회원 객체</h1>
 */
@Data
public class Member {

    private String memberId;
    private String name;

    public Member() {
    }

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}
