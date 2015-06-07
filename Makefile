com=javac

CLPATH=.:lib/commons-net-3.3/commons-net-3.3.jar:/usr/local/Java/jdk1.5.0_07/lib:lib/:main/src/

all:stats generificationUtil/logger generificationUtil/list generificationUtil/stack

stats:
	$(com) -cp $(CLPATH) main/src/statics/*.java
generificationUtil/list:stats generificationUtil/logger
	$(com) -cp $(CLPATH) main/src/generificationUtil/list/*.java
generificationUtil/logger:stats
	$(com) -cp $(CLPATH) main/src/generificationUtil/logger/*.java
generificationUtil/stack:generificationUtil/list
	$(com) -cp $(CLPATH) main/src/generificationUtil/stack/*.java

main:generificationUtil/logger
	$(com) -cp $(CLPATH) main/src/Main.java
exeMain:main
	cd main/src;\
	java Main;\
	cd ../..
exeMainNoComp:
	cd main/src;\
	java Main;\
	cd ../..
	
