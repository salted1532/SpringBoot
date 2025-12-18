package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

/*    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }*/

/*    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + email + '\'' +
                ", content='" + password + '\'' +
                '}';
    }*/
}
