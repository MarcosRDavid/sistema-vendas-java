package repository;

import java.util.ArrayList;
import java.util.List;

import model.Venda;

public class VendaRepository {

    private List<Venda> vendas = new ArrayList<>();

    public void adicionar(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> listar() {
        return vendas;
    }
}
