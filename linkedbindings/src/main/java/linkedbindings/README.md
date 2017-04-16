from webpage documentation

Linked Bindings

1) define a Module extends Abstract Module and a void configure() method
in the Module. In the module bind the higher level class to the implementation class; 
which in the example is TransactionLog to DatabaseTransactionLog

2) Add print statements to verify 
When you call TransactionLog you get a DatabaseTransactionLog b/c a concrete class was bound to the abstract class 
in the module

