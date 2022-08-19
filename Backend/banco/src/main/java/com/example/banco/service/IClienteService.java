package com.example.banco.service;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;

public interface IClienteService {
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    ClienteDTO updateCliente(ClienteDTO clienteDTO);
    ClienteDTO findClienteById(Integer id);
    void deleteClienteById(Integer id);
    PaginaDTO<ClienteDTO> findAllClientes(Integer numeroPagina, Integer tamanioPagina);
}
