package com.govey.service.surveys.application;

import com.govey.service.surveys.domain.Report;
import com.govey.service.surveys.domain.ReportBookmark;
import com.govey.service.surveys.infrastructure.*;
import com.govey.service.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportBookmarkService {
    private final ReportService reportService;
    private final ReportBookmarkRepository reportBookmarkRepository;

    @Transactional(readOnly = true)
    public Page<ReportBookmark> page(User user, int page, int limit) {
        return reportBookmarkRepository.findAllByUser(
                user,
                PageRequest.of(page, limit, Sort.by("createdAt").descending())
        );
    }

    @Transactional()
    public Optional<ReportBookmark> retrieve(UUID id) {
        return reportBookmarkRepository.findById(id);
    }

    @Transactional()
    public ReportBookmark add(User user, UUID reportId) {
        Report report = reportService.retrieve(reportId).get();
        if (reportBookmarkRepository.findByReportAndUser(report, user).isPresent()) {
            throw new RuntimeException("이미 북마크하셨습니다.");
        }

        ReportBookmark entity = reportBookmarkRepository.save(ReportBookmark.builder()
                .user(user)
                .report(report)
                .build());

        return reportBookmarkRepository.save(entity);
    }

    @Transactional()
    public void softDelete(UUID id) {
        ReportBookmark result = reportBookmarkRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                id + " does not exists")
        );
        result.setDeleted(true);
        result.setDeletedAt(LocalDateTime.now());
        reportBookmarkRepository.save(result);
    }
}
