package repository;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<>();

    public boolean adicionar(Produto produto) {
        if (buscarPorId(produto.getId()) != null) {
            return false;
        }
        produtos.add(produto);
        return true;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Produto> listarTodos() {
        return produtos;
    }
}
