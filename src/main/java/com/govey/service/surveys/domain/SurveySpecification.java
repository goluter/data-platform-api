package com.govey.service.surveys.domain;

import com.govey.service.users.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveySpecification {
    public static Specification<Survey> find(User reader, Optional<String> subject, Optional<List<SurveyStatus>> statuses) {
        return (Specification<Survey>) ((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();

            if (subject.orElse(null) != null && !subject.get().equals("")) {
                list.add(builder.like(root.get("subject").as(String.class), "%" + subject.get() + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return builder.and(list.toArray(p));
        });
    }
}