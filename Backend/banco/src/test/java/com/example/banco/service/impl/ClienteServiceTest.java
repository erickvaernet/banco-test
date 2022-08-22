package com.example.banco.service.impl;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.model.Cliente;
import com.example.banco.model.enums.GenerosEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteServiceTest {

    public ClienteService clienteService;
    public ObjectMapper objectMapper;

    @Autowired
    public ClienteServiceTest(ClienteService clienteService, ObjectMapper objectMapper) {
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;
    }

    @Test
    void createCliente() {
        ClienteDTO clienteDTO = new ClienteDTO( "3712", "Erick Vaernet", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);
    }

    @Test
    void findClienteById() {
        ClienteDTO clienteDTO = new ClienteDTO( "3712", "Erick Vaernet", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);

        ClienteDTO cl2 = null;
        cl2= clienteService.findClienteById(clienteDTO.getId());
        assertNotNull(cl2);
        assertEquals(cl2.getId(), clienteDTO.getId());
        assertEquals(cl2.getIdentificacion(), clienteDTO.getIdentificacion());
    }

    @Test
    void deleteClienteById() {
        ClienteDTO clienteDTO = new ClienteDTO( "3712", "Erick Vaernet", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);
        clienteService.deleteClienteById(clienteDTO.getId());
        ClienteDTO cl=null;
        try {
            cl= clienteService.findClienteById(clienteDTO.getId());
        }catch (Exception e){
            assertTrue(e.getMessage().contains(ClienteService.ENTITY_NOT_FOUND_MESSAGE));
        }
        assertNull(cl);
    }

    @Test
    void updateClientePUT() {
        ClienteDTO clienteDTO = new ClienteDTO( "3742", "Erick Vaernet", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);

        String nuevoNombre = "Erick Adriel";
        clienteDTO.setNombres(nuevoNombre);
        clienteService.updateClientePUT(clienteDTO.getId(),clienteDTO);
        ClienteDTO dt = clienteService.findClienteById(clienteDTO.getId());
        assertEquals(dt.getNombres(), nuevoNombre);
    }

    @Test
    void updateClientePATCH() {
        ClienteDTO clienteDTO = new ClienteDTO( "4742", "Roco Rodrigues", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);

        String nuevoNombre = "Ian Adriel";
        clienteDTO.setNombres(nuevoNombre);
        clienteService.updateClientePATCH(clienteDTO.getId(),clienteDTO);
        ClienteDTO dt = clienteService.findClienteById(clienteDTO.getId());
        assertEquals(dt.getNombres(), nuevoNombre);
    }

    @Test
    void findAllClientes() {
        ClienteDTO clienteDTO = new ClienteDTO( "4742", "Roco Rodrigues", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        clienteDTO = clienteService.createCliente(clienteDTO);
        assertNotNull(clienteDTO);
        assertTrue(clienteDTO.getId() > 0);
        ClienteDTO dt = clienteService.findClienteById(clienteDTO.getId());
        assertNotNull(dt);
        PaginaDTO<ClienteDTO> paginaDTO= clienteService.findAllClientes(0,   1);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    private Cliente mapToEntity(ClienteDTO createClienteDTO) {
        return objectMapper.convertValue(createClienteDTO, Cliente.class);
    }
}