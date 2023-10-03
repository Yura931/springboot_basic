package com.fastcampus.springboot.ch4;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_test")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTest {

    @Id
    @Column(name = "user_test_id")
    private UUID id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_email")
    private String email;

    // request dto -> command dto(불변객체)를 거치며 모든 검증이 확실히 끝난 객체의 데이터를 받기 때문에 안전함
    public UserTest(UserTestDto.UserTestCommand userTestCommand) {
        this.id = userTestCommand.getId();
        this.phoneNumber = userTestCommand.getPhoneNumber();
        this.email = userTestCommand.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        UserTest that = (UserTest) o;
        return this.id.equals(that.id) && this.email.equals(that.email) && this.phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "UserTest{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
