package proj.web_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Cria os getters e settes
@NoArgsConstructor //Cria construtor vazio(necessário para rodar o hibernate)
@AllArgsConstructor //Cria construtor com todos os parâmetros
@Entity
@Table(name="db_endereco")
public class Endereco {

    @Id
    private String cep;

    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    
}