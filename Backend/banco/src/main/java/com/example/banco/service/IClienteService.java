package com.example.banco.service;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;

public interface IClienteService {
    ClienteDTO createCliente(ClienteDTO createClienteDTO);

    ClienteDTO updateClientePATCH(Integer id, ClienteDTO updateClienteDTO);

    ClienteDTO updateClientePUT(Integer id, ClienteDTO clienteDTO);

    ClienteDTO findClienteById(Integer id);

    void deleteClienteById(Integer id);

    PaginaDTO<ClienteDTO> findAllClientes(Integer numeroPagina, Integer tamanioPagina);
}
