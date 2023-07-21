// Generated from java-escape by ANTLR 4.11.1
package br.ufscar.dc.compiladores.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LaParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(LaParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#declaracoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracoes(LaParser.DeclaracoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#decl_local_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_local_global(LaParser.Decl_local_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#declaracao_local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_local(LaParser.Declaracao_localContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#variavel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariavel(LaParser.VariavelContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#identificador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificador(LaParser.IdentificadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#dimensao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensao(LaParser.DimensaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(LaParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#tipo_basico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico(LaParser.Tipo_basicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_basico_ident(LaParser.Tipo_basico_identContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_estendido(LaParser.Tipo_estendidoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#valor_constante}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_constante(LaParser.Valor_constanteContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#registro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistro(LaParser.RegistroContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#declaracao_global}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao_global(LaParser.Declaracao_globalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametro(LaParser.ParametroContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(LaParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#corpo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCorpo(LaParser.CorpoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmd(LaParser.CmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdLeia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdLeia(LaParser.CmdLeiaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEscreva(LaParser.CmdEscrevaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdSe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdSe(LaParser.CmdSeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdCaso}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdCaso(LaParser.CmdCasoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdPara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdPara(LaParser.CmdParaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdEnquanto(LaParser.CmdEnquantoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdFaca}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdFaca(LaParser.CmdFacaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdAtribuicao(LaParser.CmdAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdChamada}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdChamada(LaParser.CmdChamadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmdRetorne(LaParser.CmdRetorneContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelecao(LaParser.SelecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#item_selecao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem_selecao(LaParser.Item_selecaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#constantes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantes(LaParser.ConstantesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumero_intervalo(LaParser.Numero_intervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unario(LaParser.Op_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_aritmetica(LaParser.Exp_aritmeticaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#termo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo(LaParser.TermoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#fator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator(LaParser.FatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp1(LaParser.Op1Context ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp2(LaParser.Op2Context ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp3(LaParser.Op3Context ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parcela}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela(LaParser.ParcelaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parcela_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_unario(LaParser.Parcela_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_nao_unario(LaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#exp_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp_relacional(LaParser.Exp_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op_relacional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_relacional(LaParser.Op_relacionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressao(LaParser.ExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#termo_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermo_logico(LaParser.Termo_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#fator_logico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFator_logico(LaParser.Fator_logicoContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#parcela_logica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParcela_logica(LaParser.Parcela_logicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op_logico_1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_1(LaParser.Op_logico_1Context ctx);
	/**
	 * Visit a parse tree produced by {@link LaParser#op_logico_2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_logico_2(LaParser.Op_logico_2Context ctx);
}