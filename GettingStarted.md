# Introduction #

This page describes how to get started using Antilia's Wicket components and building your own Antila powered OSGi-wicket applications.

# Details #

For the time being we just describe how to get the couple of demo application we provide. Our demo applications, as Antilia itself, are build as (equinox) OSGI applications. So, you will need to use Eclipse as IDE if you want to be able to run the demos.

**NOTE**: this doesn't imply that some of the components we provide cannot be used in non-OSGi WEB applications.

## Let's play application ##

A small "gaming" application to help children learn to read.

  * Check out the projects:
    1. com.antilia.common
    1. com.antilia.demo.letsplay
    1. com.antilia.export
    1. com.antilia.ext
    1. com.antilia.hibernate
    1. com.antilia.web
  * If you open the Debugs dialog configuration and you go to the section OSGi Framework, you will find the letsplay34 launcher for Eclipse 3.4 (or letsplay35 for eclipse 3.5). You can use one them to start the application.
  * Then browse to http:localhost:8080/letsplay to see the application working. Just use  user=julior and password=julior to log into the application.



## A demo showing how to use Antilia to build Data-base oriented applications ##

This application shows Antilia's Data-oriented components: so far this mean a CRUD component that is capable of generating a complex CRUD out of a JPA annotated entity bean and some (optional) additional information...

In order to get the application working, you will need to:

  * Check out the projects:
    1. com.antilia.common
    1. com.antilia.demo.manager
    1. com.antilia.export
    1. com.antilia.ext
    1. com.antilia.hibernate
    1. com.antilia.ibatis
    1. com.antilia.web
  * If you open the Debugs dialog configuration  and you go to the section OSGi Framework, you will find the **manager34** launcher for Eclipse 3.4 (or the **manager35** for eclipse 3.5). You can use one them to start the application.
  * Then browse to http:localhost:8080/manager to see the application working. Just use any user=password to log into the application.

**Note:** This is work in progress.


