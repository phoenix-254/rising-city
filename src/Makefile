#
# A simple makefile for compiling the java classes in this project
#

# define a makefile variable for the java compiler
#
JC = javac

# define a makefile variable for compilation flags
# the -g flag compiles with debugging information
#
JFLAGS = -g

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	risingCity.java

default: classes

classes: $(CLASSES:.java=.class)

# To start over from scratch, type 'make clean'.  
# Removes all .class files, so that the next make rebuilds them
#
clean: 
	$(RM) *.class
	$(RM) */*.class