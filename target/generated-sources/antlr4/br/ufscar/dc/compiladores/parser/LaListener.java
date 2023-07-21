// Generated from java-escape by ANTLR 4.11.1
package br.ufscar.dc.compiladores.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LaParser}.
 */
public interface LaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LaParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(LaParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(LaParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracoes(LaParser.DeclaracoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#declaracoes}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracoes(LaParser.DeclaracoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void enterDecl_local_global(LaParser.Decl_local_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#decl_local_global}.
	 * @param ctx the parse tree
	 */
	void exitDecl_local_global(LaParser.Decl_local_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_local(LaParser.Declaracao_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#declaracao_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_local(LaParser.Declaracao_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#variavel}.
	 * @param ctx the parse tree
	 */
	void enterVariavel(LaParser.VariavelContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#variavel}.
	 * @param ctx the parse tree
	 */
	void exitVariavel(LaParser.VariavelContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#identificador}.
	 * @param ctx the parse tree
	 */
	void enterIdentificador(LaParser.IdentificadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#identificador}.
	 * @param ctx the parse tree
	 */
	void exitIdentificador(LaParser.IdentificadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void enterDimensao(LaParser.DimensaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#dimensao}.
	 * @param ctx the parse tree
	 */
	void exitDimensao(LaParser.DimensaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(LaParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(LaParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico(LaParser.Tipo_basicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#tipo_basico}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico(LaParser.Tipo_basicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void enterTipo_basico_ident(LaParser.Tipo_basico_identContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#tipo_basico_ident}.
	 * @param ctx the parse tree
	 */
	void exitTipo_basico_ident(LaParser.Tipo_basico_identContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void enterTipo_estendido(LaParser.Tipo_estendidoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#tipo_estendido}.
	 * @param ctx the parse tree
	 */
	void exitTipo_estendido(LaParser.Tipo_estendidoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void enterValor_constante(LaParser.Valor_constanteContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#valor_constante}.
	 * @param ctx the parse tree
	 */
	void exitValor_constante(LaParser.Valor_constanteContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#registro}.
	 * @param ctx the parse tree
	 */
	void enterRegistro(LaParser.RegistroContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#registro}.
	 * @param ctx the parse tree
	 */
	void exitRegistro(LaParser.RegistroContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao_global(LaParser.Declaracao_globalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#declaracao_global}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao_global(LaParser.Declaracao_globalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(LaParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(LaParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(LaParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(LaParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#corpo}.
	 * @param ctx the parse tree
	 */
	void enterCorpo(LaParser.CorpoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#corpo}.
	 * @param ctx the parse tree
	 */
	void exitCorpo(LaParser.CorpoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(LaParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(LaParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeia(LaParser.CmdLeiaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdLeia}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeia(LaParser.CmdLeiaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscreva(LaParser.CmdEscrevaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdEscreva}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscreva(LaParser.CmdEscrevaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(LaParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(LaParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void enterCmdCaso(LaParser.CmdCasoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdCaso}.
	 * @param ctx the parse tree
	 */
	void exitCmdCaso(LaParser.CmdCasoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(LaParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(LaParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(LaParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(LaParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void enterCmdFaca(LaParser.CmdFacaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdFaca}.
	 * @param ctx the parse tree
	 */
	void exitCmdFaca(LaParser.CmdFacaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void enterCmdAtribuicao(LaParser.CmdAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdAtribuicao}.
	 * @param ctx the parse tree
	 */
	void exitCmdAtribuicao(LaParser.CmdAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void enterCmdChamada(LaParser.CmdChamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdChamada}.
	 * @param ctx the parse tree
	 */
	void exitCmdChamada(LaParser.CmdChamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void enterCmdRetorne(LaParser.CmdRetorneContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#cmdRetorne}.
	 * @param ctx the parse tree
	 */
	void exitCmdRetorne(LaParser.CmdRetorneContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#selecao}.
	 * @param ctx the parse tree
	 */
	void enterSelecao(LaParser.SelecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#selecao}.
	 * @param ctx the parse tree
	 */
	void exitSelecao(LaParser.SelecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void enterItem_selecao(LaParser.Item_selecaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#item_selecao}.
	 * @param ctx the parse tree
	 */
	void exitItem_selecao(LaParser.Item_selecaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#constantes}.
	 * @param ctx the parse tree
	 */
	void enterConstantes(LaParser.ConstantesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#constantes}.
	 * @param ctx the parse tree
	 */
	void exitConstantes(LaParser.ConstantesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void enterNumero_intervalo(LaParser.Numero_intervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#numero_intervalo}.
	 * @param ctx the parse tree
	 */
	void exitNumero_intervalo(LaParser.Numero_intervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void enterOp_unario(LaParser.Op_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op_unario}.
	 * @param ctx the parse tree
	 */
	void exitOp_unario(LaParser.Op_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void enterExp_aritmetica(LaParser.Exp_aritmeticaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#exp_aritmetica}.
	 * @param ctx the parse tree
	 */
	void exitExp_aritmetica(LaParser.Exp_aritmeticaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(LaParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(LaParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(LaParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(LaParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op1}.
	 * @param ctx the parse tree
	 */
	void enterOp1(LaParser.Op1Context ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op1}.
	 * @param ctx the parse tree
	 */
	void exitOp1(LaParser.Op1Context ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op2}.
	 * @param ctx the parse tree
	 */
	void enterOp2(LaParser.Op2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op2}.
	 * @param ctx the parse tree
	 */
	void exitOp2(LaParser.Op2Context ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op3}.
	 * @param ctx the parse tree
	 */
	void enterOp3(LaParser.Op3Context ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op3}.
	 * @param ctx the parse tree
	 */
	void exitOp3(LaParser.Op3Context ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parcela}.
	 * @param ctx the parse tree
	 */
	void enterParcela(LaParser.ParcelaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parcela}.
	 * @param ctx the parse tree
	 */
	void exitParcela(LaParser.ParcelaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_unario(LaParser.Parcela_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parcela_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_unario(LaParser.Parcela_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void enterParcela_nao_unario(LaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parcela_nao_unario}.
	 * @param ctx the parse tree
	 */
	void exitParcela_nao_unario(LaParser.Parcela_nao_unarioContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void enterExp_relacional(LaParser.Exp_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#exp_relacional}.
	 * @param ctx the parse tree
	 */
	void exitExp_relacional(LaParser.Exp_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void enterOp_relacional(LaParser.Op_relacionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op_relacional}.
	 * @param ctx the parse tree
	 */
	void exitOp_relacional(LaParser.Op_relacionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressao(LaParser.ExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressao(LaParser.ExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void enterTermo_logico(LaParser.Termo_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#termo_logico}.
	 * @param ctx the parse tree
	 */
	void exitTermo_logico(LaParser.Termo_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void enterFator_logico(LaParser.Fator_logicoContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#fator_logico}.
	 * @param ctx the parse tree
	 */
	void exitFator_logico(LaParser.Fator_logicoContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void enterParcela_logica(LaParser.Parcela_logicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#parcela_logica}.
	 * @param ctx the parse tree
	 */
	void exitParcela_logica(LaParser.Parcela_logicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_1(LaParser.Op_logico_1Context ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op_logico_1}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_1(LaParser.Op_logico_1Context ctx);
	/**
	 * Enter a parse tree produced by {@link LaParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void enterOp_logico_2(LaParser.Op_logico_2Context ctx);
	/**
	 * Exit a parse tree produced by {@link LaParser#op_logico_2}.
	 * @param ctx the parse tree
	 */
	void exitOp_logico_2(LaParser.Op_logico_2Context ctx);
}