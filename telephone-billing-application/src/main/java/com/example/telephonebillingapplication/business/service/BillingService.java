package com.example.telephonebillingapplication.business.service;

import com.example.telephonebillingapplication.business.IBillingService;
import com.example.telephonebillingapplication.dto.request.CallRecordRequest;
import com.example.telephonebillingapplication.dto.response.BillingResponse;
import com.example.telephonebillingapplication.dto.response.CallRecordResponse;
import com.example.telephonebillingapplication.model.Billing;
import com.example.telephonebillingapplication.model.User;
import com.example.telephonebillingapplication.repository.BillingRepository;
import com.example.telephonebillingapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class BillingService implements IBillingService {

    private final UserRepository userRepository;

    private final BillingRepository billingRepository;

    @Override
    public CallRecordResponse recordCallDuration(String username, CallRecordRequest request) {
        CallRecordResponse response = new CallRecordResponse();
        User user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            user = new User();
            user.setUsername(username);
            userRepository.save(user);

            Billing billing = new Billing();
            billing.setUserId(userRepository.findByUsername(username).getId());
            billing.setCallCount(1L);
            billing.setBlockCount(Math.floorDiv(request.getDuration(), 30000) + 1);
            billingRepository.save(billing);

            response.setCallCount(billing.getCallCount());
            response.setUsername(username);
            return response;
        }

        Billing billing = billingRepository.findByUserId(user.getId());
        billing.setCallCount(billing.getCallCount() + 1);
        billing.setBlockCount(billing.getBlockCount() + Math.floorDiv(request.getDuration(), 30000) + 1);
        billingRepository.save(billing);

        response.setCallCount(billing.getCallCount());
        response.setUsername(username);
        return response;
    }

    @Override
    public BillingResponse getBilling(String username) {
        User user = userRepository.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new IllegalArgumentException(String.format("Invalid username: {}", username));
        }

        Billing billing = billingRepository.findByUserId(user.getId());
        BillingResponse response = new BillingResponse();
        response.setBlockCount(billing.getBlockCount());
        response.setCallCount(billing.getCallCount());
        return response;
    }
}
