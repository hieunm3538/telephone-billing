package com.example.telephonebillingapplication.controller;

import com.example.telephonebillingapplication.business.IBillingService;
import com.example.telephonebillingapplication.dto.BaseResponse;
import com.example.telephonebillingapplication.dto.request.CallRecordRequest;
import com.example.telephonebillingapplication.dto.response.BillingResponse;
import com.example.telephonebillingapplication.dto.response.CallRecordResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mobile")
@RestController
@RequiredArgsConstructor
public class BillingController {

    private final IBillingService service;

    @PostMapping("/{username}/call")
    public BaseResponse<CallRecordResponse> recordCallDuration(@PathVariable(name = "username") @NonNull String username,
                                                              @RequestBody CallRecordRequest request) {
        if (username.length() > 32)
            throw new IllegalArgumentException("Username's length must be under 32 characters!");
        return BaseResponse.ofSucceeded(service.recordCallDuration(username, request));
    }

    @GetMapping("/{username}/billing")
    public BaseResponse<BillingResponse> getBilling(@PathVariable(name = "username") @NonNull String username) {
        if (username.length() > 32)
            throw new IllegalArgumentException("Username's length must be under 32 characters!");
        return BaseResponse.ofSucceeded(service.getBilling(username));
    }

}
