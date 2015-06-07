com=javac

CLPATH=.:lib/commons-net-3.3/commons-net-3.3.jar:/usr/local/Java/jdk1.5.0_07/lib:lib/

all:stats genUtil/logger genUtil/list genUtil/stack

stats:
	$(com) -cp $(CLPATH) lib/stats/*.java
genUtil/list:stats genUtil/logger
	$(com) -cp $(CLPATH) lib/genUtil/list/*.java
genUtil/logger:stats
	$(com) -cp $(CLPATH) lib/genUtil/logger/*.java
genUtil/stack:genUtil/list
	 $(com) -cp $(CLPATH) lib/genUtil/stack/*.java
	
downloadScheduler:genUtil/list ftpUtil
	echo "warning: downloadScheduler is nether tested nor used";\
	$(com) -cp $(CLPATH) lib/downloadScheduler/*.java
ftpUtil:genUtil/list
	echo "warning: ftpUtil is nether tested nor used";\
	$(com) -cp $(CLPATH) lib/ftpUtil/*.java


