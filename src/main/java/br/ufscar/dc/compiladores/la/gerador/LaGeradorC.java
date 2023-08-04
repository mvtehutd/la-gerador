package br.ufscar.dc.compiladores.la.gerador;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.compiladores.la.gerador.TabelaDeSimbolos.TipoLa;
import br.ufscar.dc.compiladores.parser.LaBaseVisitor;
import br.ufscar.dc.compiladores.parser.LaParser;
import br.ufscar.dc.compiladores.parser.LaParser.CmdAtribuicaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdCasoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdChamadaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdEnquantoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdEscrevaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdFacaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdLeiaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdParaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdRetorneContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdSeContext;
import br.ufscar.dc.compiladores.parser.LaParser.ConstantesContext;
import br.ufscar.dc.compiladores.parser.LaParser.CorpoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Declaracao_globalContext;
import br.ufscar.dc.compiladores.parser.LaParser.Declaracao_localContext;
import br.ufscar.dc.compiladores.parser.LaParser.Exp_relacionalContext;
import br.ufscar.dc.compiladores.parser.LaParser.ExpressaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Fator_logicoContext;
import br.ufscar.dc.compiladores.parser.LaParser.IdentificadorContext;
import br.ufscar.dc.compiladores.parser.LaParser.Item_selecaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Numero_intervaloContext;
import br.ufscar.dc.compiladores.parser.LaParser.Op_relacionalContext;
import br.ufscar.dc.compiladores.parser.LaParser.ParametroContext;
import br.ufscar.dc.compiladores.parser.LaParser.Parcela_logicaContext;
import br.ufscar.dc.compiladores.parser.LaParser.SelecaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Termo_logicoContext;
import br.ufscar.dc.compiladores.parser.LaParser.VariavelContext;

public class LaGeradorC extends LaBaseVisitor<Void> {

    StringBuilder saida;
    TabelaDeSimbolos tabela;

    public LaGeradorC() {
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }

    // Coloca a estrutura básica do programa, como includes e main, além de chamar as demais verificações
    @Override
    public Void visitPrograma(LaParser.ProgramaContext ctx) {
        saida.append("#include <stdio.h>\n");
        saida.append("#include <stdlib.h>\n");
        saida.append("\n");
        ctx.declaracoes().decl_local_global().forEach(decl_local_global -> visitDecl_local_global(decl_local_global));
        saida.append("\n");
        saida.append("int main() {\n");
        visitCorpo(ctx.corpo());
        saida.append("\treturn 0;\n}\n");
        return null;
    }

    // Corpo do programa (dentro da main) - manda visitar as declarações locais e os comandos
    @Override
    public Void visitCorpo(CorpoContext ctx) {
        ctx.declaracao_local().forEach(decl_local -> visitDeclaracao_local(decl_local));
        ctx.cmd().forEach(cmd -> visitCmd(cmd));
        return null;
    }

    // Verifica e chama o comando correspondente
    @Override
    public Void visitCmd(CmdContext ctx) {
        if (ctx.cmdLeia() != null) {
            visitCmdLeia(ctx.cmdLeia());
        }
        if (ctx.cmdEscreva() != null) {
            visitCmdEscreva(ctx.cmdEscreva());
        }
        if (ctx.cmdAtribuicao() != null) {
            visitCmdAtribuicao(ctx.cmdAtribuicao());
        }
        if (ctx.cmdSe() != null) {
            visitCmdSe(ctx.cmdSe());
        }
        if (ctx.cmdCaso() != null) {
            visitCmdCaso(ctx.cmdCaso());
        }
        if (ctx.cmdPara() != null) {
            visitCmdPara(ctx.cmdPara());
        }
        if (ctx.cmdEnquanto() != null) {
            visitCmdEnquanto(ctx.cmdEnquanto());
        }
        if (ctx.cmdFaca() != null) {
            visitCmdFaca(ctx.cmdFaca());
        }
        if (ctx.cmdRetorne() != null) {
            visitCmdRetorne(ctx.cmdRetorne());
        }
        if (ctx.cmdChamada() != null) {
            visitCmdChamada(ctx.cmdChamada());
        }
        return null;
    }

