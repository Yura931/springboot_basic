package com.fastcampus.springboot.ch4;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class UserTestDto {
    @Getter
    @NoArgsConstructor
    // 순수 request parameter 용도. controller layer에서만 쓰임
    public static class UserTestRequest {
        private enum CountryCode {
            KR, EN, FR;
        }

        private UUID id;
        private String phoneNumber;

        // 어노테이션으로 밸리데이션 체크도 함
        @NotNull
        private String email;

        // 여기에서 밸리데이션 체크도 하고, 포멧팅도 추가로 해줄 수 있음
        public UserTestRequest(UUID id, String phoneNumber, String email) {
            this.id = id;
            this.phoneNumber = CountryCode.KR + phoneNumber;
            this.email = email.replace("@", "");
        }
    }

    @RequiredArgsConstructor
    public static class UserTestResponse {
        private final String id;
        private final String phoneNumber;
        private final String email;
    }

    @Getter
    // record를 써도 됨
    // controller에서 받은 데이터를 service layer에 넘기기 직전에 최종적으로 데이터를 받는 DTO
    // 데이터 포멧팅이나 밸리데이션 체크를 모두 마친 데이터가 셋팅되기 때문에 불변객체(setter 사용 불가, 데이터 변경 불가)로
    // 만들어서 데이터 변경을 못하게 제한 함
    //
    // insert, update, delete발생 시 DTO 명을 ~~Command로 해주고, 단순 조회 시에는 ~~Query로 네이밍을 해주는게 약간 국룰
    // 이 예제에서는 이 클래스를 UserTest Entity의 생성자에 넘기는 용도로 씀 : insert
    //
    // Setter는 데이터 변경 또는 오염의 여지가 있기 때문에 불완전해서 애초에 쳐다보지도 않음
    // Builder는 null이 들어갈 것을 허용할 수도 있기 때문에 약간 불완전해서 안 씀
    public static class UserTestCommand {
        private final UUID id;
        private final String phoneNumber;
        private final String email;

        // 검증 로직은 어지간해서는 생성자에서 해주는게 좋음. 검증 로직이 여기저기 분산 안 되도록
        // service layer에서 아래 검증 로직을 수행할 수도 있지만, 지금 이 dto의 데이터에 관련된 내용이기 때문에
        // dto 생성자에서 검증 로직을 수행하는게 맞음
        // 이렇게 service와 dto의 역할을 분리해두면 service layer의 순수성이 증가함 = service는 service의 역할만 할 수 있음
        private UserTestCommand(UUID id, String phoneNumber, String email) {
            if(Objects.isNull(id)) {
                throw new IllegalArgumentException("id가 비었다");
            }

            if(Boolean.FALSE.equals(StringUtils.hasText(phoneNumber))) {
                throw new IllegalArgumentException("phoneNumber가 비었다");
            }

            if(Boolean.FALSE.equals(StringUtils.hasText(email))) {
                throw new IllegalArgumentException("email이 비었다");
            }

            this.id = id;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        // 생성자를 반환하는 메서드명은 보통 of라고 씀. 약간 국룰. 파라미터 개수에 따라서 다르긴 하지만 그냥 of로 통일하는게 편함
        public static UserTestCommand of(UUID id, String phoneNumber, String email) {
            return new UserTestCommand(id, phoneNumber, email);
        }
    }
}
