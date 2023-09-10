package spring.neotodobackend.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="neotodo_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nu_index")
    private Integer index;

    @Comment("아이디")
    @Column(name = "nu_index", columnDefinition = "varchar(50) default''", nullable = false)
    private String id;

    @Comment("비밀번호")
    @Column(name = "nu_password", columnDefinition = "varchar(500) default ''", nullable = false)
    private String password;

    @Comment("닉네임")
    @Column(name = "nu_nickname", columnDefinition = "varchar(100) default ''", nullable = false)
    private String nickname;

    @Comment("유저 상태")
    @Column(name="nu_status", columnDefinition = "enum('ACTIVE', 'DELETED') default 'ACTIVE'")
    private String status;

    @Comment("등록일시")
    @Column(name="nu_register_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'", nullable = false)
    @CreatedDate
    private LocalDateTime registerDatetime;

    @Comment("갱신일시")
    @LastModifiedDate
    @Column(name="nu_update_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'")
    private LocalDateTime updateDatetime;
}
