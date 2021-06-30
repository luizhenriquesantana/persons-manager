package service;

import com.accela.personsmanager.model.dto.AddressDTO;
import com.accela.personsmanager.model.dto.PersonDTO;
import com.accela.personsmanager.model.dto.PlainPersonDTO;
import com.accela.personsmanager.model.entity.Address;
import com.accela.personsmanager.model.entity.Person;
import com.accela.personsmanager.model.exception.AddressNotFoundException;
import com.accela.personsmanager.repository.AddressRepository;
import com.accela.personsmanager.service.AddressService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@SpringBootTest
@ContextConfiguration(classes = AddressService.class)
public class AddressServiceTest {

    private Address address;
    private AddressDTO addressDTO;
    private Person person;
    private PersonDTO personDTO;
    private PlainPersonDTO plainPersonDTO;

    private final static long addressNonExistentId = 1001;
    private final static long addressSampleId = 1;
    private final static long addressCreatedId = 0;
    private final static String addressSampleStreet = "Azusa Street";
    private final static String addressInvalidStreet = "invalid street";

    private final static int mockitoTimes = 1;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService = new AddressService(addressRepository);

    @BeforeEach
    void setUp(){
        
        
        
        address = new Address(1L, "Azusa Street", "Los Angeles", "California", "2QX5+C7", person);
        addressDTO = new AddressDTO(1L, "Azusa Street", "Los Angeles", "California", "2QX5+C7", plainPersonDTO);
    }

    @Test
    public void testGetAddressByIdNotFoundException(){
        Assertions.assertThrows(AddressNotFoundException.class, () -> {
            addressService.getAddress(addressNonExistentId);
        });
        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findById(addressNonExistentId);
    }

    @Test
    public void testGetAddressByIdSuccess(){
        Mockito.when(addressRepository.findById(addressSampleId)).thenReturn(java.util.Optional.ofNullable(address));
        Assertions.assertNotNull(addressService.getAddress(addressSampleId));
        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findById(addressSampleId);
    }

    @Test
    public void testFindAllEmployessSuccess(){
        Mockito.when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
        Assertions.assertNotNull(addressService.getAddresses());
        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findAll();
    }

//    @Test
//    @Ignore
//    public void testCreateAddressSuccess(){
//        Address createdAddress = addressService.addAddress(addressDTO);
//        Assertions.assertNotNull(addressDTO.getId());
//        Assertions.assertEquals(addressDTO.getId(), addressCreatedId);
//    }
//
//    @Test
//    @Ignore
//    public void testUpdateAddressSuccess(){
//        Mockito.when(addressRepository.findById(addressSampleId)).thenReturn(java.util.Optional.ofNullable(address));
//        AddressDTO updatedAddress = new AddressDTO(address.getId(), "Updated Street", "Updated City", "Updated State", "Updated Postal Code", plainPersonDTO);
//        addressService.editAddress(updatedAddress);
//
//        Assertions.assertEquals(updatedAddress.getId(), addressSampleId);
//
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findById(addressSampleId);
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).save(address);
//
//    }
//
//    @Test
//    @Ignore
//    public void testFindByUserNameSuccess(){
//        Mockito.when(addressRepository.findByUsername(addressSampleUsername)).thenReturn(address);
//        Assertions.assertNotNull(((UserDetailsService) addressService).loadUserByUsername(addressSampleUsername));
//        Mockito.verify(addressRepository, times(mockitoTimes)).findByUsername(addressSampleUsername);
//
//    }
//
//    @Test
//    @Ignore
//    public void testFindByUserNameNotFoundException(){
//        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
//            ((UserDetailsService) addressService).loadUserByUsername(addressNonExistentUsername);
//        });
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findByUsername(addressNonExistentUsername);
//    }
//
//    @Test
//    @Ignore
//    public void testDeleteSuccess(){
//        Mockito.when(addressRepository.findById(addressSampleId)).thenReturn(java.util.Optional.ofNullable(address));
//        addressService.deleteAddress(addressSampleId);
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).deleteById(addressSampleId);
//    }
//
//    @Test
//    @Ignore
//    public void testFindOneSuccess(){
//        Mockito.when(addressRepository.findByUsername(addressSampleUsername)).thenReturn(address);
//        addressService.findOne(addressSampleUsername);
//        assertNotNull(address.getUsername());
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findByUsername(addressSampleUsername);
//    }
//
//    @Test
//    @Ignore
//    public void testFindOneException(){
//        Mockito.when(addressRepository.findByUsername(addressNonExistentUsername)).thenReturn(null);
//        addressService.findOne(addressNonExistentUsername);
//        Mockito.verify(addressRepository, Mockito.times(mockitoTimes)).findByUsername(addressNonExistentUsername);
//    }


}
