package com.govey.service.surveys.domain;

public enum SurveyStatus {
    // 임시 저장, 작성 중
    writing,
    // 심사 대기
    waitingReview,
    // 심사 중
    review,
    // 시작 전
    pending,
    // 진행중
    ongoing,
    // 종료
    end,
}