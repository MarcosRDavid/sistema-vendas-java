package repository;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();

    public boolean adicionar(Cliente cliente) {
        if (buscarPorId(cliente.getId()) != null) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }
}
