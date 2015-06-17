com=javac

CLPATH=.:lib/commons-net-3.3/commons-net-3.3.jar:/usr/local/Java/jdk1.5.0_07/lib:lib/:main/src/

all:stats generalUtil/logger generalUtil/list generalUtil/stack

stats:
	$(com) -cp $(CLPATH) main/src/statics/*.java
generalUtil/list:stats generalUtil/logger
	$(com) -cp $(CLPATH) main/src/generalUtil/list/*.java
generalUtil/logger:stats generalUtil/PathFinder
	$(com) -cp $(CLPATH) main/src/generalUtil/logger/*.java
generalUtil/stack:generalUtil/list
	$(com) -cp $(CLPATH) main/src/generalUtil/stack/*.java
generalUtil/PathFinder:stats generalUtil/OsDetector
	$(com) -cp $(CLPATH) main/src/generalUtil/PathFinder.java
generalUtil/OsDetector:
	$(com) -cp $(CLPATH) main/src/generalUtil/OsDetector.java
generalUtil/serializer:generalUtil/logger
	$(com) -cp $(CLPATH) main/src/generalUtil/serializer/*.java
encryption:
	$(com) -cp $(CLPATH) main/src/encryption/*.java
password:encryption
	$(com) -cp $(CLPATH) main/src/password/*.java

testSer:
	$(com) -cp $(CLPATH) main/src/tests/TestSerializer.java;\
#	cd main/src/tests/;\
#	java TestSerializer;\
#	cd ../../..;

main:
	$(com) -cp $(CLPATH) main/src/Main.java
exeMain:main
	cd main/src;\
	java Main;\
	cd ../..
exeMainNoComp:
	cd main/src;\
	java Main;\
	cd ../..
	
