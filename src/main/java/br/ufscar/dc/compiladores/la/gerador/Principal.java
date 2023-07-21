package br.ufscar.dc.compiladores.la.gerador;

import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.ufscar.dc.compiladores.parser.LaLexer;
import br.ufscar.dc.compiladores.parser.LaParser;
import br.ufscar.dc.compiladores.parser.LaParser.ProgramaContext;

public class Principal {
    public static void main(String args[]) throws IOException {
        // args[0] é o primeiro argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE ENTRADA COM A LINGUAGEM
        CharStream cs = CharStreams.fromFileName(args[0]);
        LaLexer lexer = new LaLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LaParser parser = new LaParser(tokens);
        ProgramaContext arvore = parser.programa();
        LaSemantico as = new LaSemantico();
        as.visitPrograma(arvore);
        // Pega o segundo argumento da linha de comando que representa o CAMINHO PARA O ARQUIVO DE SAIDA DA ANALISE SEMÂNTICA
        // E Cria um objeto para escrever no arquivo
        if(LaSemanticoUtils.errosSemanticos.isEmpty()) {
            LaGeradorC agc = new LaGeradorC();
            agc.visitPrograma(arvore);
            try(PrintWriter pw = new PrintWriter(args[1])) {
                pw.print(agc.saida.toString());
                System.out.println(agc.saida.toString());
            }
        }
    }
}