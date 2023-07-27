package br.ufscar.dc.compiladores.la.gerador;

import java.util.List;

import br.ufscar.dc.compiladores.la.gerador.TabelaDeSimbolos.TipoLa;
import br.ufscar.dc.compiladores.parser.LaBaseVisitor;
import br.ufscar.dc.compiladores.parser.LaParser;
import br.ufscar.dc.compiladores.parser.LaParser.CmdAtribuicaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdCasoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdEnquantoContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdEscrevaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdFacaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdLeiaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdParaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdSeContext;
import br.ufscar.dc.compiladores.parser.LaParser.ConstantesContext;
import br.ufscar.dc.compiladores.parser.LaParser.CorpoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Declaracao_localContext;
import br.ufscar.dc.compiladores.parser.LaParser.Exp_relacionalContext;
import br.ufscar.dc.compiladores.parser.LaParser.ExpressaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Fator_logicoContext;
import br.ufscar.dc.compiladores.parser.LaParser.IdentificadorContext;
import br.ufscar.dc.compiladores.parser.LaParser.Item_selecaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Numero_intervaloContext;
import br.ufscar.dc.compiladores.parser.LaParser.Op_relacionalContext;
import br.ufscar.dc.compiladores.parser.LaParser.Parcela_logicaContext;
import br.ufscar.dc.compiladores.parser.LaParser.SelecaoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Termo_logicoContext;

public class LaGeradorC extends LaBaseVisitor<Void> {

    StringBuilder saida;
    TabelaDeSimbolos tabela;

    public LaGeradorC() {
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }

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

