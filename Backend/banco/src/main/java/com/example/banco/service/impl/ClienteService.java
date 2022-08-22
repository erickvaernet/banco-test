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

import java.time.LocalDate;
import java.time.Period;
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
    public ClienteDTO createCliente(ClienteDTO createClienteDTO) {
        Cliente cliente = mapToEntity(createClienteDTO);
        Cliente newCliente = clienteRepository.save(cliente);
        return mapToDTO(newCliente);
    }

    @Override
    public ClienteDTO updateCliente(Integer id, ClienteDTO clienteDTO) {
        if(id==null || id <= 0) throw new InvalidIdException();
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ClienteService.ENTITY_NOT_FOUND_MESSAGE));
        //Puede reemplazarse usando reflection pero genera menor performance
        if(clienteDTO.getIdentificacion()!=null) cliente.setIdentificacion(clienteDTO.getIdentificacion());
        if(clienteDTO.getEstado()!=null) cliente.setEstado(clienteDTO.getEstado());
        if(clienteDTO.getNombres()!=null) cliente.setNombres(clienteDTO.getNombres());
        if(clienteDTO.getDireccion()!=null) cliente.setDireccion(clienteDTO.getDireccion());
        if(clienteDTO.getTelefono()!=null) cliente.setTelefono(clienteDTO.getTelefono());
        if(clienteDTO.getFechaNacimiento()!=null) cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        if(clienteDTO.getContrasenia()!=null) cliente.setContrasenia(clienteDTO.getContrasenia());
        if(clienteDTO.getGenero()!=null) cliente.setGenero(clienteDTO.getGenero());
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return mapToDTO(clienteActualizado);
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
        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        Period period = Period.between(cliente.getFechaNacimiento(), LocalDate.now());
        clienteDTO.setEdad(period.getYears());
        return clienteDTO;
    }

    private Cliente mapToEntity(ClienteDTO createClienteDTO) {
        return objectMapper.convertValue(createClienteDTO, Cliente.class);
    }

}
