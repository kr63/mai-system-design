package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.proto.ScoreGrpc;
import org.example.proto.ScoreReply;
import org.example.proto.ScoreRequest;

@GrpcService
@Slf4j
public class ScoringService extends ScoreGrpc.ScoreImplBase {

    private final Map<String, Integer> scores = new ConcurrentHashMap<>();

    {
        scores.put("login1", 1);
        scores.put("login2", 2);
        scores.put("login3", 3);
        scores.put("login4", 4);
        scores.put("login5", 5);
    }

    @Override
    public void getScore(ScoreRequest request, StreamObserver<ScoreReply> responseObserver) {
        log.info(">>> request: {}", request);
        responseObserver.onNext(ScoreReply.newBuilder()
                .setValue(scores.getOrDefault(request.getLogin(), 0))
                .build());
        responseObserver.onCompleted();
    }

}
