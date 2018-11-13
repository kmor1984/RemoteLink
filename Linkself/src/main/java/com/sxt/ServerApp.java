package com.sxt;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.sxt.model.Request;
import com.sxt.utils.ServiceRegist;

public class ServerApp {
/*此类的作用时，当客户端的服务注册完成之后，
 * 需要发布（只有发布完成了之后才能被发现）
 * 
 * */
	public static void serverPublish(int port) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		while(true) {
		//1、监听端口
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("等待连接"+port);
		//2、等待接收请求
		Socket accept = serverSocket.accept();
		System.out.println("连接成功");
		//3、获取到远程的调用请求
		InputStream inputStream = accept.getInputStream();
		//4、将输入流转化成为对象流
		ObjectInputStream objectInputStream = new ObjectInputStream (inputStream);
		//5、读对象流中的请求对象
		Request request = (Request)objectInputStream.readObject();
		//6、这个时候，我就可以从这个request里在读取它的类名，方法，参数
		      //6.1得到类名
		        String className = request.getClassName();
		     //6.2得到方法
				String classMethod = request.getClassMethod();
		      //6.3得到参数
				Object [] args = request.getArgs();
		//7、得到了以上信息后，可以利用反射机制来构造出来这个类
				//Class<?> clazz = Class.forName(className);
				 //7.2拿到这个类之后我们就可以new对象了
				// 知道接口名称，需要得到实现对象
//				Map<接口名称，实际对象> ioc
//				ioc.get()
				      Object instance = ServiceRegist.ioss.get(className);
	   //8、利用反射机制来构造出这个类的方法
				   //8.1得到这个类的方法时还需要提供一个参数的类型
				      Class<?> [] parameterTypes = new Class<?> [args.length];	
				     for(int i = 0;i<args.length;i++) {
				    	 parameterTypes[i]=args[i].getClass();
				     }
				Method method = instance.getClass().getMethod(classMethod, parameterTypes);
		//9、调用这个方法之后，把这个结果result给客户端
		         Object result = method.invoke(instance, args);
		         
		      /*  ============== 以下代码是把得到的结果给客户端=================*/
		         //1、用ServerSocket从对列用取出Socket,创建一个输出流r
		  OutputStream outputStream = accept.getOutputStream();
		        //2、把输出流转化成为对象流
		  ObjectOutputStream objectOutputStream= new ObjectOutputStream (outputStream) ;
		        //3、对象流读取对象信息
		  objectOutputStream.writeObject(result);
		  System.out.println("处理成完！返回给客户端！");  
		  
		    /*==================以下代码是关闭流，不然会造成内存泄露================*/     
		         close(objectOutputStream,outputStream,objectInputStream,inputStream,accept,serverSocket);
	}
	}
	      /*=================写一个关闭流的close方法来集中关闭==================*/
	public static  void close(Closeable ...close)  {
		
		for(Closeable closeable:close) {
			if(null!=close) {
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//这里关闭流时也可能报错，所以我们手动给它赋值null,以等待JVM来回收它。
				closeable=null;
			          }
		        }
		  }	
	}
	
	
	
	
	
	
	
}
