package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.proto.DecisionGrpc;
import org.example.proto.DecisionRequest;
import org.example.proto.ScoreGrpc;
import org.example.proto.ScoreRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompositionService {

    private final CompositionProperties compositionProperties;
    @GrpcClient("scoring")
    private ScoreGrpc.ScoreBlockingStub score;
    @GrpcClient("decision")
    private DecisionGrpc.DecisionBlockingStub decision;

    public CompositionResponse getUser(CompositionRequest request) {
        log.info(">>> request: {}", request);
        var scoreReply = score.getScore(this.createScoreRequest(request));

        if (scoreReply.getValue() < compositionProperties.getThreshold()) {
            return CompositionResponse.builder().autorization(false).build();
        }

        var decisionReply = decision.getDecision(this.createDecisionRequest(request));
        return CompositionResponse.builder().autorization(decisionReply.getAuthorization()).build();
    }

    private ScoreRequest createScoreRequest(CompositionRequest request) {
        return ScoreRequest.newBuilder()
                .setLogin(request.getLogin())
                .build();
    }

    private DecisionRequest createDecisionRequest(CompositionRequest request) {
        return DecisionRequest.newBuilder()
                .setLogin(request.getLogin())
                .setPassword(request.getPassword())
                .build();
    }

}
