package com.example.banco.service.impl;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.model.Cliente;
import com.example.banco.repository.IClienteRepository;
import com.example.banco.service.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro el cliente con el id indicado";
    private final IClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClienteService(IClienteRepository clienteRepository, ObjectMapper objectMapper) {
        this.clienteRepository = clienteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO obj) {
        return null;
    }

    @Override
    public ClienteDTO updateCliente(ClienteDTO obj) {
        return null;
    }

    @Override
    public ClienteDTO findClienteById(Integer id) {
        return null;
    }

    @Override
    public void deleteClienteById(Integer id) {

    }

    @Override
    public PaginaDTO<ClienteDTO> findAllClientes(Integer page, Integer size) {
        return null;
    }


    private ClienteDTO mapToDTO(Cliente cliente) {
        return objectMapper.convertValue(cliente, ClienteDTO.class);
    }
    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        return objectMapper.convertValue(clienteDTO, Cliente.class);
    }

}
