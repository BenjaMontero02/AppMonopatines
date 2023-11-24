package com.ScootersApp.Service.grpcServiceImpl;
import com.ScootersApp.Service.DTOs.User.response.UserLoginResponseDTO;
import com.ScootersApp.Service.UserService;
import com.userServiceGRPC.Role;
import com.userServiceGRPC.UserResponseDTO;
import com.userServiceGRPC.email;
import com.userServiceGRPC.userServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class GrpcUserServiceImpl extends userServiceGrpc.userServiceImplBase {

    private UserService userService;

    @Override
    public void getUser(email request, StreamObserver<UserResponseDTO> responseObserver) {

        UserLoginResponseDTO busqueda = this.userService.login(request.getEmail());
        UserResponseDTO.Builder response = UserResponseDTO.newBuilder();
        List<Role> roles = new ArrayList<>();

        for(String role : busqueda.getRoles()){
            Role roleGRPC = Role.newBuilder()
                    .setName(role).build();
            roles.add(roleGRPC);
        }

        response.setEmail(busqueda.getMail());
        response.setPassword(busqueda.getPassword());
        for (Role roleValue : roles) {
            response.addRoles(roleValue);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    public GrpcUserServiceImpl(UserService userService) {
        this.userService = userService;
    }
}