    @Override
    public Void visitCorpo(CorpoContext ctx) {
        ctx.declaracao_local().forEach(decl_local -> visitDeclaracao_local(decl_local));
        ctx.cmd().forEach(cmd -> visitCmd(cmd));
        return null;
    }

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
        if(ctx.cmdPara() != null){
            visitCmdPara(ctx.cmdPara());
        }
        if(ctx.cmdEnquanto() != null){
            visitCmdEnquanto(ctx.cmdEnquanto());
        }
        if(ctx.cmdFaca() != null){
            visitCmdFaca(ctx.cmdFaca());
        }
        return null;
    }

    @Override
    public Void visitCmdEscreva(CmdEscrevaContext ctx) {
        String nomeVariavel = "";
        String stringParaImpressao = "";
        String variaveisParaImpressao = "";
        for (ExpressaoContext expressaoContext : ctx.expressao()) {
            nomeVariavel = expressaoContext.getText();
            if (nomeVariavel.startsWith("\"")) {
                stringParaImpressao += nomeVariavel.substring(1, nomeVariavel.length() - 1);
            } else {
                TipoLa tipoLaExpressao = LaSemanticoUtils.verificarTipo(tabela, expressaoContext);
                String tipoIdentificador = LaSemanticoUtils
                        .retornaTipoLaDoIdentificador(tipoLaExpressao);
                System.out.println(tipoIdentificador);
                stringParaImpressao += tipoIdentificador;
                System.out.println("STRING: " + stringParaImpressao);
                variaveisParaImpressao += ", " + nomeVariavel;
            }
        }
        saida.append("\tprintf(\"" + stringParaImpressao + "\"" + variaveisParaImpressao + ");\n");
        return null;
    }

    @Override
    public Void visitCmdLeia(CmdLeiaContext ctx) {
        String nomeVariavel = ctx.identificador(0).getText();
        TipoLa tipoLaVariavel = tabela.verificar(nomeVariavel);
        String tipoVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tabela.verificar(nomeVariavel));
        if (!tipoLaVariavel.equals(TipoLa.LITERAL)) {
            nomeVariavel = "&" + nomeVariavel;
        }
        saida.append("\tscanf(\"" + tipoVariavel + "\", " + nomeVariavel + ");\n");
        return null;
    }

    @Override
    public Void visitCmdAtribuicao(CmdAtribuicaoContext ctx) {
        String nomeVariavelAtribuicao = ctx.identificador().getText();
        String expressaoAtribuida = ctx.expressao().getText();
        System.out.println(nomeVariavelAtribuicao);
        System.out.println(expressaoAtribuida);
        saida.append("\t" + nomeVariavelAtribuicao + " = " + expressaoAtribuida + ";\n");
        return null;
    }

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
        if (ctx.comandosenao.size() > 0) {
            saida.append("\telse{\n");
            for (CmdContext cmdSenaoContext : ctx.comandosenao) {
                saida.append("\t");
                visitCmd(cmdSenaoContext);
            }
            saida.append("\t}\n");
        }
        return null;
    }

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

    @Override
    public Void visitExp_relacional(Exp_relacionalContext ctx) {
        Op_relacionalContext operadorRelacional = ctx.op_relacional();
        System.out.println(ctx.getText());
        if (operadorRelacional != null) {
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
        }else{
            saida.append("(");
            visitExp_relacional(ctx.exp_aritmetica(0).termo(0).fator(0).parcela(0).parcela_unario().expressao(0)
                        .termo_logico(0).fator_logico(0).parcela_logica().exp_relacional());
            saida.append(")");
        }
        return null;
    }

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

    @Override
    public Void visitCmdPara(CmdParaContext ctx) {
        String variavel = ctx.IDENT().getText();
        String inicio = ctx.exp_aritmetica(0).getText();
        String fim = ctx.exp_aritmetica(1).getText();
        saida.append("\tfor("+ variavel + " = " + inicio + "; " + variavel + " <= " + fim + "; " + variavel + "++){\n");
        for (CmdContext cmdContext : ctx.cmd()) {
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t}\n");
        return null;
    }

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

    @Override
    public Void visitCmdEnquanto(CmdEnquantoContext ctx) {
        saida.append("\twhile(");
        visitExpressao(ctx.expressao());
        saida.append("){\n");
        for (CmdContext cmdContext : ctx.cmd()) {
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t}\n");
        return null;
    }

    @Override
    public Void visitCmdFaca(CmdFacaContext ctx) {
        saida.append("\tdo{\n");
        for (CmdContext cmdContext : ctx.cmd()) {
            saida.append("\t");
            visitCmd(cmdContext);
        }
        saida.append("\t} while(");
        visitExpressao(ctx.expressao());
        saida.append(");\n");
        return null;
    }

    @Override
    public Void visitDeclaracao_local(Declaracao_localContext ctx) {
        System.out.println(ctx.getText());
        if (ctx.variavel() != null) {
            TipoLa tipoLaVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(ctx.variavel().tipo().getText());
            String tipoVariavel = tipoLaVariavel.getValor();
            String nomeVariavel = "";
            String nomeImpressao = "";

            List<IdentificadorContext> listaDeIdentificadores = ctx.variavel().identificador();
            for (IdentificadorContext identificadorContext : listaDeIdentificadores) {
                nomeVariavel = identificadorContext.getText();
                System.out.println(nomeVariavel);
                System.out.println(nomeImpressao);
                nomeImpressao += nomeVariavel;
                if (tipoLaVariavel.equals(TipoLa.LITERAL)) {
                    nomeImpressao += "[80]";
                }
                nomeImpressao += ",";
                System.out.println("ctx: " + ctx.variavel().tipo().getText() + "verificacao: " + tipoVariavel);
                tabela.adicionar(nomeVariavel, tipoLaVariavel, false, null);
            }
            nomeImpressao = nomeImpressao.substring(0, nomeImpressao.length() - 1);
            saida.append("\t" + tipoVariavel + " " + nomeImpressao + ";\n");
        } else if (ctx.getText().startsWith("constante")) {
            TipoLa tipoLaConstante = LaSemanticoUtils.retornaTipoLaDoIdentificador(ctx.tipo_basico().getText());
            String nomeConstante = ctx.IDENT().getText();
            String valorConstante = ctx.valor_constante().getText();
            saida.append("#define " + nomeConstante + " " + valorConstante + "\n");
            tabela.adicionar(nomeConstante, tipoLaConstante, false, null);
        }

        return null;
    }

}