package projekt.downloadScheduler;

public interface Worker
extends Runnable
{

	public int work();
	public void start();

}
