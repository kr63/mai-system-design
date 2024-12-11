package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.proto.DecisionGrpc;
import org.example.proto.DecisionReply;
import org.example.proto.DecisionRequest;

@GrpcService
@Slf4j
public class DecisionService  extends DecisionGrpc.DecisionImplBase {

    private final Map<String, Boolean> decision = new ConcurrentHashMap<>();

    {
        decision.put("login1", true);
        decision.put("login2", true);
        decision.put("login3", true);
        decision.put("login4", true);
        decision.put("login5", false);
    }

    @Override
    public void getDecision(DecisionRequest request, StreamObserver<DecisionReply> responseObserver) {
        log.info(">>> Received request: {}", request);

        var reply = DecisionReply.newBuilder()
                .setAuthorization(decision.getOrDefault(request.getLogin(), false))
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
