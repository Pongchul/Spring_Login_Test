package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;                    // DB에 저장 되는 아이디

    @NotEmpty
    private String loginId;             // 회원 로그인 할 때 사용되는 아이디

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
