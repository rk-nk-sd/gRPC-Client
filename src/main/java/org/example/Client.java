package org.example;

import com.example.grpc.GreetingServiceGrpc;
import com.example.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        //канал
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext().build();

        //stub - это способ вызова удаленных процедур
        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        GreetingServiceOuterClass.HelloRequest request =
                GreetingServiceOuterClass.HelloRequest.newBuilder().setName("Vasya").build();

        GreetingServiceOuterClass.HelloResponse response = stub.greeting(request);

        System.out.println(response);

        //Закрываем канал
        channel.shutdownNow();
    }
}
