package org.zerock.mreview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "m_member") // m_member 안하면 충돌 일어날 가능성 있음
public class Member extends BaseEntity { // 회원(고유한 번호, 이메일, 아이디, 패스워드, 닉네임). >회원과 로그인에 대한 처리는 스프링 시큐리티로 함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String email;
    private String pw;
    private String nickname;
}
