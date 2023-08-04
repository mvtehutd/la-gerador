package br.ufscar.dc.compiladores.la.gerador;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

import br.ufscar.dc.compiladores.la.gerador.TabelaDeSimbolos.TipoLa;
import br.ufscar.dc.compiladores.parser.LaParser;
import br.ufscar.dc.compiladores.parser.LaParser.Exp_aritmeticaContext;
import br.ufscar.dc.compiladores.parser.LaParser.Exp_relacionalContext;
import br.ufscar.dc.compiladores.parser.LaParser.ExpressaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.FatorContext;
import br.ufscar.dc.compiladores.parser.LaParser.Fator_logicoContext;
import br.ufscar.dc.compiladores.parser.LaParser.ParcelaContext;
import br.ufscar.dc.compiladores.parser.LaParser.Parcela_logicaContext;
import br.ufscar.dc.compiladores.parser.LaParser.TermoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Termo_logicoContext;

public class LaSemanticoUtils {
    public static List<String> errosSemanticos = new ArrayList<>();

    // Mensagens erros
    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    // Verifica os tipos da expressão
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, ExpressaoContext expressaoContext) {
        TabelaDeSimbolos.TipoLa ret = null;
        // Percorre os termos lógicos e, se forem iguais retorna o tipo, senão retorna o
        // tipo inválido
        for (Termo_logicoContext ta : expressaoContext.termo_logico()) {
            
            TabelaDeSimbolos.TipoLa aux = verificarTipo(tabela, ta);
            if (ret == null) {
                ret = aux;
            } else if (tiposDiferentes(aux, ret) && aux != TabelaDeSimbolos.TipoLa.INVALIDO) {
                ret = TabelaDeSimbolos.TipoLa.INVALIDO;
            }
        }

        return ret;
    }

    // verifica os tipos do termo lógico
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            Termo_logicoContext termoLogicoContext) {
        TabelaDeSimbolos.TipoLa ret = null;
        // Percorre os fatores lógicos e, se forem iguais retorna o tipo, senão retorna
        // o tipo inválido
        for (Fator_logicoContext ta : termoLogicoContext.fator_logico()) {
            TabelaDeSimbolos.TipoLa aux = verificarTipo(tabela, ta);
            if (ret == null) {
                ret = aux;
            } else if (tiposDiferentes(aux, ret) && aux != TabelaDeSimbolos.TipoLa.INVALIDO) {
                ret = TabelaDeSimbolos.TipoLa.INVALIDO;
            }
        }

        return ret;
    }

    // Se for Fator Lógico, manda verificar a parcela lógica
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            Fator_logicoContext fatorLogicoContext) {
        return verificarTipo(tabela, fatorLogicoContext.parcela_logica());

    }

    // verifica os tipos da parcela lógica.
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            Parcela_logicaContext parcelaLogicaContext) {
        // Se for 'verdadeiro' ou 'falso', já retorna o tipo lógico, senão verifica a
        // expressão relacional
        if (parcelaLogicaContext.getText().equals("verdadeiro") || parcelaLogicaContext.getText().equals("falso")) {
            return TipoLa.LOGICO;
        } else {
            return verificarTipo(tabela, parcelaLogicaContext.exp_relacional());
        }
    }

    // verifica os tipos da expressão relacional
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            Exp_relacionalContext expressaoRelacionalContext) {
        // Se tiver operador relacional, então é do tipo lógico, senão verifica a
        // expressão aritmética
        if (expressaoRelacionalContext.op_relacional() != null) {
            return TipoLa.LOGICO;
        } else {
            return verificarTipo(tabela, expressaoRelacionalContext.primeiraExp);
        }
    }

    // verifica os tipos da expressão aritmética
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            Exp_aritmeticaContext expressaoContext) {
        TabelaDeSimbolos.TipoLa ret = null;
        // Percorre os termos verificando os tipos e os retorna. Se forem diferentes,
        // retorna o tipo inválido
        for (TermoContext ta : expressaoContext.termo()) {
            TabelaDeSimbolos.TipoLa aux = verificarTipo(tabela, ta);
            if (ret == null) {
                ret = aux;
            } else if (tiposDiferentes(aux, ret) && aux != TabelaDeSimbolos.TipoLa.INVALIDO) {
                ret = TabelaDeSimbolos.TipoLa.INVALIDO;
            }
        }

        return ret;
    }

    // verifica os tipos do termo
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, LaParser.TermoContext ctx) {
        TabelaDeSimbolos.TipoLa ret = null;
        // Percorre os fatores verificando e retornando seus tipos. Se forem diferentes,
        // retorna o tipo inválido
        for (FatorContext fa : ctx.fator()) {
            TabelaDeSimbolos.TipoLa aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (tiposDiferentes(aux, ret) && aux != TabelaDeSimbolos.TipoLa.INVALIDO) {
                ret = TabelaDeSimbolos.TipoLa.INVALIDO;
            }
        }
        return ret;
    }

    // verifica os tipos do fator
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, LaParser.FatorContext ctx) {
        TabelaDeSimbolos.TipoLa ret = null;
        // Percorre as parcelas verificando e retornando seus tipos. Se forem
        // diferentes, retorna o tipo inválido
        for (ParcelaContext fa : ctx.parcela()) {
            TabelaDeSimbolos.TipoLa aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (tiposDiferentes(aux, ret) && aux != TabelaDeSimbolos.TipoLa.INVALIDO) {
                ret = TabelaDeSimbolos.TipoLa.INVALIDO;
            }
        }
        return ret;
    }

    // verifica os tipos da parcela
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, LaParser.ParcelaContext ctx) {
        TipoLa tipo = null;
        // verifica se é uma parcela unária ou não
        if (ctx.parcela_unario() != null) {
            tipo = verificarTipo(tabela, ctx.parcela_unario());
            return tipo;
        }
        if (ctx.parcela_nao_unario() != null) {
            return verificarTipo(tabela, ctx.parcela_nao_unario());
        }
        // se não for nenhum dos tipos acima, só pode ser um operador unário
        // que não estava presente nos casos de teste
        return tipo;
    }

    // verifica os tipos da parcela unária
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, LaParser.Parcela_unarioContext ctx) {
        // verifica se é inteiro, real ou o tipo da variável
        if (ctx.NUM_INT() != null) {
            return TabelaDeSimbolos.TipoLa.INTEIRO;
        }
        if (ctx.NUM_REAL() != null) {
            return TabelaDeSimbolos.TipoLa.REAL;
        }

        if (ctx.identificador() != null || ctx.ponteiro != null) {
            if (ctx.identificador().ponto != null) {
                return tabela.verificarTipoRegistro(ctx.identificador().IDENT(0).getText(),
                        ctx.identificador().IDENT(1).getText());
            }
            if (!ctx.identificador().dimensao().isEmpty()) {
                return tabela.verificar(ctx.identificador().IDENT(0).getText());
            }
            return tabela.verificar(ctx.identificador().getText());
        }

        if (ctx.IDENT() != null) {
            return tabela.verificar(ctx.IDENT().getText());
        }
        // se não for nenhum dos tipos acima, dentre os casos de teste, só pode ser
        // uma expressão entre parêntesis
        return verificarTipo(tabela, ctx.expPar);
    }

    // verifica os tipos da parcela não unária
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela,
            LaParser.Parcela_nao_unarioContext ctx) {
        // verifica o tipo da variável que está registrada na tabela de símbolos ou
        // então é uma cadeia, do tipo literal
        if (ctx.identificador() != null) {
            if (ctx.identificador().ponto != null) {
                return tabela.verificarTipoRegistro(ctx.identificador().IDENT(0).getText(),
                        ctx.identificador().IDENT(1).getText());
            }
            return tabela.verificar(ctx.identificador().getText());
        } else {
            return TabelaDeSimbolos.TipoLa.LITERAL;
        }
    }

    // verifica o tipo da variável
    public static TabelaDeSimbolos.TipoLa verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }

    // método que compara se dois tipos não são compatíveis.
    // ele retorna true caso forem diferentes ou false caso forem compatíveis
    public static boolean tiposDiferentes(TipoLa primeiro, TipoLa segundo) {
        if (primeiro == segundo) {
            return false;
        }
        // garante que considere a compatibilidade entre inteiro e real
        if ((primeiro == TipoLa.INTEIRO || primeiro == TipoLa.REAL)
                && (segundo == TipoLa.INTEIRO || segundo == TipoLa.REAL)) {
            return false;
        }
        return true;
    }

    public static TipoLa retornaTipoLaDoIdentificador(TabelaDeSimbolos tabela, Token token, String tipo) {
        // verifica se é algum dos tipos padrão
        switch (tipo) {
            case
                    "inteiro":
                return TipoLa.INTEIRO;
            case
                    "literal":
                return TipoLa.LITERAL;
            case
                    "real":
                return TipoLa.REAL;
            case
                    "logico":
                return TipoLa.LOGICO;

            default:
                if ((tabela.existe(tipo) && tabela.verificar(tipo) == TipoLa.REGISTRO)) {
                    return TipoLa.REGISTRO;
                } else {
                    LaSemanticoUtils.adicionarErroSemantico(token,
                            "tipo " + tipo + " nao declarado");
                }
                break;
        }
        return TipoLa.INVALIDO;
    }
    
    public static TipoLa retornaTipoLaDoIdentificador( String tipo) {
        // verifica se é algum dos tipos padrão
        switch (tipo) {
            case
                    "inteiro":
                return TipoLa.INTEIRO;
            case
                    "literal":
                return TipoLa.LITERAL;
            case
                    "real":
                return TipoLa.REAL;
            case
                    "logico":
                return TipoLa.LOGICO;

            default:
                return TipoLa.REGISTRO;
        }
    }

    // retorna o tipo para printf em C
    public static String retornaTipoLaDoIdentificador( TipoLa tipo) {
        // verifica se é algum dos tipos padrão
        
        switch (tipo) {
            case
                    INTEIRO:
                return "%d";
            case
                    LITERAL:
                return "%s";
            case
                    REAL:
                return "%f";

            default:
                return "";
        }
    }
}