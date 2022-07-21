package org.zerock.mreview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Movie extends BaseEntity { // Movie클래스는 단순하게 영화 제목만 가지는 구조

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno; // 영화 고유번호
    private String title; // 영화 제목
}
