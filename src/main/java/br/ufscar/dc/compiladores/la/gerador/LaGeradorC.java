package br.ufscar.dc.compiladores.la.gerador;

import org.stringtemplate.v4.ST;

import br.ufscar.dc.compiladores.la.gerador.TabelaDeSimbolos.TipoLa;
import br.ufscar.dc.compiladores.parser.LaBaseVisitor;
import br.ufscar.dc.compiladores.parser.LaParser;
import br.ufscar.dc.compiladores.parser.LaParser.CmdContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdEscrevaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CmdLeiaContext;
import br.ufscar.dc.compiladores.parser.LaParser.CorpoContext;
import br.ufscar.dc.compiladores.parser.LaParser.Declaracao_localContext;

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
        if(ctx.cmdLeia() != null){
            visitCmdLeia(ctx.cmdLeia());
        }
        if(ctx.cmdEscreva() != null){
            visitCmdEscreva(ctx.cmdEscreva());
        }
        return null;
    }

    @Override
    public Void visitCmdEscreva(CmdEscrevaContext ctx) {
        String nomeVariavel = ctx.expressao(0).getText();
        String tipoVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tabela.verificar(nomeVariavel));
        saida.append("\tprintf(\""+ tipoVariavel+"\", " + nomeVariavel + ");\n");
        return null;
    }

    @Override
    public Void visitCmdLeia(CmdLeiaContext ctx) {
        String nomeVariavel = ctx.identificador(0).getText();
        String tipoVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(tabela.verificar(nomeVariavel));
        saida.append("\tscanf(\""+ tipoVariavel+"\", &" + nomeVariavel + ");\n");
        return null;
    }

    @Override
    public Void visitDeclaracao_local(Declaracao_localContext ctx) {
        TipoLa tipoLaVariavel = LaSemanticoUtils.retornaTipoLaDoIdentificador(ctx.variavel().tipo().getText());
        String tipoVariavel = tipoLaVariavel.getValor();
        String nomeVariavel = ctx.variavel().identificador(0).getText();
        tabela.adicionar(nomeVariavel, tipoLaVariavel, false, null);
        saida.append("\t" + tipoVariavel + " " + nomeVariavel + ";\n");
        return null;
    }

    

    
}