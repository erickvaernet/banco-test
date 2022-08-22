package com.example.banco.service.impl;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.CuentaDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.model.Cliente;
import com.example.banco.model.enums.GenerosEnum;
import com.example.banco.model.enums.TipoCuentasEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CuentaServiceTest {

    public CuentaService cuentaService;
    public ClienteService clienteService;
    public ObjectMapper objectMapper;
    public Cliente cliente;

    @Autowired
    public CuentaServiceTest(CuentaService cuentaService, ClienteService clienteService, ObjectMapper objectMapper) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
        this.objectMapper = objectMapper;

    }

    @BeforeEach
    void setUp() {
        ClienteDTO clienteDTO = new ClienteDTO( "3712", "Erick Vaernet", GenerosEnum.MASCULINO, LocalDate.now(), "dir 1234", "3624284819", "contra13", true);
        cliente = mapToEntity(clienteService.createCliente(clienteDTO));
        System.out.println(cliente.getId());
        assertNotNull(cliente);
        assertTrue(cliente.getId() > 0);
    }

    @Test
    void createCuenta() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);
    }

    @Test
    void findCuentaById() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);

        CuentaDTO cl2 = null;
        cl2= cuentaService.findCuentaById(cuentaDTO.getNumeroCuenta());
        assertNotNull(cl2);
        assertEquals(cl2.getNumeroCuenta(), cuentaDTO.getNumeroCuenta());
        assertEquals(cl2.getTipo(), cuentaDTO.getTipo());
    }

    @Test
    void deleteCuentaById() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);

        clienteService.deleteClienteById(cuentaDTO.getNumeroCuenta());
        CuentaDTO cl=null;
        try {
            cl= cuentaService.findCuentaById(cuentaDTO.getNumeroCuenta());
        }catch (Exception e){
            assertTrue(e.getMessage().contains(CuentaService.ENTITY_NOT_FOUND_MESSAGE));
        }
        assertNull(cl);
    }

    @Test
    void updateCuentaPatch() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);

        Double nuevoSaldo = 100.50;
        cuentaDTO.setSaldo(nuevoSaldo);
        cuentaService.updateCuentaPut(cuentaDTO.getNumeroCuenta(),cuentaDTO);
        CuentaDTO dt =cuentaService.findCuentaById(cuentaDTO.getNumeroCuenta());
        assertEquals(dt.getSaldo(), nuevoSaldo);
    }

    @Test
    void updateCuentaPut() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);

        Double nuevoSaldo = 150.50;
        cuentaDTO.setSaldo(nuevoSaldo);
        cuentaService.updateCuentaPut(cuentaDTO.getNumeroCuenta(),cuentaDTO);
        CuentaDTO dt =cuentaService.findCuentaById(cuentaDTO.getNumeroCuenta());
        assertEquals(dt.getSaldo(), nuevoSaldo);
    }

    @Test
    void findAllCuentas() {
        CuentaDTO cuentaDTO = new CuentaDTO(TipoCuentasEnum.AHORRO, 0d , true, cliente);
        cuentaDTO = cuentaService.createCuenta(cuentaDTO);
        assertNotNull(cuentaDTO);
        assertTrue(cuentaDTO.getNumeroCuenta() > 0);
        CuentaDTO dt = cuentaService.findCuentaById(cuentaDTO.getNumeroCuenta());
        assertNotNull(dt);
        PaginaDTO<CuentaDTO> paginaDTO= cuentaService.findAllCuentas(0,   1);
        assertTrue(paginaDTO.getResultados().size() > 0);
    }

    private Cliente mapToEntity(ClienteDTO createClienteDTO) {
        return objectMapper.convertValue(createClienteDTO, Cliente.class);
    }
}