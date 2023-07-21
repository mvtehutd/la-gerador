grammar La;

// Palavras chave da gramática, representam palavras que possuem um sentindo/executam uma função na gramática.
PALAVRA_CHAVE: 'algoritmo' | 'declare' | 'literal' | 'inteiro' | 'real' | 'logico' | 'se' | 'entao' | 'senao' | 
    'fim_se' | 'caso' | 'seja' | 'fim_caso' | 'e' | 'nao' | 'ou' | 'leia' | 'escreva' | 'fim_algoritmo' | 'para' | 
    'faca' | 'fim_para' | 'ate' | 'tipo' | 'enquanto' | 'fim_enquanto' | 'registro' | 'fim_registro' | 'fim_procedimento' | 'var' |
    'procedimento' | 'funcao' | 'fim_funcao' | 'retorne' | 'constante' | 'falso' | 'verdadeiro';

// Identificadores para números inteiros e reais
NUM_INT: ('0'..'9')+;

NUM_REAL: ('0'..'9')+ ('.' ('0'..'9')+)?;

// Identifica uma cadeia de caracteres dentro de " ".
CADEIA: '"' ~('\n'|'\r'|'"')* '"';

// Operadores da gramática que vão desde aritméticas, relacionais, até aqueles que executam alguma ação na gramática.
DELIM: ':';

INTERVALO: '..';

OP_ARIT: '+' | '-' | '*' | '/' | '%';

OP_REL:	'=' | '≠' | '<>' | '>' | '<' | '≥' | '>=' | '≤' | '<=';

OP_CAMPO: '.';

ATRIBUICAO: '<-';

PONTEIRO: '^' | '&';


// Identifica um comentário que é representado por uma sequência de caracteres dentro de { } e ignora ele
COMENTARIO: '{' ~('\n'|'\r'|'{'|'}')* '}' {skip();};

// Ignora espaços em branco
WS: ( ' ' | '\t' | '\r' | '\n') {skip();};

// Identificadores para os [] e () e ,
ABREPAR: '(';

FECHAPAR: ')';

ABRECHAVE: '[';

FECHACHAVE: ']';

VIRGULA: ',';

// Reconhece os identificadores (variáveis), que são qualquer sequência de caracteres iniciada por uma letra ou _ (underline)
IDENT: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

// Identificadores que representam os possíveis erros
// Reconhece uma cadeia que não teve suas "" fechada
CADEIA_NAO_FECHADA: '"' ~('\n'|'\r'|'"')* '\n';

// Reconhece um comentário que não fechou sua }
COMENTARIO_NAO_FECHADO: '{' ~('\n'|'\r'|'{'|'}')* '\n' ;

// Qualquer outro símbolo não identificado é reconhecido aqui
ERRO: .;



// Gramática de LA
programa: declaracoes 'algoritmo' corpo 'fim_algoritmo';

declaracoes: decl_local_global*;

decl_local_global: declaracao_local | declaracao_global;

declaracao_local: declaracao = 'declare' variavel
                | constante = 'constante' IDENT ':' tipo_basico '=' valor_constante
                | declaracaoTipo = 'tipo' IDENT ':' tipo;

variavel: identificador (',' identificador)* ':' tipo;

identificador: IDENT (ponto = '.' IDENT)* dimensao;

dimensao: ('[' exp_aritmetica ']')*;

tipo: registro | tipo_estendido;

tipo_basico: 'literal' | 'inteiro' | 'real' | 'logico';

tipo_basico_ident: tipo_basico | IDENT;

tipo_estendido: '^'? tipo_basico_ident;

valor_constante: CADEIA | NUM_INT | NUM_REAL | 'verdadeiro' | 'falso';

registro: 'registro' variavel* 'fim_registro';

declaracao_global: 'procedimento' IDENT '(' parametros? ')' declaracao_local* cmd* 'fim_procedimento'
                | 'funcao' IDENT '(' parametros? ')' ':' tipo_estendido declaracao_local* cmd* 'fim_funcao';

parametro: 'var'? identificador (',' identificador)* ':' tipo_estendido;

parametros: parametro (',' parametro)*;

corpo: declaracao_local* cmd*;

cmd: cmdLeia | cmdEscreva | cmdSe | cmdCaso | cmdPara | cmdEnquanto
    | cmdFaca | cmdAtribuicao | cmdChamada | cmdRetorne;

cmdLeia: 'leia' '(' '^'? identificador (',' '^'? identificador)* ')';

cmdEscreva: 'escreva' '(' expressao (',' expressao)* ')';

cmdSe: 'se' expressao 'entao' cmd* ('senao' cmd*)* 'fim_se';

cmdCaso: 'caso' exp_aritmetica 'seja' selecao ('senao' cmd*)? 'fim_caso';

cmdPara: 'para' IDENT '<-' exp_aritmetica 'ate' exp_aritmetica 'faca' cmd* 'fim_para';

cmdEnquanto: 'enquanto' expressao 'faca' cmd* 'fim_enquanto';

cmdFaca: 'faca' cmd* 'ate' expressao;

cmdAtribuicao: '^'? identificador '<-' expressao;

cmdChamada: IDENT '(' expressao (',' expressao)* ')';

cmdRetorne: retorne='retorne' expressao;

selecao: item_selecao*;

item_selecao: constantes ':' cmd*;

constantes: numero_intervalo (',' numero_intervalo)*;

numero_intervalo: op_unario? NUM_INT ('..' op_unario? NUM_INT)?;

op_unario: '-';

exp_aritmetica: termo (op1 termo)*;

termo: fator (op2 fator)*;

fator: parcela (op3 parcela)*;

op1: '+' | '-';

op2: '*' | '/';

op3: '%';

parcela: op_unario? parcela_unario | parcela_nao_unario;

parcela_unario: (ponteiro = '^')? identificador
            | IDENT '(' expIdent += expressao (',' expIdent += expressao)* ')'
            | NUM_INT
            | NUM_REAL
            | '(' expPar = expressao ')';

parcela_nao_unario: '&' identificador | CADEIA;

exp_relacional: primeiraExp = exp_aritmetica (op_relacional outrasExp += exp_aritmetica)?;

op_relacional: '=' | '<>' | '>=' | '<=' | '>' | '<';

expressao: termo_logico (op_logico_1 termo_logico)*;

termo_logico: fator_logico (op_logico_2 fator_logico)*;

fator_logico: 'nao'? parcela_logica;

parcela_logica: ('verdadeiro' | 'falso') | exp_relacional;

op_logico_1: 'ou';

op_logico_2: 'e';