# abas-tools-showcase
Showcase for all modules in abas tools


The showcase contains an infosystem which shows the english/german names of all fields in a Vartab. The goal of the showcase is to show the integration of different tools and how to use them together in abas tools.

* AJO Developer
* FO Developer
* Screen Designer
* Report Designer


Installation

1. Make sure you have the workDir ow1 in abas
2. Import the infosystem from the project showcase/infosystem
* copy the tgz file to the root folder of client
* extract the tgz file using tar -xf option
* import the infosystem using abas ERP and then generate the Java class
3. Import the project into client/java and add java/projects/showcase/classes to mandant.classpath
4. Customize the jasper report in abas
5. Replace the provided jasper report layout to win/jasper/layout/
