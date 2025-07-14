package com.hsk.crud.h2database.domain.question;

import java.time.LocalDateTime;
import java.util.List;

import com.hsk.crud.h2database.domain.answer.Answer;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Question {
    /* 질문 id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /* 질문 제목 */
    @Column(length = 200)
    private String subject;
    /* 질문 내용 */
    @Column(columnDefinition = "TEXT")
    private String content;
    /* 질문 생성 시간 */
    private LocalDateTime createDate;
    /* 질문에 해당되는 대답 id */
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
    /* 질문 수정 시간 */
    private LocalDateTime modifyDate;
}
