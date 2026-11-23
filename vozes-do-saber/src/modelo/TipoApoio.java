package modelo;

public enum TipoApoio {
    MATERIAL_DE_ESTUDO("Material de estudo"),
    ACESSO_A_ATIVIDADE("Acesso a atividade"),
    CONTATO_COM_EDUCADOR("Contato com educador"),
    APOIO_NO_ENSINO_SUPERIOR("Apoio no ensino superior"),
    OUTRO("Outro");

    private final String descricao;

    TipoApoio(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
