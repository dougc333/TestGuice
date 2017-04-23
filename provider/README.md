The Guice provider feature consists of 3 parts
1) A provider interface. This defines a set of methods
independent of a service or a serviceImpl. 

2) A service interface. A collection of methods for the service.
The interface methods here are independent of the provider interface

3) A serviceImpl. The methods here are independent of the methods
in the provider interface. 

The goal is to implement the provider methods in the service
interface/impl. 

