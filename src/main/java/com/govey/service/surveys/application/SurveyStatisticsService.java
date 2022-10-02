package com.govey.service.surveys.application;

import com.govey.controller.users.surveys.*;
import com.govey.service.surveys.domain.*;
import com.govey.service.surveys.infrastructure.SurveyRepository;
import com.govey.service.surveys.infrastructure.SurveyTagRepository;
import com.govey.service.users.domain.User;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SurveyStatisticsService {
    private final PollService pollService;
    private final PollUserService pollUserService;

    @Transactional(readOnly = true)
    public List<SurveyStatisticResponse> getStatistics(UUID surveyId) {
        List<Poll> polls = pollService.findAllBySurvey(surveyId);
        List<SurveyStatisticResponse> responses = polls.stream().reduce(new ArrayList<>(), (list, poll) -> {
                    List<PollUser> pollUsers = pollUserService.listByPollId(poll.getId());
                    HashMap<String, Integer> answerMap = pollUsers.stream().reduce(new HashMap<String, Integer>(), (map, answer) -> {
                        String value = answer.getValue();
                        if (!map.containsKey(value)) {
                            map.put(value, 0);
                        }
                        map.put(value, map.get(value) + 1);
                        return map;
                    }, (a, b) -> {
                        a.putAll(b);
                        return a;
                    });

                    String[] key = answerMap.keySet().toArray(new String[0]);
                    Integer[] value = answerMap.values().toArray(new Integer[0]);
                    List<SurveyStatisticGroup> groupList = new ArrayList<>();
                    for (int i = 0; i < answerMap.size(); i++) {
                        groupList.add(SurveyStatisticGroup.builder().name(key[i]).count(value[i]).build());
                    }

                    list.add(SurveyStatisticResponse.builder()
                            .id(poll.getId())
                            .survey(poll.getSurvey())
                            .subject(poll.getSubject())
                            .description(poll.getDescription())
                            .content(poll.getContent())
                            .duplicable(poll.getDuplicable())
                            .type(poll.getType())
                            .mandatory(poll.getMandatory())
                            .priority(poll.getPriority())
                            .groups(groupList)
                            .build());

                    return list;
                },
                (a, b) -> {
                    a.addAll(b);
                    return a;
                });

        return responses;
    }
}
