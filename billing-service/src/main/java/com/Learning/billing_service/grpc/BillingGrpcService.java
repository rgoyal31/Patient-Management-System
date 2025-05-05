package com.Learning.billing_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class BillingGrpcService extends BillingServiceImplBase{

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<billing.BillingResponse> responseObserver){

        log.info("createBilling Account Request Received {}", billingRequest.toString());

        //Business logic- eg. save to db, perform calculation etc

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("123456")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(billingResponse);
        responseObserver.onCompleted();
    }
}
