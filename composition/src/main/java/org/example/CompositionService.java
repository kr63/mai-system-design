package org.example;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.DecisionGrpc;
import org.example.proto.DecisionRequest;
import org.example.proto.ScoreGrpc;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompositionService {

    public CompositionResponse getUser1(@GrpcClient("scoring")
                                       ScoreGrpc.ScoreBlockingStub score,
                                       @GrpcClient("decision")
                                       DecisionGrpc.DecisionBlockingStub decision,
                                       CompositionRequest request) {

        var reply = decision.getDecision(this.createDecisionRequest(request));
        return null;
    }

    private DecisionRequest createDecisionRequest(CompositionRequest request) {
        return DecisionRequest.newBuilder()
                .setLogin(request.getLogin())
                .setPassword(request.getPassword())
                .build();
    }

    public CompositionResponse getUser(CompositionRequest request) {
        return null;
    }

}
