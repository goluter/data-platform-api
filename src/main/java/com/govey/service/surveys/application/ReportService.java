package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.ReportRequest;
import com.govey.service.surveys.domain.Survey;
import com.govey.service.surveys.domain.Report;
import com.govey.service.surveys.infrastructure.ReportRepository;
import com.govey.service.surveys.infrastructure.SurveyRepository;
import com.govey.service.surveys.infrastructure.SurveyRewardRepository;
import com.govey.service.surveys.infrastructure.SurveyTagRepository;
import com.govey.service.users.domain.User;
import com.govey.service.users.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final SurveyRepository surveyRepository;
    private final SurveyRewardRepository surveyRewardRepository;
    private final SurveyTagRepository surveyTagRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Report> page(int page, int limit) {
        return reportRepository.findAll(PageRequest.of(page, limit, Sort.by("createdAt").descending()));
    }

    @Transactional(readOnly = true)
    public Page<Report> page(UUID surveyId, int page, int limit) {
        Survey survey = surveyRepository.findById(surveyId).get();

        return reportRepository.findAllBySurvey(
                survey,
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional(readOnly = true)
    public Page<Report> page(User user, int page, int limit) {
        return reportRepository.findAllByUser(
                user,
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<Report> retrieve(UUID id) {
        return reportRepository.findById(id);
    }

    @Transactional()
    public Report add(User user, UUID surveyId, ReportRequest request) {
        Survey survey = surveyRepository.findById(surveyId).get();
        Report report = new Report();
        report.setCreatedAt(LocalDateTime.now());
        report.setUser(user);
        report.setSurvey(survey);
        report.setAuthor(user.getNickname());
        report.setImageUrl(request.getImageUrl());
        report.setSubject(request.getSubject());
        report.setContent(request.getContent());
        report.setCategory(request.getCategory());
        report.setIsNotice(request.getIsNotice());
        return reportRepository.save(report);
    }

    @Transactional()
    public Report update(UUID id, ReportRequest request) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );

        if (!report.getUser().isActivated()) {
            throw new IllegalStateException("deactivated user");
        }

        if (request.getSubject() != null) {
            report.setSubject(request.getSubject());
        }
        if (request.getCategory() != null) {
            report.setCategory(request.getCategory());
        }
        if (request.getImageUrl() != null) {
            report.setImageUrl(request.getImageUrl());
        }
        if (request.getContent() != null) {
            report.setContent(request.getContent());
        }
        if (request.getIsNotice() != null) {
            report.setIsNotice(request.getIsNotice());
        }

        return reportRepository.save(report);
    }

    @Transactional()
    public void softDelete(UUID id) {
        Report result = reportRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        reportRepository.save(result);
    }
}
