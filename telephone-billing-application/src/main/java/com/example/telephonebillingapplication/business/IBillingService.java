package com.example.telephonebilling.business;

import com.example.telephonebilling.dto.request.CallRecordRequest;
import com.example.telephonebilling.dto.response.BillingResponse;
import com.example.telephonebilling.dto.response.CallRecordResponse;

public interface IBillingService {
    CallRecordResponse recordCallDuration(String username, CallRecordRequest request);

    BillingResponse getBilling(String username);
}
