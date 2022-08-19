package com.example.banco.service;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;

public interface IClienteService {
    ClienteDTO createCliente(ClienteDTO obj);
    ClienteDTO updateCliente(ClienteDTO obj);
    ClienteDTO findClienteById(Integer id);
    void deleteClienteById(Integer id);
    PaginaDTO<ClienteDTO> findAllClientes(Integer page, Integer size);
}
