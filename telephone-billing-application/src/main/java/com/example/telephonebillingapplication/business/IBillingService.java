package com.example.telephonebillingapplication.business;

import com.example.telephonebillingapplication.dto.request.CallRecordRequest;
import com.example.telephonebillingapplication.dto.response.BillingResponse;
import com.example.telephonebillingapplication.dto.response.CallRecordResponse;

public interface IBillingService {
    CallRecordResponse recordCallDuration(String username, CallRecordRequest request);

    BillingResponse getBilling(String username);
}
