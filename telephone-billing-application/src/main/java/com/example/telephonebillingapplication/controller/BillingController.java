package com.example.telephonebilling.controller;

import com.example.telephonebilling.business.IBillingService;
import com.example.telephonebilling.dto.request.CallRecordRequest;
import com.example.telephonebilling.dto.response.BillingResponse;
import com.example.telephonebilling.dto.response.CallRecordResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mobile")
@RestController
@RequiredArgsConstructor
public class BillingController {

    private final IBillingService service;

    @PostMapping("/{username}/call")
    public CallRecordResponse recordCallDuration(@PathVariable(name = "username") @NonNull String username,
                                                 @RequestBody CallRecordRequest request) {
        if (username.length() > 32)
            throw new IllegalArgumentException("Username's length must be under 32 characters!");
        return service.recordCallDuration(username, request);
    }

    @GetMapping("/{username}/billing")
    public BillingResponse getBilling(@PathVariable(name = "username") @NonNull String username) {
        if (username.length() > 32)
            throw new IllegalArgumentException("Username's length must be under 32 characters!");
        return service.getBilling(username);
    }

}
