/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.cliente;

import com.mycompany.prochild.backend.models.Cliente;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class ClienteServices {
    
    private ClienteRepository cliente = new ClienteRepository();
    
    public List<Cliente> findAllClientes() {
        return cliente.findAllClientes();
    }
    
    public int insertCliente(Cliente new_cliente) {
        return cliente.insertCliente(new_cliente);
    }
}
