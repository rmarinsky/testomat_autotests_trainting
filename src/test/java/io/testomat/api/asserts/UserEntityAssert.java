package io.testomat.api.asserts;

import io.testomat.testmanagement.dao.entities.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEntityAssert {

    private final UserEntity userEntity;

    public UserEntityAssert(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UserEntityAssert hasId(int expectedId) {
        assertThat(userEntity.id)
                .withFailMessage("Expected id to be %d but was %d", expectedId, userEntity.id)
                .isEqualTo(expectedId);
        return this;
    }

    public UserEntityAssert hasName(String expectedName) {
        assertThat(userEntity.name)
                .withFailMessage("Expected name to be %s but was %s", expectedName, userEntity.name)
                .isEqualTo(expectedName);
        return this;
    }

    public UserEntityAssert hasEmail(String expectedEmail) {
        assertThat(userEntity.email)
                .withFailMessage("Expected email to be %s but was %s", expectedEmail, userEntity.email)
                .isEqualTo(expectedEmail);
        return this;
    }

    public UserEntityAssert hasPassword(String expectedPassword) {
        assertThat(userEntity.password)
                .withFailMessage("Expected password to be %s but was %s", expectedPassword, userEntity.password)
                .isEqualTo(expectedPassword);
        return this;
    }

    public UserEntityAssert hasRole(String expectedRole) {
        assertThat(userEntity.role)
                .withFailMessage("Expected role to be %s but was %s", expectedRole, userEntity.role)
                .isEqualTo(expectedRole);
        return this;
    }

}