    // Faz a Conversão do Comando Escreva
    @Override
    public Void visitCmdEscreva(CmdEscrevaContext ctx) {
        String nomeVariavel = "";
        String stringParaImpressao = "";
        String variaveisParaImpressao = "";
        for (ExpressaoContext expressaoContext : ctx.expressao()) {
            nomeVariavel = expressaoContext.getText();

            // Imprimir String
            if (nomeVariavel.startsWith("\"")) {
                stringParaImpressao += nomeVariavel.substring(1, nomeVariavel.length() - 1);
            } else { // imprimir variáveis 

                TipoLa tipoLaExpressao = LaSemanticoUtils.verificarTipo(tabela, expressaoContext);

                String tipoIdentificador = LaSemanticoUtils
                        .retornaTipoLaDoIdentificador(tipoLaExpressao);

                stringParaImpressao += tipoIdentificador;

                variaveisParaImpressao += ", " + nomeVariavel;
            }
        }
        saida.append("\tprintf(\"" + stringParaImpressao + "\"" + variaveisParaImpressao + ");\n");
        return null;
    }

    // Conversão Comando Leia
    @Override
    public Void visitCmdLeia(CmdLeiaContext ctx) {
        String nomeVariavel = ctx.identificador(0).getText();
        TipoLa tipoLaVariavel = tabela.verificar(nomeVariavel);
        String tipoVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tabela.verificar(nomeVariavel));
        if (!tipoLaVariavel.equals(TipoLa.LITERAL)) { // Se não for string, põe &
            nomeVariavel = "&" + nomeVariavel;
        }
        saida.append("\tscanf(\"" + tipoVariavel + "\", " + nomeVariavel + ");\n");
        return null;
    }

    // Conversão Comando de Atribuição
    @Override
    public Void visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
        String nomeVariavelAtribuicao = ctx.identificador().getText();
        String expressaoAtribuida = ctx.expressao().getText();
        if (ctx.getText().startsWith("^")) { // se for ponteiro
            nomeVariavelAtribuicao = "*" + nomeVariavelAtribuicao;
        }
        // Se for string, usa o strcpy()
        if (LaSemanticoUtils.verificarTipo(tabela, ctx.expressao()).equals(TipoLa.LITERAL)) {
            saida.append("\tstrcpy(" + nomeVariavelAtribuicao + ", " + expressaoAtribuida + ");\n");
        } else { // senão faz a atribuição normalmente com =
            saida.append("\t" + nomeVariavelAtribuicao + " = " + expressaoAtribuida + ";\n");
        }
        return null;
    }

    // Conversão Comando Se
    @Override
    public Void visitCmdSe(CmdSeContext ctx) {
        saida.append("\tif(");
        visitExpressao(ctx.expressao());
        saida.append("){\n");
        for (CmdContext cmdSeContext : ctx.comandose) {
            saida.append("\t");
            visitCmd(cmdSeContext);
        }
        saida.append("\t}\n");
        if (ctx.comandosenao.size() > 0) { // se tem comando senão
            saida.append("\telse{\n");
            for (CmdContext cmdSenaoContext : ctx.comandosenao) {
                saida.append("\t");
                visitCmd(cmdSenaoContext);
            }
            saida.append("\t}\n");
        }
        return null;
    }

    // Conversão Expressão
    @Override
    public Void visitExpressao(ExpressaoContext ctx) {
        for (int i = 0; i < ctx.termo_logico().size(); i++) {
            visitTermo_logico(ctx.termo_logico(i));
            if (ctx.op_logico_1(i) != null) {
                saida.append(" || ");
            }
        }
        return null;
    }

    // Conversão Termo Lógico
    @Override
    public Void visitTermo_logico(Termo_logicoContext ctx) {
        for (int i = 0; i < ctx.fator_logico().size(); i++) {
            visitFator_logico(ctx.fator_logico(i));
            if (ctx.op_logico_2(i) != null) {
                saida.append(" && ");
            }
        }
        return null;
    }

    // Conversão Fator Lógico
    @Override
    public Void visitFator_logico(Fator_logicoContext ctx) {
        Parcela_logicaContext parcelaLogica = ctx.parcela_logica();
        Exp_relacionalContext expressaoRelacional = parcelaLogica.exp_relacional();
        if (ctx.getText().startsWith("nao")) {
            saida.append("!");
        }
        if (parcelaLogica.getText().equals("verdadeiro")) {
            saida.append("true");
        }
        if (parcelaLogica.getText().equals("falso")) {
            saida.append("false");
        }
        if (expressaoRelacional != null) {
            visitExp_relacional(expressaoRelacional);
        }
        return null;
    }

    // Conversão Expressão Relacional
    @Override
    public Void visitExp_relacional(Exp_relacionalContext ctx) {
        Op_relacionalContext operadorRelacional = ctx.op_relacional();

        if (operadorRelacional != null) { // se tem operador relacional (expressão simples)
            saida.append(ctx.exp_aritmetica(0).getText());
            if (operadorRelacional.getText().equals("<>")) {
                saida.append("!=");
            } else if (operadorRelacional.getText().equals("=")) {
                saida.append("==");
            } else {
                saida.append(operadorRelacional.getText());
            }
            if (ctx.exp_aritmetica().size() > 1) {
                saida.append(ctx.exp_aritmetica(1).getText());
            }
        } else { // com expressão aritmética
            saida.append("(");
            if (ctx.exp_aritmetica(0).termo(0).fator(0).parcela(0).parcela_nao_unario() != null) {
                saida.append(ctx.exp_aritmetica(0).termo(0).fator(0).parcela(0).parcela_nao_unario().getText());
            } else {
                visitExp_relacional(ctx.exp_aritmetica(0).termo(0).fator(0).parcela(0).parcela_unario().expressao(0)
                        .termo_logico(0).fator_logico(0).parcela_logica().exp_relacional());
            }
            saida.append(")");
        }
        return null;
    }

    // Conversão Comando Caso
    @Override
    public Void visitCmdCaso(CmdCasoContext ctx) {
        saida.append("\tswitch(" + ctx.exp_aritmetica().getText() + "){\n");
        visitSelecao(ctx.selecao());
        if (ctx.cmd().size() > 0) {
            saida.append("\t\tdefault:\n");
            for (CmdContext cmdContext : ctx.cmd()) {
                saida.append("\t\t");
                visitCmd(cmdContext);
            }
        }
        saida.append("\t}\n");
        return null;
    }

    // Conversão Seleção
    @Override
    public Void visitSelecao(SelecaoContext ctx) {
        for (Item_selecaoContext item_selecaoContext : ctx.item_selecao()) {
            visitConstantes(item_selecaoContext.constantes());
            for (CmdContext cmdContext : item_selecaoContext.cmd()) {
                saida.append("\t\t");
                visitCmd(cmdContext);
            }
            saida.append("\t\t\tbreak;\n");

        }
        return null;
    }

    // Conversão Constantes do Comando Caso
    @Override
    public Void visitConstantes(ConstantesContext ctx) {
        for (Numero_intervaloContext numero_intervaloContext : ctx.numero_intervalo()) {
            if (numero_intervaloContext.NUM_INT().size() > 1) {
                for (int i = Integer.parseInt(numero_intervaloContext.NUM_INT(0).getText()); i < Integer
                        .parseInt(numero_intervaloContext.NUM_INT(1).getText()); i++) {
                    saida.append("\t\tcase " + i + ":\n");
                }
            } else {
                saida.append("\t\tcase " + numero_intervaloContext.NUM_INT(0).getText() + ":\n");
            }
        }
        return null;
    }

    // Conversão Comando Para
    @Override
    public Void visitCmdPara(CmdParaContext ctx) {
        String variavel = ctx.IDENT().getText();
        String inicio = ctx.exp_aritmetica(0).getText();
        String fim = ctx.exp_aritmetica(1).getText();
        // estrutura for
        saida.append(
                "\tfor(" + variavel + " = " + inicio + "; " + variavel + " <= " + fim + "; " + variavel + "++){\n");
        for (CmdContext cmdContext : ctx.cmd()) { // Comandos
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t}\n");
        return null;
    }

    // Conversão Comando Enquanto
    @Override
    public Void visitCmdEnquanto(CmdEnquantoContext ctx) {
        // estrutura while
        saida.append("\twhile(");
        visitExpressao(ctx.expressao());
        saida.append("){\n");
        for (CmdContext cmdContext : ctx.cmd()) { // comandos
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t}\n");
        return null;
    }

    // COnversão Comando Faça
    @Override
    public Void visitCmdFaca(CmdFacaContext ctx) {
        saida.append("\tdo{\n");
        for (CmdContext cmdContext : ctx.cmd()) { // comandos
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t} while(");
        visitExpressao(ctx.expressao());
        saida.append(");\n");
        return null;
    }

    // Conversão do Comando de Retorno
    @Override
    public Void visitCmdRetorne(CmdRetorneContext ctx) {
        saida.append("\treturn " + ctx.expressao().getText() + ";\n");
        return null;
    }

    // Conversão do Comando de Chamada
    @Override
    public Void visitCmdChamada(CmdChamadaContext ctx) {
        saida.append("\t" + ctx.IDENT().getText());
        for (ExpressaoContext expressaoContext : ctx.expressao()) {
            visitExpressao(expressaoContext);
        }
        saida.append(";\n");
        return null;
    }

    // Conversão da Declaração Local
    @Override
    public Void visitDeclaracao_local(Declaracao_localContext ctx) {
        // Variáveis, Ponteiros e Registros
        if (ctx.getStart().getText().equals("declare") || ctx.getStart().getText().equals("tipo")) {
            Boolean ponteiro = false;
            TabelaDeSimbolos tabelaAdicional = null;
            String tipo = "";
            String nomeDoRegistro = "";
            switch (ctx.getStart().getText()) {
                case "declare":
                    tipo = ctx.variavel().tipo().getText();
                    nomeDoRegistro = ctx.variavel().identificador(0).getText();
                    break;
                case "tipo":
                    tipo = ctx.tipo().getText();
                    nomeDoRegistro = ctx.IDENT().getText();
                    break;
                default:
                    break;
            }

            if (tipo.startsWith("^")) { // ponteiro
                tipo = tipo.substring(1, tipo.length());
                ponteiro = true;
            }

            if (tipo.startsWith("registro")) { // tratamento do registro

                String typeDefStruct = ctx.getStart().getText().equals("tipo") ? "typedef " : "";
                saida.append("\t" + typeDefStruct + "struct {\n");
                tabelaAdicional = new TabelaDeSimbolos();

                List<VariavelContext> listaDeVariaveisDoRegistro = ctx.getStart().getText().equals("declare")
                        ? ctx.variavel().tipo().registro().variavel()
                        : ctx.tipo().registro().variavel();
                ;
                for (VariavelContext variavelContext : listaDeVariaveisDoRegistro) {
                    tipo = variavelContext.tipo().getText();
                    TipoLa tipoLaVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tipo);
                    String tipoVariavel = tipoLaVariavel.getValor();
                    String nomeVariavel = "";
                    String nomeImpressao = "";
                    List<IdentificadorContext> listaDeIdentificadores = variavelContext.identificador();
                    for (IdentificadorContext identificadorContext : listaDeIdentificadores) {
                        nomeVariavel = identificadorContext.getText();

                        nomeImpressao += nomeVariavel;
                        if (tipoLaVariavel.equals(TipoLa.LITERAL)) { // se for string, faz um vetor de char com 80 caracteres
                            nomeImpressao += "[80]";
                        }
                        if (ponteiro == true) { // se for ponteiro, acrescenta seu indicativo *
                            tipoVariavel += " *";
                        }
                        if (nomeVariavel.contains("[")) { // nome da variável sem []
                            nomeVariavel = nomeVariavel.substring(0, nomeVariavel.indexOf("["));
                        }
                        nomeImpressao += ",";

                        tabelaAdicional.adicionar(nomeVariavel, tipoLaVariavel, ponteiro, null);
                    }
                    nomeImpressao = nomeImpressao.substring(0, nomeImpressao.length() - 1);
                    saida.append("\t\t" + tipoVariavel + " " + nomeImpressao + ";\n");
                }
                saida.append("\t} " + nomeDoRegistro + ";\n");
                tabela.adicionar(nomeDoRegistro, TipoLa.REGISTRO, ponteiro, tabelaAdicional);
            } else { // variáveis normais ou de dentro de um registro

                TipoLa tipoLaVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tipo);
                String tipoVariavel = tipoLaVariavel.getValor();
                if (tipoLaVariavel.equals(TipoLa.REGISTRO)) { // se é de um registro, recupera sua tabela

                    tabelaAdicional = tabela.existeNaTabelaPrincipal(tipo) ? tabela.recuperaRegistro(tipo) : null;
                    tipoVariavel = tipo;
                }
                String nomeVariavel = "";
                String nomeImpressao = "";

                List<IdentificadorContext> listaDeIdentificadores = ctx.variavel().identificador();
                for (IdentificadorContext identificadorContext : listaDeIdentificadores) {
                    nomeVariavel = identificadorContext.getText();

                    nomeImpressao += nomeVariavel;
                    if (tipoLaVariavel.equals(TipoLa.LITERAL)) { // se for string, faz um vetor de char com 80 caracteres
                        nomeImpressao += "[80]";
                    }
                    if (ponteiro == true) { // se for ponteiro, acrescenta seu indicativo *
                        tipoVariavel += " *";
                    }
                    if (nomeVariavel.contains("[")) { // nome da variável sem []
                        nomeVariavel = nomeVariavel.substring(0, nomeVariavel.indexOf("["));
                    }
                    nomeImpressao += ",";

                    tabela.adicionar(nomeVariavel, tipoLaVariavel, ponteiro, tabelaAdicional);
                }
                nomeImpressao = nomeImpressao.substring(0, nomeImpressao.length() - 1);
                saida.append("\t" + tipoVariavel + " " + nomeImpressao + ";\n");
            }
        } else if (ctx.getText().startsWith("constante")) { // Constante
            TipoLa tipoLaConstante = LaSemanticoUtils.retornaTipoLaDoIdentificador(ctx.tipo_basico().getText());
            String nomeConstante = ctx.IDENT().getText();
            String valorConstante = ctx.valor_constante().getText();
            saida.append("#define " + nomeConstante + " " + valorConstante + "\n");
            tabela.adicionar(nomeConstante, tipoLaConstante, false, null);
        }

        return null;
    }

    // Conversão Declaração Global
    @Override
    public Void visitDeclaracao_global(Declaracao_globalContext ctx) {
        String nome = ctx.IDENT().getText();
        String nomeParametro = "";
        TipoLa tipoLaParametro = null;
        String tipoParametro = "";
        String parametros = "";
        if (ctx.getText().startsWith("funcao")) { // distinção para função com tipo de retorno
            TipoLa tipoFuncao = LaSemanticoUtils.retornaTipoLaDoIdentificador(ctx.tipo_estendido().getText());
            tabela.adicionar(nome, tipoFuncao, null, tabela);
            saida.append(tipoFuncao.getValor() + " " + nome + "(");
        }
        if (ctx.getText().startsWith("procedimento")) { // distinção para procedimentos (void)
            saida.append("void " + nome + "(");
        }
        visitParametros(ctx.parametros());
        for (ParametroContext parametro : ctx.parametros().parametro()) { // Recupera os parâmetros
            nomeParametro = parametro.identificador(0).getText();
            tipoLaParametro = LaSemanticoUtils.retornaTipoLaDoIdentificador(parametro.tipo_estendido().getText());
            tipoParametro = tipoLaParametro.getValor();
            if (tipoLaParametro.equals(TipoLa.LITERAL)) { // se é string, é um vetor de char, então põe *
                tipoParametro += " *";
            }
            parametros += tipoParametro + " " + nomeParametro + ",";
            tabela.adicionar(nomeParametro, tipoLaParametro, null, null);
        }
        saida.append(parametros.substring(0, parametros.length() - 1) + "){\n");
        for (Declaracao_localContext declaracao_localContext : ctx.declaracao_local()) { // Recupera as declarações locais
            visitDeclaracao_local(declaracao_localContext);
        }
        for (CmdContext cmdContext : ctx.cmd()) { // Recupera os comandos 
            visitCmd(cmdContext);
        }
        saida.append("}\n");
        return null;
    }
}