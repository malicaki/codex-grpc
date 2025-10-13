package com.example.ui.controller;

import com.example.grpc.FavoriteRequest;
import com.example.grpc.SearchFavoritesServiceGrpc;
import com.example.ui.model.FavoriteItemResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class FavoriteSearchController {

    private final SearchFavoritesServiceGrpc.SearchFavoritesServiceBlockingStub favoritesStub;

    public FavoriteSearchController(
            @GrpcClient("business-service") SearchFavoritesServiceGrpc.SearchFavoritesServiceBlockingStub favoritesStub) {
        this.favoritesStub = favoritesStub;
    }

    @GetMapping("/favorites")
    public List<FavoriteItemResponse> getFavorites(@RequestParam(defaultValue = "anonymous") String userId) {
        return favoritesStub.getFavorites(FavoriteRequest.newBuilder().setUserId(userId).build())
                .getFavoritesList()
                .stream()
                .map(item -> new FavoriteItemResponse(item.getQuery(), item.getUsageCount()))
                .toList();
    }
}
