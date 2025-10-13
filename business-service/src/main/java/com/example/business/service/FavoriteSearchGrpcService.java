package com.example.business.service;

import com.example.grpc.FavoriteItem;
import com.example.grpc.FavoriteRequest;
import com.example.grpc.FavoriteResponse;
import com.example.grpc.SearchFavoritesServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class FavoriteSearchGrpcService extends SearchFavoritesServiceGrpc.SearchFavoritesServiceImplBase {

    @Override
    public void getFavorites(FavoriteRequest request, StreamObserver<FavoriteResponse> responseObserver) {
        List<FavoriteItem> favorites = List.of(
                FavoriteItem.newBuilder().setQuery("hazelcast cache clear").setUsageCount(42).build(),
                FavoriteItem.newBuilder().setQuery("grpc spring boot tutorial").setUsageCount(35).build(),
                FavoriteItem.newBuilder().setQuery("couchbase query indexes").setUsageCount(20).build()
        );

        FavoriteResponse response = FavoriteResponse.newBuilder()
                .addAllFavorites(favorites)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
