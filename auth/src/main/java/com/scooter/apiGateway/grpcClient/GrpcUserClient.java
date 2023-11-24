package com.scooter.apiGateway.grpcClient;

import com.userServiceGRPC.UserResponseDTO;
import com.userServiceGRPC.userServiceGrpc;
import com.userServiceGRPC.email;
import com.scooter.apiGateway.DTO.UserResponseLoginDTO;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GrpcUserClient {

    public UserResponseLoginDTO getUserByEmail(String emailUser) {
        ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9090").usePlaintext().build();
        userServiceGrpc.userServiceBlockingStub blockingStub = userServiceGrpc.newBlockingStub(channel);
        UserResponseDTO user = blockingStub.getUser(email.newBuilder().setEmail(emailUser).build());
        channel.shutdown();
        UserResponseLoginDTO userToReturn = new UserResponseLoginDTO();

        userToReturn.setMail(user.getEmail());
        userToReturn.setPassword(user.getPassword());
        userToReturn.setRoles(user.getRolesList());
        return userToReturn;
    }
}
