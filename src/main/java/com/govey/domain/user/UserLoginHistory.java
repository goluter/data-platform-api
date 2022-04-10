package com.govey.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class UserLoginHistory {
    private LocalDate created_at;
    private LocalDate updated_at;
    private LocalDate deleted_at;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user_id;

    private String ip;
    private String header;
    private String timezone;

    @Builder
    public UserLoginHistory(LocalDate created_at,
                            LocalDate updated_at,
                            LocalDate deleted_at,
                            Long id,
                            User user_id,
                            String ip,
                            String header,
                            String timezone) {
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.id = id;
        this.user_id = user_id;
        this.ip = ip;
        this.header = header;
        this.timezone = timezone;
    }
}
