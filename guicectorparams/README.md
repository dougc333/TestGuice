
For a ctor like Dog(String name, Animal animal) 
where do we inject the name parameter? 
Guice when creating the class ignores primitive types and will create 
the class/instance without waiting for the parameter to be there. This is a 
case of mixed parameters, some supplied by Guice, some supplied by the user.

Use the Guice concept of factories where factories return instances and the
Guice FactoryModuleBuilder. 
 
From the docs: 
https://google.github.io/guice/api-docs/latest/javadoc/index.html?com/google/inject/assistedinject/FactoryModuleBuilder.html

public class RealPayment implements Payment{
  public RealPayment(
    CreditService cs; //from injector
    Date startDate; // from user of class RealPayment
  );
}

//step 1: create a factory to return the class. 
public interface PaymentFactory{
  public Payment create(Date startDate);
}

//note this is different using Provider<CreditService> cs vs. CreditService cs
public class RealPaymentFactory implements PaymentFactory{
  private final Provider<CreditService> cs; 
  public PaymentFactory(Provicer<CreditService> creditService){
    this.cs = creditService;
  }
  
}







This is annoying to have to write the boiler plate code everytime 
there are mixed parameters. Replace the above verbose code with AssistedInject








