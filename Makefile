com=javac

CLPATH=.:lib/commons-net-3.3/commons-net-3.3.jar:/usr/local/Java/jdk1.5.0_07/lib:lib/:main/src/

all:stats generificationUtil/logger generificationUtil/list generificationUtil/stack

stats:
	$(com) -cp $(CLPATH) main/src/statics/*.java
generificationUtil/list:stats generificationUtil/logger
	$(com) -cp $(CLPATH) main/src/generificationUtil/list/*.java
generificationUtil/logger:stats generificationUtil/PathFinder
	$(com) -cp $(CLPATH) main/src/generificationUtil/logger/*.java
generificationUtil/stack:generificationUtil/list
	$(com) -cp $(CLPATH) main/src/generificationUtil/stack/*.java
generificationUtil/PathFinder:stats generificationUtil/OsDetector
	$(com) -cp $(CLPATH) main/src/generificationUtil/PathFinder.java
generificationUtil/OsDetector:
	$(com) -cp $(CLPATH) main/src/generificationUtil/OsDetector.java
generificationUtil/serializer:generificationUtil/logger
	$(com) -cp $(CLPATH) main/src/generificationUtil/serializer/*.java
krypt:
	$(com) -cp $(CLPATH) main/src/krypt/*.java
passwd:krypt
	$(com) -cp $(CLPATH) main/src/passwd/*.java

testSer:
	$(com) -cp $(CLPATH) main/src/tests/TestSerializer.java;\
#	cd main/src/tests/;\
#	java TestSerializer;\
#	cd ../../..;

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
	
