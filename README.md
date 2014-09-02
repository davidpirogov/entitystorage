Entity Storage
==============

**Note: Experimental!**

This is a library for modelling efficient entity storage based on different types of required efficiencies.
While I realise that these problems have been solved many times over in file systems and data transfer protocols, this is purely a learning exercise for me.

Efficiency Drivers
 * Speed - fast read and write, or
 * Volume - efficient use of available space

The storage model is comprised of a Domain, which is a homogenous storage type. A Domain contains many elements of type Entity. An object of type Entity contains many elements of same type (Entity), some of which are serialisable children.

In summary:
 * A Domain is universal scope
 * An Entity is world and local scope
 
Lastly, I would not recommend using this code in production.

