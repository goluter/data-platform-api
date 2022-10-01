package com.govey.controller.users.storeitems;

import com.govey.service.store.application.StoreItemService;
import com.govey.service.store.domain.StoreItem;
import com.govey.service.users.application.UserService;
import com.govey.service.users.domain.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/v1/store-items")
public class StoreItemController {
    private final StoreItemService storeItemService;

    private final UserService userService;

    @GetMapping("/page")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ApiOperation(value = "상품 목록 조회", notes = "전체 상품을 조회한다.")
    public ResponseEntity<Page<StoreItem>> page(Authentication authentication, @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "limit", defaultValue = "0") int limit) {
//        User reader = userService.getUserByUsername(authentication.getName()).get();
        User reader = userService.getUserByUsername("admin").get();
        Page<StoreItem> result = storeItemService.page(reader, page, limit);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StoreItem> add(Authentication authentication, @Valid @RequestBody StoreItemRequest body) {
//        User author = userService.getUserByUsername(authentication.getName()).get();
        User author = userService.getUserByUsername("admin").get();
        return ResponseEntity.ok(storeItemService.add(author, body));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StoreItem> retrieve(Authentication authentication,@PathVariable UUID id) {
//        Optional<User> author = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(storeItemService.retrieve(id, userService.getUserByUsername("admin")).get());
    }

    @PatchMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<StoreItem> update(@PathVariable UUID id, @Valid @RequestBody StoreItemRequest body) {
        return ResponseEntity.ok(storeItemService.update(id, body));
    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity delete(@PathVariable UUID id) {
//        service.softDelete(id);
//        return ResponseEntity.ok().build();
//    }
}
