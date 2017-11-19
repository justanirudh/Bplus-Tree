JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        treesearch.java \
				BEntry.java \
				BPair.java \
        InvalidInputException.java \
				StringUtils.java \
				Tree.java \
				TreeNode.java \
				TreeUtils.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	rm -rf *.class output_file.txt
