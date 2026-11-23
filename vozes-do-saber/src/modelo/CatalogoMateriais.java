package modelo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoMateriais {
    private List<MaterialEstudo> materiais;

    public CatalogoMateriais() {
        this.materiais = new ArrayList<>();
    }

    public List<MaterialEstudo> getMateriais() { return new ArrayList<>(materiais); }
    public void setMateriais(List<MaterialEstudo> materiais) {
        if (materiais == null || materiais.contains(null)) throw new IllegalArgumentException("Lista de materiais inválida.");
        this.materiais = new ArrayList<>(materiais);
    }

    // guarda o material no catalogo e na instituicao
    public void cadastrarMaterial(MaterialEstudo material) {
        if (material == null) throw new IllegalArgumentException("Material é obrigatório.");
        if (!materiais.contains(material)) {
            material.getInstituicao().publicarMaterial(material);
            materiais.add(material);
        }
    }

    // mostra so os materiais da etapa escolhida
    public List<MaterialEstudo> buscarPorEtapa(String etapaEnsino) {
        if (etapaEnsino == null || etapaEnsino.isBlank()) throw new IllegalArgumentException("Informe a etapa de ensino.");
        List<MaterialEstudo> resultado = new ArrayList<>();
        for (MaterialEstudo material : materiais) {
            if (material.getEtapaEnsino().equalsIgnoreCase(etapaEnsino.trim())) resultado.add(material);
        }
        return resultado;
    }
}
