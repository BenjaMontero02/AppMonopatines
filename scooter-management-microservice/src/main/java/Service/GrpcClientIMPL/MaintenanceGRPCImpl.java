package Service.GrpcClientIMPL;


import Service.DTO.ScooterKmsDTO;
import com.maintenance.grpc.MaintenanceServiceGrpc;
import com.maintenance.grpc.ScooterListResponse;
import com.maintenance.grpc.number;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceGRPCImpl{

        ManagedChannel channel = NettyChannelBuilder.forTarget("dns://localhost:8082").usePlaintext().build();

        public ScooterKmsDTO scooterForKms(Long kms){
                MaintenanceServiceGrpc.MaintenanceServiceBlockingStub stub = MaintenanceServiceGrpc.newBlockingStub(channel);
                ScooterListResponse
        }
}
