package com.baska.GateWay.Controllers;


import com.baska.GateWay.Payload.SignInRequestPayload;
import com.baska.GateWay.Payload.SignUpRequestPayload;
import com.baska.GateWay.Services.MyJwtAccessService;
import com.id.grpc.RenewJwtServiceGrpc;
import com.id.grpc.SignInServiceGrpc;
import com.id.grpc.SignUpServiceGrpc;
import com.id.grpc.UserServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserServiceController {

    @Autowired
    MyJwtAccessService myJwtAccessService;

//    @PostMapping("/updateuser")
//    public ResponseEntity<?> updateUser(@Validated @RequestBody UpdateUserRequestPayload updateUserRequestPayload, @RequestHeader HttpHeaders headers){
//        String token = null;
//        try {
//            token = headers.get("Authorization").get(0);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not find");
//        }
//        JwtAccessResponse jwtAccessResponse = myJwtAccessService.checkJwtAccess(token,"UPDATE", "USER_SERVICE");
//        if (jwtAccessResponse.getExpiredToken()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is expired");
//        }
//        if (!jwtAccessResponse.getPermission()){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//        }
//
//        return ResponseEntity.ok().body("");
//    }
//
//
//    @PostMapping("/getuser")
//    public ResponseEntity<?> getUser(@Validated @RequestBody GetUserRequest getUserRequest, @RequestHeader HttpHeaders headers){
//        String token = null;
//        try {
//            token = headers.get("Authorization").get(0);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token not find");
//        }
//        JwtAccessResponse jwtAccessResponse = myJwtAccessService.checkJwtAccess(token,"READ", "USER_SERVICE");
//        if (jwtAccessResponse.getExpiredToken()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is expired");
//        }
//        if (!jwtAccessResponse.getPermission()){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//        }
//        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
//        GetUserServiceGrpc.GetUserServiceBlockingStub stub = GetUserServiceGrpc.newBlockingStub(channel);
//        UserServiceProto.GetUserRequest request = UserServiceProto.GetUserRequest.newBuilder()
//                .setUserId(getUserRequest.getUserId())
//                .build();
//        UserServiceProto.GetUserResponse response = stub.getUser(request);
//        channel.shutdownNow();
//        return ResponseEntity.ok().body(response.getJson());
//    }
//
//    @PostMapping("/getalluser")
//    public ResponseEntity<?> getAllUser(@RequestHeader HttpHeaders headers){
//        String token = null;
//        try {
//            token = headers.get("Authorization").get(0);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token not find");
//        }
//        JwtAccessResponse jwtAccessResponse = myJwtAccessService.checkJwtAccess(token,"READ", "USER_SERVICE");
//        if (jwtAccessResponse.getExpiredToken()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is expired");
//        }
//        if (!jwtAccessResponse.getPermission()){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
//        }
//        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
//        GetAllUserServiceGrpc.GetAllUserServiceBlockingStub stub = GetAllUserServiceGrpc.newBlockingStub(channel);
//        UserServiceProto.GetAllUserRequest request = UserServiceProto.GetAllUserRequest.newBuilder()
//                .setUserId(jwtAccessResponse.getUserId())
//                .build();
//        UserServiceProto.GetAllUserResponse response = stub.getAllUser(request);
//        channel.shutdownNow();
//        return ResponseEntity.ok().body(response.getJson());
//    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequestPayload signUpRequestPayload) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
        SignUpServiceGrpc.SignUpServiceBlockingStub stub = SignUpServiceGrpc.newBlockingStub(channel);
        UserServiceProto.SignUpRequest request = UserServiceProto.SignUpRequest.newBuilder()
                .setEmail(signUpRequestPayload.getEmail())
                .setUsername(signUpRequestPayload.getUsername())
                .setPassword(signUpRequestPayload.getPassword())
                .build();
        UserServiceProto.SignUpResponse response = stub.signUp(request);
        channel.shutdownNow();
        return ResponseEntity.ok().body(response.getMessage());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody SignInRequestPayload signInRequestPayload) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
        SignInServiceGrpc.SignInServiceBlockingStub stub = SignInServiceGrpc.newBlockingStub(channel);
        UserServiceProto.SignInRequest request = UserServiceProto.SignInRequest.newBuilder()
                .setUsername(signInRequestPayload.getUsername())
                .setPassword(signInRequestPayload.getPassword())
                .build();
        UserServiceProto.SignInResponse response = stub.signIn(request);
        channel.shutdownNow();
        return ResponseEntity.ok().body(response.getAnswer());
    }

    @PostMapping("/renewtoken")
    public ResponseEntity<?> renewToken(@RequestHeader HttpHeaders headers) {
        String s = headers.get("Authorization").get(0);
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8079").usePlaintext().build();
        RenewJwtServiceGrpc.RenewJwtServiceBlockingStub stub = RenewJwtServiceGrpc.newBlockingStub(channel);
        UserServiceProto.RenewJwtRequest request = UserServiceProto.RenewJwtRequest.newBuilder()
                .setToken(s)
                .build();
        UserServiceProto.RenewJwtResponse response = stub.renew(request);
        channel.shutdownNow();
        return ResponseEntity.ok().body(response.getToken());
    }
}
