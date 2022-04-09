# Solid Principles
The SOLID principles were introduced by Robert C. Martin in his paper [Design Principles and Design Patterns](http://staff.cs.utu.fi/~jounsmed/doos_06/material/DesignPrinciplesAndPatterns.pdf) . These concepts  were later built upon by Michael Feathers, who introduced us to the SOLID acronym.

## Single Responsibility
The S in SOLID stands for Single Responsibility
A class should have only one responsibility. It should have only one reason to change. This principle makes the software easier to implement and prevents unexpected side-effects of future changes.

## Open/Closed - Open for Extension, Closed for Modification
The O in SOLID stands for Open/Closed.
Classes should be open for extension but closed for modification. In doing so, we stop ourselves from modifying existing code and causing potential new bugs.


>  The one exception to the rule is when fixing bugs in existing code


## Liskov Substitution
The L in SOLID stands for Liskov Substitution.

If class A is a subtype of class B, we should be able to replace B with A without disrupting the behavior of our program

## Interface Segregation
The I in SOLID stands for Interface Segregation

> Clients should not be forced to depend upon interfaces that they do not use

A client should not be exposed to methods it doesn\`t need. Declaring methods in an interface that the client doesn\`t need pollutes the interface and leads to a "bulky" or "fat"
interface

## Dependency Inversion
The D in SOLID stands for Dependency Inversion.
"Depend upon abstractions, not conretions". 

* High level modules should not depend on low-level modules. Both should depend on abstractions
* Abstractions should not depend on details. Details should depend on abstractions

### Why is it important? 
Makes programs more
* Reusable
* Testable
* Maintainable
* Efficient*



