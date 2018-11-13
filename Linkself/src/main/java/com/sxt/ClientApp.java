package com.sxt;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sxt.model.Request;

public class ClientApp {
/*此类是发生一个请求对象，需要进供host,和端口及请求*/
	public static Object invoker (String host,int port,Request request) throws Exception {
		//发送请求对象
		Socket socket = new Socket(host,port);
		//创建一个输出流，要把你的问题发送出去，别人才能解决呢！
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream); 
		objectOutputStream.writeObject(request);
		
	  //接收响应
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream= new ObjectInputStream(inputStream);
		Object Object = objectInputStream.readObject();
	//搞定之后呢，关闭资源
		close(objectInputStream,inputStream,objectOutputStream,outputStream,socket);
		return Object;
		}
	
	//总体关闭的方法
	public static void close (Closeable ... close) {
		for (Closeable closeable : close) {
			try {
				if(null!=close){
				closeable.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				closeable=null;
			}
		}
		
		
		
		
	} 
	
	
	
	
	
	
	
	
	
}
