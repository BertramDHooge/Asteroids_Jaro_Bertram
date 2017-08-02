// ANTLR v4 Grammar for controling UFO entities in Asteroids.
// Check the tutorial at http://jnb.ociweb.com/jnb/jnbJun2008.html
// or http://www.antlr.org/ for details.


grammar AsteroidsProgram;

@header {
    package asteroids.part3.programs.internal.generated;
}


// ------------------------------------------------------------------------
// --- Eval and Related Definitions ---------------------------------------
// ------------------------------------------------------------------------
program:	(funcdef+=functiondef)*
            programBody=statementSequence
;

functiondef: DEF funcname=IDENTIFIER
               LEFT_BRACE body=statementSequence RIGHT_BRACE
;

statement:    assignmentStatement
            | whileStatement
            | breakStatement
            | returnStatement
            | ifStatement
            | printStatement
            | actionStatement
; 

assignmentStatement:   variableName=IDENTIFIER ASSIGN value=bool ';'
;

whileStatement:    WHILE condition=bool LEFT_BRACE
                      body=statementSequence
                   RIGHT_BRACE
;

breakStatement: BREAK ';'
;

returnStatement: RETURN value=bool ';'
;

ifStatement: IF condition=bool LEFT_BRACE
                ifbody=statementSequence
             RIGHT_BRACE
            (ELSE LEFT_BRACE
            	elsebody=statementSequence RIGHT_BRACE)?
;

printStatement: PRINT value=bool ';'
;

statementSequence: stmts+=statement (stmts+=statement)*
; 

actionStatement:  THRUSTON ';' #thrustOnAction
                | THRUSTOFF ';' #thrustOffAction
                | TURN angle=bool ';' #turnAction
                | FIRE ';' #fireAction
                | SKIP_ACTION ';' #skipAction
;

bool:   number=NUMBER #numberExpression
            | variable=IDENTIFIER #readVariableExpression
            | parameter=PARAM #readParameterExpression
            | entit=entity #entityExpression
            | LEFT_PAREN subExpr=bool RIGHT_PAREN #parenExpression
            | SUB operand=bool #changeSignExpression
            | leftAdd=bool ADD rightAdd=bool #addExpression
            | leftMul=bool MUL rightMult=bool #mulExpression
            | functCall=functionCall #functCall
            | NOT expr=bool  #notExpression
            | SQRT expr=bool  #sqrtExpression
            | GETX expr=bool #getXExpression
            | GETY expr=bool #getYExpression
            | GETVX expr=bool #getVXExpression
            | GETVY expr=bool #getVYExpression
            | GETRADIUS expr=bool #getRadiusExpression
            | dir=GETDIR #getDirectionExpression
            | leftEq=bool EQ rightEq=bool #equalExpression
            | leftLt=bool LT rightLt=bool #lessThanExpression
;

entity:   NULL
        | SELF
        | SHIP
        | ASTEROID
        | PLANETOID
        | BULLET
        | PLANET
        | ANY
;

functionCall: funcName=IDENTIFIER LEFT_PAREN
                  (actualArgs+=bool (COMMA actualArgs+=bool)*)?
              RIGHT_PAREN
;
            

// ------------------------------------------------------------------------
// --- Specifiers -----------------------------------------------
// ------------------------------------------------------------------------

NULL:       'null';
SELF:       'self';
SHIP:       'ship';
ASTEROID:   'asteroid';
PLANETOID:  'planetoid';
BULLET:     'bullet';
PLANET:     'planet';
ANY:        'any';


// ------------------------------------------------------------------------
// --- Unary Operations ---------------------------------------------------
// ------------------------------------------------------------------------
GETRADIUS: 'getradius';
GETX:      'getx';
GETY:      'gety';
GETVX:     'getvx';
GETVY:     'getvy';
GETDIR:    'getdir';
SQRT:      'sqrt';
NOT:       '!';


// ------------------------------------------------------------------------
// --- Space Entity Actions -----------------------------------------------
// ------------------------------------------------------------------------
THRUSTON:  'thrust';
THRUSTOFF: 'thrust_off';
TURN:      'turn';
FIRE:      'fire'; 
SKIP_ACTION:      'skip';


// ------------------------------------------------------------------------
// --- Control Flow -------------------------------------------------------
// ------------------------------------------------------------------------
DEF:       'def';
IF:        'if';
THEN:      'then';
ELSE:      'else';
WHILE:     'while';
BREAK:     'break';
RETURN:    'return';
PRINT:     'print';
NOTHING:   'nothing';


// ------------------------------------------------------------------------
// --- Assignment and Arithmetics -----------------------------------------
// ------------------------------------------------------------------------
ASSIGN:   ':=';
MUL:      '*';
ADD:      '+';
SUB:      '-';
EQ:       '==';
LT:       '<';


// ------------------------------------------------------------------------
// --- Literals and Variables ---------------------------------------------
// ------------------------------------------------------------------------

NUMBER:  INTEGER | FLOAT;
FLOAT:   INTEGER '.' ('0'..'9')+;
INTEGER: ('-'|'+')? ('0'..'9')+;

IDENTIFIER: LETTER (LETTER | DIGIT | '_')*;
fragment LETTER: LOWER | UPPER;
fragment LOWER: 'a'..'z';
fragment UPPER: 'A'..'Z';
fragment DIGIT: '0'..'9';

PARAM: DOLLAR DIGIT (DIGIT)*;

COMMA: ',';
DOLLAR: '$';

LEFT_PAREN: '(';
RIGHT_PAREN: ')';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';

// ------------------------------------------------------------------------
// --- Syntactical Ballast ------------------------------------------------
// ------------------------------------------------------------------------

// Skip runs of newline, space and tab characters.
WHITESPACE: [ \t\r\n]+ -> skip;
 
// Single-line comments begin with //, are followed by any characters
// other than those in a newline, and are terminated by newline characters.
SINGLE_COMMENT: '//' ~('\r' | '\n')* NEWLINE -> skip;
fragment NEWLINE: ('\r'? '\n')+;

