package spring.neotodobackend.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="neotodo_todo_list")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ntl_index")
    private Integer index;

    @Comment("할일 내용")
    @Column(name="ntl_title", columnDefinition = "varchar(500) default ''")
    private String title;

    @Comment("할일 체크 여부")
    @Column(name="ntl_progress_status", columnDefinition = "enum('TODO', 'DOING', 'DONE') default 'TODO'")
    private String progressStatus;

    @Comment("할일 상태")
    @Column(name="ntl_status", columnDefinition = "enum('ACTIVE', 'DELETED') default 'ACTIVE'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ntl_user_index", columnDefinition = "int default 0", nullable = false,
            referencedColumnName = "nu_index")
    private User user;

    @Comment("등록일시")
    @Column(name="ntl_register_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'", nullable = false)
    @CreatedDate
    private LocalDateTime registerDatetime;

    @Comment("갱신일시")
    @LastModifiedDate
    @Column(name="ntl_update_datetime", columnDefinition = "datetime default '0000-00-00 00:00:00'")
    private LocalDateTime updateDatetime;
}
