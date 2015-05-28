# The full power of OSGi #

As our applications run on an OSGi environment (equinox by now) we can use this to make our applications modular. How? Well just to give an example:

  * By installing the bundle (plug-in) com.antilia.export suddenly all the tables of your application are capable of exporting themselves to PDF, Excel, CSV, etcetera. This is shown in following screen shots:

http://antilia.googlecode.com/svn/wiki/exportpdf.PNG

and

http://antilia.googlecode.com/svn/wiki/exportpdf2.PNG

How does this work?

  * There is a service that allows to register Menu-entries providers.
  * The export bundle finds that service and registers a provider that knows how to add buttons that know how to export a table to PDF, Excel, etc.
  * When the table is rendering it ask this service for providers for the top-table menu, hence the export functionality.

Now, lets see this is completely dynamic. If we go to the OSGi console of eclipse and we type the command **ss** we will see something like:

http://antilia.googlecode.com/svn/wiki/exportpdf0.PNG

If we them type **stop 19** and do type again **ss** command we see export bundle is no longer active...as shown by the following screen-shot

http://antilia.googlecode.com/svn/wiki/exportpdf3.PNG

If we now go back to our application and reload the page we were in... We will see the buttons for exporting to PDF and Excel are no longer available!

http://antilia.googlecode.com/svn/wiki/exportpdf4.PNG

Nice, eh? Just think of the possibilities this opens up to your applications in terms of modularity and extensibility!




