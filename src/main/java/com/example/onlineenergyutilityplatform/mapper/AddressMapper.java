package com.example.onlineenergyutilityplatform.mapper;

import com.example.onlineenergyutilityplatform.domain.entity.Address;
import com.example.onlineenergyutilityplatform.dto.address.AddressDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddressMapper {

    public static Address addressDtoToAddress(AddressDTO addressDTO) {
        return Address.builder()
                .addressId(UUID.randomUUID().toString())
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .postalCode(addressDTO.getPostalCode())
                .street(addressDTO.getStreet())
                .build();
    }

    public static Address addressDtoToAddressOnUpdate(AddressDTO addressDTO,String addressId) {
        return Address.builder()
            .addressId(addressId)
            .city(addressDTO.getCity())
            .country(addressDTO.getCountry())
            .postalCode(addressDTO.getPostalCode())
            .street(addressDTO.getStreet())
            .build();
    }
}
