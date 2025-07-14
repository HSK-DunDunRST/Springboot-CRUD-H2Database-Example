package com.hsk.crud.h2database.domain.answer;

import java.time.LocalDateTime;

import com.hsk.crud.h2database.domain.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
    /* 답변 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /* 답변 내용 */
    @Column(columnDefinition = "TEXT")
    private String content;
    /* 답변 생성 시간 */
    private LocalDateTime createDate;
    /* 답변에 해당되는 질문 id */
    @ManyToOne
    private Question question;
    /* 답변 수정 시간 */
    private LocalDateTime modifyDate;
}
