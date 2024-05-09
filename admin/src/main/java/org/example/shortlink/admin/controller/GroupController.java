package org.example.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.example.shortlink.admin.service.GroupServise;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LLY
 * @className GroupController
 * @date 2024/5/9
 */
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupServise groupServise;
}
