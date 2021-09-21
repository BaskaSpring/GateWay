package com.baska.GateWay.Controllers;



import com.baska.GateWay.Payload.GetEventsRequest;
import com.baska.GateWay.Payload.GetEventsRequestJson;
import com.baska.GateWay.Payload.CheckJwtPayloadResponse;
import com.baska.GateWay.Services.MyJwtAccessService;
import com.google.gson.Gson;
import com.id.grpc.CalendarServiceGrpc;
import com.id.grpc.CalendarServiceProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/calendar")
public class CalendarServiceController {

    @Autowired
    MyJwtAccessService myJwtAccessService;

    @GetMapping("/getevents")
    public ResponseEntity<?> getEvents(@Validated @RequestBody GetEventsRequest getEventsRequest, @RequestHeader HttpHeaders headers) throws ParseException {
        String token = null;
        try {
            token = headers.get("Authorization").get(0);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token not find");
        }
        CheckJwtPayloadResponse checkJwtPayloadResponse = myJwtAccessService.checkJwtAccess(token,getEventsRequest.getUserId());
        if (checkJwtPayloadResponse.getExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is expired");
        }
        if (checkJwtPayloadResponse.getValid()){
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            Date dateBegin = formatter.parse(getEventsRequest.getDateBegin());
            Date dateEnd = formatter.parse(getEventsRequest.getDateEnd());
            GetEventsRequestJson getEventsRequestJson = new GetEventsRequestJson();
            getEventsRequestJson.setDateBegin(dateBegin.toInstant());
            getEventsRequestJson.setDateEnd(dateEnd.toInstant());
            getEventsRequestJson.setUserId(getEventsRequest.getUserId());
            Gson gson = new Gson();
            String json = gson.toJson(getEventsRequestJson);
            ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8069").usePlaintext().build();
            CalendarServiceGrpc.CalendarServiceBlockingStub stub = CalendarServiceGrpc.newBlockingStub(channel);
            CalendarServiceProto.GetEventsRequest request = CalendarServiceProto.GetEventsRequest.newBuilder()
                    .setJson(json)
                    .build();
            CalendarServiceProto.GetEventsResponse response = stub.getEvents(request);
            channel.shutdownNow();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getJson());

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token is not valid");
    }
}
