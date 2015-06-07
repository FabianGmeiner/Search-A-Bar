package tests;
import generificationUtil.serializer.*;
import generificationUtil.list.*;


public class TestSerializer
{
	public static void dosth()
	{
		Serializer ser=new Serializer("TestSerializer.ser");
		ListStore store=new ListStore("TestSerializer");
		String[] strs=new String[4];
		strs[0]="Hallo";
		strs[1]=" Welt!";
		strs[2]=" Wie geht";
		strs[3]=" es dir?";
		store.append(strs);
		ser.addObject(store);
		ser.serialize();

		Deserializer deSer=new Deserializer("TestSerializer.ser");
		deSer.readObjs();
		ListStore deStore=(ListStore) deSer.getObject();
		String  deStr=(String)deStore.remove();
		System.out.printf("%s\n",deStr);

		
	}
}
