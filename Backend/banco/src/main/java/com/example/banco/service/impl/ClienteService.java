package com.example.banco.service.impl;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.exception.EntityNotFoundException;
import com.example.banco.exception.InvalidIdException;
import com.example.banco.model.Cliente;
import com.example.banco.repository.IClienteRepository;
import com.example.banco.service.IClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        //cliente.setContrasenia(clienteDTO.getContrasenia());

        Cliente newCliente = clienteRepository.save(cliente);
        return mapToDTO(newCliente);
    }

    @Override
    public ClienteDTO updateCliente(ClienteDTO clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        clienteDTO.setId(clienteActualizado.getId());
        return clienteDTO;
    }

    @Override
    public ClienteDTO findClienteById(Integer id) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ClienteService.ENTITY_NOT_FOUND_MESSAGE));
        return mapToDTO(cliente);
    }

    @Override
    public void deleteClienteById(Integer id) {
        findClienteById(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<ClienteDTO> findAllClientes(Integer numeroPagina, Integer tamanioPagina) {
        numeroPagina = numeroPagina==null? 0 : numeroPagina;
        tamanioPagina = tamanioPagina==null? 10 : tamanioPagina;
        Pageable numeroPaginaRequest = PageRequest.of(numeroPagina,tamanioPagina);
        Page<Cliente> paginaClientes = clienteRepository.findAll(numeroPaginaRequest);
        List<ClienteDTO> listaClientesDTO = paginaClientes.getContent().stream().map(this::mapToDTO).toList();
        long cantidad  = paginaClientes.getTotalElements();
        return new PaginaDTO<>(numeroPagina , tamanioPagina , cantidad, listaClientesDTO);
    }


    private ClienteDTO mapToDTO(Cliente cliente) {
        return objectMapper.convertValue(cliente, ClienteDTO.class);
    }
    private Cliente mapToEntity(ClienteDTO clienteDTO) {
        return objectMapper.convertValue(clienteDTO, Cliente.class);
    }

}
