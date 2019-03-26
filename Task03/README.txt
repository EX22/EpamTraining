The project’s technical description.

    The application performs text parsing capabilities, as well as required in
the task conditions functionality such as sort paragraphs by sentences amount,
sort words in each sentences by length, sort lexemes in a whole text in
descending order according to given symbol appearance, restore initial text with
calculated binary expressions.
    Text parsing capabilities here perform by means parsers in a logic package
united in a chain which is an implementation of behavioral design pattern
Chain of Responsibility. At the same time in an entity package classes
implement design pattern Composite that allows put almost all methods
implementation in a parent abstract class “TextComposite” while common methods
themselves declared in “TextComponent” interface. All text component here is
represented by specific entity all of that extend abstract class.
    There are binary expressions in given text example which is needed to be
calculated. This capability is implemented using the Interpreter behavioral
pattern with applying Functional Interfaces. In general logic of this
functionality were put in “ExpressionParser” and “ExpressionCalc” classes,
that perform respectively transforming binary expression into reverse Polish
notation and its result calculation, using lambda expressions and
“Expression” functional interface’s capability.
