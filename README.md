# TODO:

- Better Request-Logging 
- Unit-Test
- Integration-Test
- Proper README file



__If time left__  
Hibernate with CDI  
The following Java types cannot be proxied by the container:

classes which don’t have a non-private constructor with no parameters, and
classes which are declared final or have a final method,
arrays and primitive types.
It’s usually very easy to fix an unproxyable dependency problem. If an injection point of type X results in an unproxyable dependency, simply:

add a constructor with no parameters to X,
change the type of the injection point to`Instance<X>`,
introduce an interface Y, implemented by the injected bean, and change the type of the injection point to Y, or
if all else fails, change the scope of the injected bean to @Dependent.