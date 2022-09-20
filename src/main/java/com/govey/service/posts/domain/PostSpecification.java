package com.govey.service.posts.domain;

import com.govey.service.user.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostSpecification {
    public static Specification<Post> find(User reader, Optional<String> subject, Optional<String> category) {
        return (Specification<Post>) ((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();

            if (category.orElse(null) != null && !category.get().equals("")) {
                list.add(builder.equal(root.get("category").as(String.class), category.get()));
            }
            if (subject.orElse(null) != null && !subject.get().equals("")) {
                list.add(builder.like(root.get("subject").as(String.class), "%" + subject.get() + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return builder.and(list.toArray(p));
        });
    }
}