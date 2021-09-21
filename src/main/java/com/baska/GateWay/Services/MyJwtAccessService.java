package com.baska.GateWay.Services;





import com.baska.GateWay.Payload.CheckJwtPayloadResponse;
import com.id.grpc.CheckJwtServiceGrpc;
import com.id.grpc.UserServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;


@Service
public class MyJwtAccessService {
    public CheckJwtPayloadResponse checkJwtAccess(String token, Long userId){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
        CheckJwtServiceGrpc.CheckJwtServiceBlockingStub stub = CheckJwtServiceGrpc.newBlockingStub(channel);
        UserServiceProto.CheckJwtRequest request = UserServiceProto.CheckJwtRequest.newBuilder()
                .setBearer(token)
                .setUserId(userId)
                .build();
        UserServiceProto.CheckJwtResponse response = stub.checkJwt(request);
        channel.shutdownNow();
        return new CheckJwtPayloadResponse(response.getValid(),response.getExpiredToken());
    }
}
