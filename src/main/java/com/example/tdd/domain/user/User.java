package com.example.tdd.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
//@Setter 엔티티는 불변 지향
//@Table(name=~)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;

    private String email;

    // 빌더를 통한 객체 생성 - 객체를 생성할 때 필요한 데이터를 명시적으로 설정, 객체의 불변성 유지
    // 객체 생성과정에서만 필드 생성하고, 이후에 상태변경을 허용하지 않음.
    @Builder
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

}
