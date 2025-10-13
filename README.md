# codex-grpc

Kurumsal projelerde kullanılabilecek örnek bir Spring tabanlı mikroservis mimarisi. UI Controller servisi REST ile çağrıları alır ve iş mantığı için gRPC üzerinden Business Service ile haberleşir.

## Modüller

- **common-grpc**: gRPC protobuf tanımlarını ve üretilen stub'ları içerir.
- **business-service**: Favori aramaları dönen gRPC sunucusu.
- **ui-controller**: UI'dan gelen REST çağrılarını karşılayan ve gRPC istemcisi olarak business servisine bağlanan servis.

## Çalıştırma

Önce tüm modülleri derleyin:

```bash
mvn clean install
```

Ardından ayrı terminallerde servisleri çalıştırın:

```bash
mvn -pl business-service spring-boot:run
```

```bash
mvn -pl ui-controller spring-boot:run
```

UI Controller servisi çalıştığında favori aramaları aşağıdaki uç noktadan alabilirsiniz:

```bash
curl "http://localhost:8080/api/search/favorites?userId=demo"
```

Business servisi şimdilik statik veri döner; Couchbase veya Hazelcast entegrasyonu için `FavoriteSearchGrpcService` sınıfı genişletilebilir.
