package Mapper;

import DTO.AddressDTO;
import Entity.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {


    public static AddressDTO addressToDTO(Address address) {
        if (address == null) return null;

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setType(address.getType());
        dto.setLine1(address.getLine1());
        dto.setLine2(address.getLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        return dto;
    }

    public static Address dtoToAddress(AddressDTO dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setId(dto.getId());
        address.setType(dto.getType());
        address.setLine1(dto.getLine1());
        address.setLine2(dto.getLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        return address;
    }

    public static List<AddressDTO> addressListToDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::addressToDTO)
                .collect(Collectors.toList());
    }

    public static List<Address> dtoListToAddressList(List<AddressDTO> dtos) {
        return dtos.stream()
                .map(AddressMapper::dtoToAddress)
                .collect(Collectors.toList());
    }
}

