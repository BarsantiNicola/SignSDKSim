package org.example.signsdkdemo.infrastructure.repository;

import org.example.signsdkdemo.infrastructure.models.StoredDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<StoredDevice,String> {

}
