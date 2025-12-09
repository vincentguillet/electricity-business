package fr.vguillet.electricitybusiness.controller.app;

import fr.vguillet.electricitybusiness.Mapper.app.AddressMapper;
import fr.vguillet.electricitybusiness.dto.app.AddressDTO;
import fr.vguillet.electricitybusiness.service.app.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/app/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> getAllAddresses() {
        return addressService.getAllAddresses()
                .stream()
                .map(AddressMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(address -> ResponseEntity.ok(AddressMapper.toDto(address)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> saveAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return addressService.getAddressById(id)
                .map(existingUser -> addressService.updateAddress(AddressMapper.fromDto(addressDTO))
                        .map(address -> ResponseEntity.ok(AddressMapper.toDto(address)))
                        .orElse(ResponseEntity.notFound().build()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressDTO> deleteAddressById(@PathVariable Long id) {
        return addressService.deleteAddressById(id)
                .map(address -> ResponseEntity.ok(AddressMapper.toDto(address)))
                .orElse(ResponseEntity.notFound().build());
    }
}
