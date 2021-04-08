package com.project.dps.domain.poc;

import com.project.dps.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("secure")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurePoc extends Poc {

    private String type; // POC 항목
    private String content; // POC 내용
    private LocalDateTime createTime; // 생성 날짜


    // 생성자 메서드
    public SecurePoc (Stage stage, String type, String content) {
        super(stage);
        this.type = type;
        this.content = content;
        this.createTime = LocalDateTime.now();
    }
}
