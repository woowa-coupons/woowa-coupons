package woowa.promotion.admin.promotion.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import woowa.promotion.global.domain.audit.AuditingFields;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Promotion extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 512)
    private String content;

    @Column(nullable = false, length = 1024)
    private String bannerUrl;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant startedAt;

    @Column(nullable = false)
    private Instant finishedAt;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ProgressStatus progressStatus;

    @Column(nullable = false, length = 1024)
    private String promotionPageUrl;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean isDisplay;

}
