package com.govey.controller.users.rewards;

import com.govey.service.rewards.application.RewardService;
import com.govey.service.rewards.domain.Reward;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/rewards")
public class RewardController {
    private final UserService userService;
    private final RewardService rewardService;

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Reward> add(@Valid @RequestBody RewardRequest body) {
        //        User author = userService.getUserByUsername(authentication.getName()).get();
//        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(rewardService.add(body));
    }

//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/page")
    @ApiOperation(value = "보상 목록 조회", notes = "https://docs.google.com/spreadsheets/d/1R44vgp9HqKqKVdzLmPedYtaI06ZS4kOkUEXkr94kAvQ/edit#gid=550061713 칭호, 업적 탭 참고")
    public ResponseEntity<Page<Reward>> page(Authentication authentication,
                                             @RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "limit", defaultValue = "0") int limit,
                                             @ApiParam(name = "보상 타입", value = "칭호 or 업적-포인트 or 업적-설문등록 .. etc https://docs.google.com/spreadsheets/d/1R44vgp9HqKqKVdzLmPedYtaI06ZS4kOkUEXkr94kAvQ/edit#gid=550061713 칭호, 업적 탭 참고")
                                             @RequestParam(value = "type", defaultValue = "0")
                                                         String type) {
//        User reader = userService.getUserByUsername(authentication.getName()).get();
        return ResponseEntity.ok(rewardService.page(type, page, limit));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Reward> retrieve(@PathVariable UUID id, Authentication authentication) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User user = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(rewardService.retrieve(id).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Reward> update(@PathVariable UUID id, @Valid @RequestBody RewardRequest body) {
        return ResponseEntity.ok(rewardService.update(id, body));
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity delete(@PathVariable UUID id) {
//        rewardService.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
}
