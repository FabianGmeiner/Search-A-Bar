com=javac

CLPATH=.:lib/commons-net-3.3/commons-net-3.3.jar:/usr/local/Java/jdk1.5.0_07/lib:lib/

all:stats generificationUtil/logger generificationUtil/list generificationUtil/stack

stats:
	$(com) -cp $(CLPATH) lib/stats/*.java
generificationUtil/list:stats generificationUtil/logger
	$(com) -cp $(CLPATH) lib/generificationUtil/list/*.java
generificationUtil/logger:stats
	$(com) -cp $(CLPATH) lib/generificationUtil/logger/*.java
generificationUtil/stack:generificationUtil/list
	 $(com) -cp $(CLPATH) lib/generificationUtil/stack/*.java
	
