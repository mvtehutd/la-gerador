package br.ufscar.dc.compiladores.la.gerador;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TabelaDeSimbolos {
    public enum TipoLa {
        LITERAL ("char"),
        INTEIRO ("int"),
        REAL ("float"),
        LOGICO ("bool"),
        INVALIDO ("invalido"),
        REGISTRO ("struct");

        private String valor;
        private TipoLa(String valor){
            this.valor = valor;
        }

        public String getValor(){
            return this.valor;
        }
    }

    class EntradaTabelaDeSimbolos {
        String nome;
        TipoLa tipo;
        Boolean ponteiro;
        TabelaDeSimbolos registro;

        private EntradaTabelaDeSimbolos(String nome, TipoLa tipo, Boolean ponteiro, TabelaDeSimbolos registro) {
            this.nome = nome;
            this.tipo = tipo;
            this.ponteiro = ponteiro;
            this.registro = registro;
        }

        // Método para formatar a entrada da tabela de símbolos, incluindo as
        // sub-tabelas
        public String formatarEntrada(String indent) {
            StringBuilder sb = new StringBuilder();
            String subTabelas = "";
            if (registro != null) {
                subTabelas = "\n" + registro.formatarTabela(indent + "    ");
            }
            sb.append(String.format("%-20s %-20s %-8s", indent + nome, tipo, ponteiro));
            if (!subTabelas.isEmpty()) {
                sb.append(" Registro");
            }
            sb.append(subTabelas);
            return sb.toString();
        }

    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public void adicionar(String nome, TipoLa tipo, Boolean ponteiro, TabelaDeSimbolos registro) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ponteiro, registro));
    }

    public boolean existe(String nome) {
        if (tabela.containsKey(nome)) {
            return true;
        }
        for (Map.Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
            String key = entry.getKey();
            TabelaDeSimbolos tabelaAdicional = tabela.get(key).registro;
            if (tabelaAdicional != null && tabelaAdicional.existe(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeNaTabelaPrincipal(String nome) {
        return tabela.containsKey(nome);
    }

    public boolean existeRegistro(String nome, String nomeVariavel) {
        return tabela.get(nome).registro.existe(nomeVariavel);
    }

    // Método para formatar a tabela, incluindo as sub-tabelas
    // Método para formatar a tabela, incluindo as sub-tabelas
    public String formatarTabela(String indent) {
        StringBuilder sb = new StringBuilder();

        // Imprimir o cabeçalho da tabela
        sb.append(String.format("%-20s %-20s %-8s %s%n", "Nome", "Tipo", "Ponteiro", "Registro"));

        // Imprimir as entradas da tabela
        for (EntradaTabelaDeSimbolos entrada : tabela.values()) {
            sb.append(entrada.formatarEntrada(indent)).append("\n");
        }

        return sb.toString();
    }

    // Método para imprimir a tabela de símbolos
    public void imprimirTabela() {
        System.out.println(formatarTabela(""));
    }

    public TipoLa verificar(String nome) {
        TipoLa tipo = TipoLa.INVALIDO;
        if (tabela.get(nome) != null) {
            return tabela.get(nome).tipo;
        } else {
            for (Map.Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
                String key = entry.getKey();
                TabelaDeSimbolos tabelaAdicional = tabela.get(key).registro;
                if (tabelaAdicional != null && tabelaAdicional.existe(nome)) {
                    return tabelaAdicional.tabela.get(nome).tipo;
                }
            }
        }
        return tipo;
    }

    public boolean verificarPonteiro(String nome) {
        return tabela.get(nome).ponteiro;
    }

    public boolean verificarPonteiroRegistro(String nome, String nomeVariavel) {
        return tabela.get(nome).registro.verificarPonteiro(nomeVariavel);
    }

    public TabelaDeSimbolos recuperaRegistro(String nome) {
        return tabela.get(nome).registro;
    }

    public Integer retornaNumeroDeElementosDaSubTabela(String nome) {
        return tabela.get(nome).registro.tabela.size();
    }

    public TipoLa verificarTipoRegistro(String nome, String nomeVariavel) {
        
        if(tabela.get(nome) != null){
            return tabela.get(nome).registro.verificar(nomeVariavel);
        };
        for (Entry<String, EntradaTabelaDeSimbolos> entry : tabela.entrySet()) {
                String key = entry.getKey();
                TabelaDeSimbolos tabelaAdicional = tabela.get(key).registro;
                if (tabelaAdicional != null && tabelaAdicional.existe(nome)) {
                    return tabelaAdicional.tabela.get(nome).registro.verificar(nomeVariavel);
            }
        }

        return null;


    }

    public Collection<EntradaTabelaDeSimbolos> retornarParametrosDaFuncao(String nomeFuncao) {
        return tabela.get(nomeFuncao).registro.tabela.values();
    }

}